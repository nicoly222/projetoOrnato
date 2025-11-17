package com.ifsp.projetoOrnato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// A interface Repository extende JpaRepository para herdar todos os métodos CRUD
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}