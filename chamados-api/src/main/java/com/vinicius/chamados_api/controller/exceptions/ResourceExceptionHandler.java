package com.vinicius.chamados_api.controller.exceptions;

import com.vinicius.chamados_api.dto.ErroResponseDTO;
import com.vinicius.chamados_api.service.exceptions.ChamadoInexistenteException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ChamadoInexistenteException.class)
    public ResponseEntity<ErroResponseDTO> chamadoInexistente(HttpServletRequest request, ChamadoInexistenteException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroResponseDTO erroResponseDTO = new ErroResponseDTO(status.value(), e.getMessage(), LocalDateTime.now(), request.getRequestURI());
        return ResponseEntity.status(status).body(erroResponseDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseDTO> chamadoInvalido(HttpServletRequest request, MethodArgumentNotValidException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String mensagem = e.getBindingResult().getFieldErrors()
                .stream().map(erro -> String.format("%s: %s", erro.getField(), erro.getDefaultMessage()))
                .collect(Collectors.joining(", "));
        ErroResponseDTO erroResponseDTO = new ErroResponseDTO(status.value(), mensagem, LocalDateTime.now(), request.getRequestURI());
        return ResponseEntity.status(status).body(erroResponseDTO);
    }


}

