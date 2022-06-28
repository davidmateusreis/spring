package com.david.biblioteca.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.david.biblioteca.models.Papel;
import com.david.biblioteca.models.Usuario;
import com.david.biblioteca.repositories.UsuarioRepository;

@Component
public class LoginSucesso extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        String login = authentication.getName();

        Usuario usuario = usuarioRepository.findByLogin(login);

        String redirectURL = "";
        if (temAutorizacao(usuario, "ADMIN")) {
            redirectURL = "/auth/admin/admin-index";
        } else if (temAutorizacao(usuario, "USER")) {
            redirectURL = "/auth/user/user-index";
        } else if (temAutorizacao(usuario, "BIBLIOTECARIO")) {
            redirectURL = "/auth/biblio/biblio-index";
        }
        response.sendRedirect(redirectURL);
    }

    private boolean temAutorizacao(Usuario usuario, String papel) {
        for (Papel pp : usuario.getPapeis()) {
            if (pp.getPapel().equals(papel)) {
                return true;
            }
        }
        return false;
    }
}