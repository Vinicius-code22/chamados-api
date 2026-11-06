package com.vinicius.chamados_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("admin123")).roles("ADMIN").build();
        UserDetails cliente = User.withUsername("cliente").password(passwordEncoder.encode("cliente123")).roles("CLIENTE").build();
        return new InMemoryUserDetailsManager(admin, cliente);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/chamados").hasAnyRole("ADMIN", "CLIENTE")
                .requestMatchers(HttpMethod.GET, "/chamados/{id}").hasAnyRole("ADMIN", "CLIENTE")
                .anyRequest().hasRole("ADMIN")
    )
    .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
