package com.example.projetolex.services;

import com.example.projetolex.domain.Livro;
import com.example.projetolex.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LivroService {

    private LivroRepository livroRepository;

    // Implementação de métodos para manipulação de livros
    public void adicionarLivro(Livro livro) {
        livroRepository.save(livro);
    }
    public void removerLivro(int id) {
        livroRepository.deleteById(id);
    }
    public Livro buscarLivroPorId(int id) {
        return livroRepository.findById(id).orElse(null);
    }
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }
    public void atualizarLivro(Livro livro) {
        livroRepository.save(livro);
    }
    public List<Livro> buscarLivrosPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }
    public List<Livro> buscarLivrosPorAutor(String autor) {
        return livroRepository.findByAutorContainingIgnoreCase(autor);
    }
    public List<Livro> buscarLivrosPorEditora(String editora) {
        return livroRepository.findByEditoraContainingIgnoreCase(editora);
    }
    public List<Livro> buscarLivrosPorAnoPublicacao(String anoPublicacao) {
        return livroRepository.findByAnoPublicacaoContainingIgnoreCase(anoPublicacao);
    }
    public List<Livro> buscarLivrosPorPreco(Double preco) {
        return livroRepository.findByPreco(preco);
    }

}
