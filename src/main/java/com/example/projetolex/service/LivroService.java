package com.example.projetolex.service;

import com.example.projetolex.domain.Livro;
import com.example.projetolex.domain.LivroDTO;
import com.example.projetolex.execption.DadoInvalidoException;
import com.example.projetolex.repository.EscritorRepository;
import com.example.projetolex.repository.LivroRepository;
import com.example.projetolex.util.ValidadorDados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final EscritorRepository escritorRepository;

    public Livro adicionarLivro(LivroDTO livro) {
        validarDadosLivro(livro);
        Livro livroNovo = new Livro();
        livroNovo.setTitulo(livro.getTitulo());
        livroNovo.setPreco(livro.getPreco());
        livroNovo.setAnoPublicacao(livro.getAnoPublicacao());
        livroNovo.setEscritor(escritorRepository.findById(livro.getEscritor()).orElseThrow(() -> new DadoInvalidoException("Escritor não encontrado.")));
        livroNovo.setEditora(livro.getEditora());

        salvarLivro(livroNovo);
        return livroNovo;
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

    public void salvarLivro(Livro livro) {
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

    public void validarDadosLivro(LivroDTO livro) {
        if (livro == null ||
                livro.getTitulo() == null || livro.getTitulo().trim().isEmpty() ||
                livro.getPreco() == null ||
                livro.getAnoPublicacao() == null) {
            throw new IllegalArgumentException("Erro ao cadastrar, objeto não preenchido.");
        }
        if (!ValidadorDados.validarNumeroDouble(livro.getPreco())) {
            throw new DadoInvalidoException("Erro ao cadastrar, preço inválido.");
        }
        if (!ValidadorDados.validarAnoPublicacao(livro.getAnoPublicacao())) {
            throw new DadoInvalidoException("Erro ao cadastrar, ano de publicação inválido.");
        }
        if (!ValidadorDados.validarNome(livro.getTitulo())) {
            throw new DadoInvalidoException("Erro ao cadastrar, título inválido.");
        }

        if (livro.getEscritor() == 0) {
            throw new DadoInvalidoException("Erro ao cadastrar, escritor inválido.");
        }

    }
}
