package com.ifsp.projetoOrnato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // MOSTRA a tela de login (GET)
    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro() {
        return "login"; 
    }

    // PROCESSA o formulário (POST)
    @PostMapping("/cadastrar")
    public String formCadastro(
            @RequestParam("nome") String nome,
            @RequestParam("email") String email,
            @RequestParam("senha") String senha
    ) {

            
        try {
            User novo = new User(nome,email,senha);
            userService.cadastrar(novo);
            return "redirect:/telaprincipal";

        } catch (Exception e) {
            return "redirect:/login";
        }
    }
}
