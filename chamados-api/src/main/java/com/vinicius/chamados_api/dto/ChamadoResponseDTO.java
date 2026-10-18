package com.vinicius.chamados_api.dto;

import com.vinicius.chamados_api.entity.enums.StatusChamado;

public record ChamadoResponseDTO(Long id, String titulo, StatusChamado status) {
}
