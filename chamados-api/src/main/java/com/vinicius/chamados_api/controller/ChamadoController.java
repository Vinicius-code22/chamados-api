package com.vinicius.chamados_api.controller;

import com.vinicius.chamados_api.dto.ChamadoResponseDTO;
import com.vinicius.chamados_api.service.ChamadoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RequestMapping("/chamados")
@RestController
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @GetMapping
    public List<ChamadoResponseDTO> listar() {
        return chamadoService.listar();
    }

    @GetMapping("/{id}")
    public ChamadoResponseDTO buscarPorId(@PathVariable Long id) {
        return chamadoService.buscarPorId(id);
    }
}
