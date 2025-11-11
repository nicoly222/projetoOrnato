package com.ifsp.projetoOrnato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setNome(userDetails.getNome());
            user.setEmail(userDetails.getEmail());
            user.setSenha(userDetails.getSenha());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User login(String email, String senha) {
        User user = userRepository.findById(email);
        if (user != null && user.getSenha().equals(senha)) {
            return user;
        }
        return null;
    }
}