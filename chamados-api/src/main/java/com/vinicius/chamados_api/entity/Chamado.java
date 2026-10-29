package com.vinicius.chamados_api.entity;

import com.vinicius.chamados_api.entity.enums.StatusChamado;
import jakarta.persistence.*;

@Entity
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private StatusChamado status;

    protected Chamado() {

    }

    public Chamado(String titulo, StatusChamado status) {
        this.titulo = titulo;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public StatusChamado getStatus() {
        return status;
    }
}
