package com.ifsp.projetoOrnato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private AuthService authService;

    // Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        if (authService.autenticar(loginRequest.getEmail(), loginRequest.getSenha())) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        } else {
            return ResponseEntity.status(401).body("Email ou senha incorretos");
        }
    }

    // Endpoint de cadastro
    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrar(@RequestBody CadastroRequest cadastroRequest) {
        if (authService.cadastrar(cadastroRequest.getNome(), cadastroRequest.getEmail(), cadastroRequest.getSenha())) {
            return ResponseEntity.ok("Usuário cadastrado com sucesso!");
        } else {
            return ResponseEntity.status(400).body("Email já cadastrado");
        }
    }
}
