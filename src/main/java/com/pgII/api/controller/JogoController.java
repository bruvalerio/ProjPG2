package com.pgII.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pgII.api.model.Jogo;
import com.pgII.api.repository.JogoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {

    @Autowired
    private JogoRepository jogoRepository;

    // POST: Adiciona um novo jogo
    @PostMapping
    public Jogo adicionarJogo(@RequestBody Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    // GET: Lista todos os jogos
    @GetMapping
    public List<Jogo> listarJogos() {
        return jogoRepository.findAll();
    }

    // GET: Busca um jogo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscarJogoPorId(@PathVariable Long id) {
        return jogoRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Atualiza um jogo existente
    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizarJogo(@PathVariable Long id, @RequestBody Jogo jogoDetalhes) {
        return jogoRepository.findById(id)
            .map(jogo -> {
                jogo.setNomeTimeA(jogoDetalhes.getNomeTimeA());
                jogo.setNomeTimeB(jogoDetalhes.getNomeTimeB());
                jogo.setGolsTimeA(jogoDetalhes.getGolsTimeA());
                jogo.setGolsTimeB(jogoDetalhes.getGolsTimeB());
                return ResponseEntity.ok(jogoRepository.save(jogo));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Deleta um jogo
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarJogo(@PathVariable Long id) {
        return jogoRepository.findById(id)
            .map(jogo -> {
                jogoRepository.delete(jogo);
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
