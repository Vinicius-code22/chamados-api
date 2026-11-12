package com.vinicius.chamados_api.config;

import com.vinicius.chamados_api.entity.Usuario;
import com.vinicius.chamados_api.entity.enums.Cargo;
import com.vinicius.chamados_api.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder =   passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        criarSeNaoExistir("admin", "vini123", Cargo.ADMIN);
        criarSeNaoExistir("cliente", "vini456", Cargo.CLIENTE);
    }

    private void criarSeNaoExistir(String nome, String senha, Cargo cargo) {
        if (!this.usuarioRepository.existsByNome(nome)) {
            String senhaCriptografada =  this.passwordEncoder.encode(senha);
            this.usuarioRepository.save(new Usuario(nome, senhaCriptografada, cargo));
        }
    }
}
