    package com.vinicius.chamados_api.dto;


    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;

    public record ChamadoRequestDTO( @NotBlank(message = "O título é obrigatório") @Size(min = 5, max = 250, message = "O título deve ter entre {min} e {max} caracteres") String titulo) {

    }
