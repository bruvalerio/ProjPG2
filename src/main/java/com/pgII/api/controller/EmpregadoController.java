package com.pgII.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pgII.api.model.Empregado;
import com.pgII.api.repository.EmpregadoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/empregados")
public class EmpregadoController {

    @Autowired
    private EmpregadoRepository empregadoRepository;

    @PostMapping
    public Empregado adicionarEmpregado(@RequestBody Empregado empregado) {
        return empregadoRepository.save(empregado);
    }

    @GetMapping
    public List<Empregado> listarEmpregados() {
        return empregadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empregado> buscarEmpregadoPorId(@PathVariable Long id) {
        return empregadoRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empregado> atualizarEmpregado(@PathVariable Long id, @RequestBody Empregado empregadoDetalhes) {
        return empregadoRepository.findById(id)
            .map(empregado -> {
                empregado.setNome(empregadoDetalhes.getNome());
                empregado.setCargo(empregadoDetalhes.getCargo());
                empregado.setSalario(empregadoDetalhes.getSalario());
                return ResponseEntity.ok(empregadoRepository.save(empregado));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEmpregado(@PathVariable Long id) {
        return empregadoRepository.findById(id)
            .map(empregado -> {
                empregadoRepository.delete(empregado);
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
