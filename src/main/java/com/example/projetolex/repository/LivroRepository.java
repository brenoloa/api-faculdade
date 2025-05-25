package com.example.projetolex.repository;

import com.example.projetolex.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    @Query("SELECT l FROM Livro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Livro> findByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    @Query("SELECT l FROM Livro l WHERE LOWER(l.escritor) LIKE LOWER(CONCAT('%', :autor, '%'))")
    List<Livro> findByAutorContainingIgnoreCase(@Param("autor") String autor);

    @Query("SELECT l FROM Livro l WHERE LOWER(l.editora) LIKE LOWER(CONCAT('%', :editora, '%'))")
    List<Livro> findByEditoraContainingIgnoreCase(@Param("editora") String editora);

    @Query("SELECT l FROM Livro l WHERE CAST(l.anoPublicacao AS string) LIKE %:anoPublicacao%")
    List<Livro> findByAnoPublicacaoContainingIgnoreCase(@Param("anoPublicacao") String anoPublicacao);

    @Query("SELECT l FROM Livro l WHERE l.preco = :preco")
    List<Livro> findByPreco(@Param("preco") Double preco);
}
