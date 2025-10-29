package com.ifsp.projetoOrnato;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<cliente, Long> {
    cliente findByEmail(String email);
}
