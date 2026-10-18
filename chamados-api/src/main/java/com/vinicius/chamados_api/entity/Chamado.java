package com.vinicius.chamados_api.entity;

import com.vinicius.chamados_api.entity.enums.StatusChamado;

public class Chamado {
    private Long id;
    private String titulo;
    private StatusChamado status;

    public Chamado(Long id, String titulo, StatusChamado status) {
        this.id = id;
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
