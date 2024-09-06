package com.JavaMiniProject.JavaProject.Service;

import com.JavaMiniProject.JavaProject.Domain.Jogador;
import com.JavaMiniProject.JavaProject.Repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    public Jogador adicionarJogador(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    public List<Jogador> adicionarJogadores(List<Jogador> jogadores) {
        List<Jogador> adicionados = new ArrayList<>();
        for(Jogador el: jogadores){
            jogadorRepository.save(el);
            adicionados.add(el);
        }
        return adicionados;
    }

    public void removerJogador(Long id) {
        jogadorRepository.deleteById(id);
    }

    public Jogador atualizarJogador(Long id, Jogador novoJogador) {
        Jogador jogadorExistente = jogadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        jogadorExistente.setNome(novoJogador.getNome());
        jogadorExistente.setEmail(novoJogador.getEmail());
        return jogadorRepository.save(jogadorExistente);
    }

    public Optional<Jogador> buscarJogadoresPorId(Long id) {
        return jogadorRepository.findById(id);
    }


    public List<Jogador> buscarJogadoresPorEmail(String email){
        List<Jogador> jogadores = jogadorRepository.findByEmail(email);
        return jogadores;
    }
}

