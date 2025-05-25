package com.example.projetolex.Controller;

import com.example.projetolex.domain.Escritor;
import com.example.projetolex.domain.Livro;
import com.example.projetolex.services.EscritorService;
import com.example.projetolex.services.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/biblioteca")
public class BibliotecaController {

    @Autowired
    private EscritorService escritorService;

    @Autowired
    private LivroService livroService;


    // ===================================================================================

    @GetMapping("/listar/escritor")
    public List<Escritor> cadastroEscritor() {
        return escritorService.listarEscritores();
    }

    @GetMapping("/listar/livro")
    public List<Livro> cadastroLivro() {
        return livroService.listarLivros();
    }

    @GetMapping("/listar/escritor/{id}")
    public Escritor buscarEscritorPorId(@PathVariable int id) {
        return escritorService.buscarEscritorPorId(id);
    }

    @GetMapping("/listar/livro/{id}")
    public Livro buscarLivroPorId(@PathVariable int id) {
        return livroService.buscarLivroPorId(id);
    }

    @GetMapping("/listar/escritor/nome/{nome}")
    public List<Escritor> buscarEscritoresPorNome(@PathVariable String nome) {
        return escritorService.buscarEscritoresPorNome(nome);
    }

    @GetMapping("/listar/livro/titulo/{titulo}")
    public List<Livro> buscarLivrosPorTitulo(@PathVariable String titulo) {
        return livroService.buscarLivrosPorTitulo(titulo);
    }

    @GetMapping("/listar/livro/autor/{autor}")
    public List<Livro> buscarLivrosPorAutor(@PathVariable String autor) {
        return livroService.buscarLivrosPorAutor(autor);
    }

    @GetMapping("/listar/livro/editora/{editora}")
    public List<Livro> buscarLivrosPorEditora(@PathVariable String editora) {
        return livroService.buscarLivrosPorEditora(editora);
    }

    @GetMapping("/listar/livro/anoPublicacao/{anoPublicacao}")
    public List<Livro> buscarLivrosPorAnoPublicacao(@PathVariable String anoPublicacao) {
        return livroService.buscarLivrosPorAnoPublicacao(anoPublicacao);
    }

    @GetMapping("/listar/livro/preco/{preco}")
    public List<Livro> buscarLivrosPorPreco(@PathVariable Double preco) {
        return livroService.buscarLivrosPorPreco(preco);
    }


    // ===================================================================================

    @PostMapping("/adicionar/escritor")
    public Escritor adicionarEscritor(@RequestBody Escritor escritor) {
        return escritorService.adicionarEscritor(escritor);
    }

    @PostMapping("/adicionar/livro")
    public void adicionarLivro(@RequestBody Livro livro) {
        livroService.adicionarLivro(livro);
    }

    @DeleteMapping("/remover/escritor/{id}")
    public void removerEscritor(@PathVariable int id) {
        escritorService.removerEscritor(id);
    }
    @DeleteMapping("/remover/livro/{id}")
    public void removerLivro(@PathVariable int id) {
        livroService.removerLivro(id);
    }

//    @PutMapping("/atualizar/escritor/{id}")
//    public void atualizarEscritorPorId(@PathVariable int id, Escritor escritor) {
//        escritor.setId(id);
//        escritorService.atualizarEscritor(escritor);
//    }
//    @PutMapping("/atualizar/livro/{id}")
//    public void atualizarLivroPorId(@PathVariable int id, Livro livro) {
//        livro.setI
//        livroService.atualizarLivro(livro);
//    }

}
