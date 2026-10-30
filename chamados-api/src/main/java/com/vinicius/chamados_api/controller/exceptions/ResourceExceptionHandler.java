package com.vinicius.chamados_api.controller.exceptions;

import com.vinicius.chamados_api.dto.ErroResponseDTO;
import com.vinicius.chamados_api.service.exceptions.ChamadoInexistenteException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ChamadoInexistenteException.class)
    public ResponseEntity<ErroResponseDTO> chamadoInexistente(HttpServletRequest request, ChamadoInexistenteException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroResponseDTO erroResponseDTO = new ErroResponseDTO(status.value(), e.getMessage(), LocalDateTime.now(), request.getRequestURI());
        return ResponseEntity.status(status).body(erroResponseDTO);
    }

}

