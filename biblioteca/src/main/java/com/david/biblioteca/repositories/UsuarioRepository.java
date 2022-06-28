package com.david.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.biblioteca.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);
}
