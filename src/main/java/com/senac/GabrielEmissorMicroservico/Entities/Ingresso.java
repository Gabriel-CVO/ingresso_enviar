package com.senac.GabrielEmissorMicroservico.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingresso")
public class Ingresso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ingresso_nome_comprador", length = 255)
    private String nomeComprador;

    @Column(name = "ingresso_cpf_comprador", length = 255)
    private String cpfComprador;

    @Column(name = "ingresso_idade_comprador")
    private Integer idadeComprador;

    @Column(name = "ingresso_status")
    private Integer status;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public String getCpfComprador() {
        return cpfComprador;
    }

    public void setCpfComprador(String cpfComprador) {
        this.cpfComprador = cpfComprador;
    }

    public Integer getIdadeComprador() {
        return idadeComprador;
    }

    public void setIdadeComprador(Integer idadeComprador) {
        this.idadeComprador = idadeComprador;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
