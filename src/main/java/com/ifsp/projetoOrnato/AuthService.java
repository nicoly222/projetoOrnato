package com.ifsp.projetoOrnato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
     @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Cadastro de usuário
    public boolean cadastrar(String nome, String email, String senha) {
        // Verifica se o email já está registrado
        if (userRepository.findByEmail(email).isPresent()) {
            return false; // Já existe um usuário com esse email
        }

        // Criptografando a senha
        String senhaCriptografada = passwordEncoder.encode(senha);

        // Cria o novo usuário
        User user = new User();
        user.setNome(nome);
        user.setEmail(email);
        user.setSenha(senhaCriptografada); // Armazena a senha criptografada

        userRepository.save(user);
        return true;
    }

    // Autenticação de usuário
    public boolean autenticar(String email, String senha) {
        // Busca o usuário pelo email
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            // Verifica se a senha fornecida bate com a senha criptografada no banco
            return passwordEncoder.matches(senha, user.get().getSenha());
        }
        return false; // Email não encontrado
    }
}
    
