package com.challenge.churn.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class ChurnPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull(message = "A probabilidade de churn não pode ser nula")
    private Double probabilidadeChurn;
    @Column
    @NotNull(message = "A data de predição não pode ser nula")
    private Date dataPredicao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getProbabilidadeChurn() {
        return probabilidadeChurn;
    }

    public void setProbabilidadeChurn(Double probabilidadeChurn) {
        this.probabilidadeChurn = probabilidadeChurn;
    }

    public Date getDataPredicao() {
        return dataPredicao;
    }

    public void setDataPredicao(Date dataPredicao) {
        this.dataPredicao = dataPredicao;
    }
}
