package com.JavaMiniProject.JavaProject.Controller;

import com.JavaMiniProject.JavaProject.Domain.Jogo;
import com.JavaMiniProject.JavaProject.Service.JogoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    private static final Logger logger = LoggerFactory.getLogger(JogoController.class);


    @PostMapping
    public ResponseEntity<Jogo> createJogo(@RequestBody Jogo jogo) {
        Jogo savedJogo = jogoService.saveJogo(jogo);
        return ResponseEntity.ok(savedJogo);
    }
    @GetMapping
    public ResponseEntity<List<Jogo>> getAllJogos() {
        List<Jogo> jogos = jogoService.getAllJogos();
        return ResponseEntity.ok(jogos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> getJogoById(@PathVariable Long id) {
        Optional<Jogo> jogo = jogoService.getJogoById(id);
        return jogo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{idJogo}/{idJogador}")
    public ResponseEntity<Jogo> updateJogo(@PathVariable Long idJogo,@PathVariable Long idJogador ,@RequestBody Jogo jogoDetails) {
        try {
            Jogo updatedJogo = jogoService.updateJogo(idJogo,idJogador, jogoDetails);
            return ResponseEntity.ok(updatedJogo);
        } catch (RuntimeException e) {
            logger.error("Erro ao atualizar jogo: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJogo(@PathVariable Long id) {
        try {
            jogoService.deleteJogo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
