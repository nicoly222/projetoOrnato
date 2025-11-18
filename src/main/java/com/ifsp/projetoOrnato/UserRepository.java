package com.ifsp.projetoOrnato;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Vai ser útil depois para o login
    User findByEmail(String email);
}

