package com.vinicius.chamados_api.service.exceptions;

public class ChamadoInexistenteException extends RuntimeException {
    public ChamadoInexistenteException(Long id) {
        super("Chamado inexistente com id " + id);
    }
}
