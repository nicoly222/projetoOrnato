package com.ifsp.projetoOrnato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User cadastrar(User user) throws Exception {

        // Verifica se já existe alguém com esse email
        User existente = userRepository.findByEmail(user.getEmail());
        if (existente != null) {
            throw new Exception("Email já cadastrado.");
        }

        return userRepository.save(user);
    }
}