package com.senac.GabrielEmissorMicroservico.Configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class MQConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @PostConstruct
    private void createResources() {
        Queue queue = new Queue("fila-ingresso", true);

        DirectExchange directExchange = new DirectExchange("ingressomq");

        Binding binding = new Binding(
            queue.getName(),
            Binding.DestinationType.QUEUE,
            directExchange.getName(),
            queue.getName(),
            null
        );

        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareBinding(binding);
    }
}

