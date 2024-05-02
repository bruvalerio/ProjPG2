package com.pgII.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pgII.api.model.Empregado;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {
    
}
