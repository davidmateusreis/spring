package com.david.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("msgBemVindo", "Bem-vindo à Biblioteca");
        return "publica-index";
    }

    @RequestMapping("/login") 
	public String login() {
		return "login";
	}
}
