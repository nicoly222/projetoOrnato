package com.ifsp.projetoOrnato;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/login")
public class ClienteController {

    private final ClienteRepository usuarioRepo;

    public ClienteController(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepoRepo;
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String autenticar(@RequestParam String email, @RequestParam String senha) {
        Cliente cliente = clienteRepo.findByEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return "redirect:/";
        }
        return "redirect:/login?erro";
    }
}
