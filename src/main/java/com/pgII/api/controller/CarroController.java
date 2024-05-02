package com.pgII.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pgII.api.model.Carro;
import com.pgII.api.repository.CarroRepository;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    // POST: Adiciona um novo carro
    @PostMapping
    public Carro adicionarCarro(@RequestBody Carro carro) {
        return carroRepository.save(carro);
    }

    // GET: Lista todos os carros
    @GetMapping
    public List<Carro> listarCarros() {
        return carroRepository.findAll();
    }

    // GET: Busca um carro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarCarroPorId(@PathVariable Long id) {
        return carroRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Atualiza um carro existente
    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizarCarro(@PathVariable Long id, @RequestBody Carro carroDetalhes) {
        return carroRepository.findById(id)
            .map(carro -> {
                carro.setModelo(carroDetalhes.getModelo());
                carro.setMarca(carroDetalhes.getMarca());
                carro.setAno(carroDetalhes.getAno());
                carro.setCategoria(carroDetalhes.getCategoria());
                return ResponseEntity.ok(carroRepository.save(carro));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Deleta um carro
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCarro(@PathVariable Long id) {
        return carroRepository.findById(id)
            .map(carro -> {
                carroRepository.delete(carro);
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
