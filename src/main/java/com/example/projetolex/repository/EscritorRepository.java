package com.example.projetolex.repository;

import com.example.projetolex.domain.Escritor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EscritorRepository extends JpaRepository<Escritor, Integer> {

    @Query("SELECT e FROM Escritor e WHERE LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Escritor> findByNomeContainingIgnoreCase(@Param("nome") String nome);

}
