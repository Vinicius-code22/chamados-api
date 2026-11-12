package com.vinicius.chamados_api.service;

import com.vinicius.chamados_api.entity.Usuario;
import com.vinicius.chamados_api.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        UserDetails user = User.withUsername(usuario.getNome())
                .password(usuario.getSenha())
                .roles(usuario.getCargo().toString())
                .build();

       return user;
    }

}
