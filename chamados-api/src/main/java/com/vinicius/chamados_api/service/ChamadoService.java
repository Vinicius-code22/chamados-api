package com.vinicius.chamados_api.service;

import com.vinicius.chamados_api.dto.ChamadoRequestDTO;
import com.vinicius.chamados_api.dto.ChamadoResponseDTO;
import com.vinicius.chamados_api.entity.Chamado;
import com.vinicius.chamados_api.entity.enums.StatusChamado;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChamadoService {

    private Long ultimoId = 3L;

    private final List<Chamado> listChamados = new ArrayList<>(List.of(new Chamado(1L, "Pc desligou", StatusChamado.EM_AVALIACAO),
            new Chamado(2L, "Preventiva notebook", StatusChamado.AGUARDANDO_APROVACAO),
            new Chamado(3L, "Preventiva pc", StatusChamado.FINALIZADO)));


    private ChamadoResponseDTO converterParaDTO(Chamado chamado) {
        return new ChamadoResponseDTO(chamado.getId(), chamado.getTitulo(), chamado.getStatus());
    }

    public List<ChamadoResponseDTO> listar() {
       return listChamados.stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public ChamadoResponseDTO buscarPorId(Long id) {
        for (Chamado chamado : listChamados) {
            if (chamado.getId().equals(id)) {
                return converterParaDTO(chamado);
            }
        }

        return null;
    }

    public ChamadoResponseDTO criar (ChamadoRequestDTO chamadoRequestDTO) {
        ultimoId++;
        Chamado chamado = new Chamado(ultimoId, chamadoRequestDTO.titulo(), StatusChamado.RECEBIDO);
        listChamados.add(chamado);

        return converterParaDTO(chamado);
    }
}

