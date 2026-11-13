package com.vinicius.chamados_api.service;

import com.vinicius.chamados_api.dto.ChamadoRequestDTO;
import com.vinicius.chamados_api.dto.ChamadoResponseDTO;
import com.vinicius.chamados_api.entity.Chamado;
import com.vinicius.chamados_api.entity.Usuario;
import com.vinicius.chamados_api.entity.enums.StatusChamado;
import com.vinicius.chamados_api.repository.ChamadoRepository;
import com.vinicius.chamados_api.repository.UsuarioRepository;
import com.vinicius.chamados_api.service.exceptions.ChamadoInexistenteException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;

    private final UsuarioRepository usuarioRepository;

    public ChamadoService(ChamadoRepository chamadoRepository,  UsuarioRepository usuarioRepository) {
        this.chamadoRepository = chamadoRepository;
        this.usuarioRepository = usuarioRepository;
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

    public ChamadoResponseDTO criar(ChamadoRequestDTO chamadoRequestDTO, String nomeUsuario) {
        Usuario usuario = usuarioRepository.findByNome(nomeUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado: " + nomeUsuario));
        Chamado chamado = new Chamado(chamadoRequestDTO.titulo(), StatusChamado.RECEBIDO, usuario);
        Chamado chamadoSalvo = chamadoRepository.save(chamado);

        return converterParaDTO(chamadoSalvo);
    }

    public void deletarPorId(Long id) {
        if (!chamadoRepository.existsById(id)) {
            throw new ChamadoInexistenteException(id);
        }
        chamadoRepository.deleteById(id);
    }
}

