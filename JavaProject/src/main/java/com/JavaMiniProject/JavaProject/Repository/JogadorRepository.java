package com.JavaMiniProject.JavaProject.Repository;


import com.JavaMiniProject.JavaProject.Domain.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    @Query("SELECT j FROM Jogador j WHERE j.email LIKE %:email%")
    List<Jogador> findByEmail(@Param("email") String email);

}
