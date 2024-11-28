package com.senac.GabrielEmissorMicroservico.Services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.GabrielEmissorMicroservico.Entities.Ingresso;
import com.senac.GabrielEmissorMicroservico.Repositories.IngressoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class IngressoService implements Serializable {

    private IngressoRepository ingressoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public IngressoService(IngressoRepository ingressoRespository) {
        this.ingressoRepository = ingressoRespository;
    }

    @Transactional
    public List<Ingresso> buscarIngressos() {
        return ingressoRepository.buscarIngressos();
    }

    @Transactional
    public Optional<Ingresso> buscarIngressosPorId(int id) {
        return ingressoRepository.buscarIngressosPorId(id);
    }

    @Transactional
    public Ingresso adicionarIngresso(Ingresso ingresso) {
        Ingresso salvo = ingressoRepository.save(ingresso);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend("ingressomq", "fila-ingresso", salvo);
        return salvo;
    }

    @Transactional
    public Ingresso atualizarIngresso(Ingresso ingresso, int id) {
        Ingresso ingressoExistente = ingressoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Ingresso não encontrado"));

        ingressoExistente.setCpfComprador(ingresso.getCpfComprador());
        ingressoExistente.setNomeComprador(ingresso.getNomeComprador());
        ingressoExistente.setIdadeComprador(ingresso.getIdadeComprador());

        return ingressoRepository.save(ingressoExistente);
    }

    @Transactional
    public void apagarIngressoPorId(int id) {
        ingressoRepository.deleteById(id);
    }

    @RabbitListener(queues = "fila-ingresso")
    private void subscribe(Ingresso ingresso) {
        if (ingresso != null) {
            System.out.println("Ingresso recebido: " + ingresso.getNomeComprador());
        } else {
            System.err.println("Mensagem inválida recebida.");
        }
    }
}
