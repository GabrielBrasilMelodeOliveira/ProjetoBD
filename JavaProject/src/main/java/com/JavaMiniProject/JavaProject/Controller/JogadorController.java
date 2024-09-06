package com.JavaMiniProject.JavaProject.Controller;

import com.JavaMiniProject.JavaProject.Domain.Jogador;
import com.JavaMiniProject.JavaProject.Service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jogadores")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @PostMapping
    public ResponseEntity<Jogador> adicionarJogador(@RequestBody Jogador jogador) {
        return new ResponseEntity<>(jogadorService.adicionarJogador(jogador), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerJogador(@PathVariable Long id) {
        jogadorService.removerJogador(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizarJogador(@PathVariable Long id, @RequestBody Jogador jogador) {
        return new ResponseEntity<>(jogadorService.atualizarJogador(id, jogador), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarJogadoresPorId(@PathVariable Long id) {
        Optional<Jogador> jogador = jogadorService.buscarJogadoresPorId(id);
        if (!jogador.isPresent()){
            throw new RuntimeException();
        }
        return new ResponseEntity<>(jogador.get(), HttpStatus.OK);
    }

    @GetMapping("/procurar")
    public ResponseEntity<List<Jogador>> buscarJogadoresPorEmail(@RequestParam String email) {
        List<Jogador> jogadores = jogadorService.buscarJogadoresPorEmail(email);
        return new ResponseEntity<>(jogadores, HttpStatus.OK);
    }

    @PostMapping("/criar/jogadores")
    public ResponseEntity<List<Jogador>> adicionarJogadores(@RequestBody List<Jogador> jogadores) {
        return new ResponseEntity<>(jogadorService.adicionarJogadores(jogadores), HttpStatus.CREATED);
    }

}

