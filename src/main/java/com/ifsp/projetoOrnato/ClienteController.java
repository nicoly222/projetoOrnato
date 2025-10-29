package com.ifsp.projetoOrnato;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class ClienteController {

    private final ClienteRepository clienteRepo;

    public ClienteController(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String autenticar(@RequestParam String email, @RequestParam String senha) {
        cliente cliente = clienteRepo.findByEmail(email);

        if (cliente != null && cliente.getSenha().equals(senha)) {
            return "redirect:/";
        }

        return "redirect:/login?erro";
    }
}
