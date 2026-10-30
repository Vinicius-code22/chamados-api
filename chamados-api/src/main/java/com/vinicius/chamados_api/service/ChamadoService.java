package com.vinicius.chamados_api.service;

import com.vinicius.chamados_api.dto.ChamadoRequestDTO;
import com.vinicius.chamados_api.dto.ChamadoResponseDTO;
import com.vinicius.chamados_api.entity.Chamado;
import com.vinicius.chamados_api.entity.enums.StatusChamado;
import com.vinicius.chamados_api.repository.ChamadoRepository;
import com.vinicius.chamados_api.service.exceptions.ChamadoInexistenteException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;

    public ChamadoService(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }
    private ChamadoResponseDTO converterParaDTO(Chamado chamado) {
        return new ChamadoResponseDTO(chamado.getId(), chamado.getTitulo(), chamado.getStatus());
    }

    public List<ChamadoResponseDTO> listar() {
       return chamadoRepository.findAll().stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public ChamadoResponseDTO buscarPorId(Long id) {

        return chamadoRepository.findById(id).map(this::converterParaDTO).orElseThrow(() -> new ChamadoInexistenteException(id));
    }

    public ChamadoResponseDTO criar (ChamadoRequestDTO chamadoRequestDTO) {
        Chamado chamado = new Chamado(chamadoRequestDTO.titulo(), StatusChamado.RECEBIDO);
        Chamado chamadoSalvo = chamadoRepository.save(chamado);

        return converterParaDTO(chamadoSalvo);
    }

    public void deletarPorId(Long id) {
        chamadoRepository.deleteById(id);
    }
}

