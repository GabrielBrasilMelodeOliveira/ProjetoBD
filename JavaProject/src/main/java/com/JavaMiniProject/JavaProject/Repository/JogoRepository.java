package com.JavaMiniProject.JavaProject.Repository;

import com.JavaMiniProject.JavaProject.Domain.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {
    List<Jogo> findByTituloContaining(String substring);
}
