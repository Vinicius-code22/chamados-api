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
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    protected Chamado() {

    }

    public Chamado(String titulo, StatusChamado status, Usuario usuario) {
        this.titulo = titulo;
        this.status = status;
        this.usuario = usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }
}
