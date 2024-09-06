package com.JavaMiniProject.JavaProject.Service;

import com.JavaMiniProject.JavaProject.Domain.Jogador;
import com.JavaMiniProject.JavaProject.Domain.Jogo;
import com.JavaMiniProject.JavaProject.Repository.JogadorRepository;
import com.JavaMiniProject.JavaProject.Repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    public Jogo saveJogo(Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    public List<Jogo> getAllJogos() {
        return jogoRepository.findAll();
    }

    public Optional<Jogo> getJogoById(Long id) {
        return jogoRepository.findById(id);
    }

    public Jogo updateJogo(Long idJogo,Long idJogador, Jogo jogoDetails) {
        Optional<Jogo> jogo = jogoRepository.findById(idJogo);
        if (jogo.isPresent()) {
            Jogo existingJogo = jogo.get();
            existingJogo.setTitulo(jogoDetails.getTitulo());
            existingJogo.setGenero(jogoDetails.getGenero());
            Optional<Jogador> jogador = jogadorRepository.findById(idJogador);
            if (jogador.isPresent()){
                existingJogo.setJogador(jogador.get());
            }

            return jogoRepository.save(existingJogo);
        } else {
            throw new RuntimeException("Jogo não encontrado com ID: " + idJogo);
        }
    }


    public void deleteJogo(Long id) {
        if (jogoRepository.existsById(id)) {
            jogoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Jogo não encontrado " + id);
        }
    }
}
