package com.pgII.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pgII.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}