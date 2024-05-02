package com.pgII.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pgII.api.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    
}