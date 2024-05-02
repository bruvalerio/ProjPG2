package com.pgII.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pgII.api.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
    
}
