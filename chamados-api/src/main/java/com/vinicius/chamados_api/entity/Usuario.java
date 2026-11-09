package com.vinicius.chamados_api.entity;


import com.vinicius.chamados_api.entity.enums.Cargo;
import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nome;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    protected Usuario() {
    }

    public Usuario(String nome, String senha, Cargo cargo) {
        this.nome = nome;
        this.senha = senha;
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public Cargo getCargo() {
        return cargo;
    }
}
