package com.example.projetolex.testesUnitarios;

import com.example.projetolex.domain.Livro;
import com.example.projetolex.domain.Escritor;
import com.example.projetolex.domain.LivroDTO;
import com.example.projetolex.execption.DadoInvalidoException;
import com.example.projetolex.repository.LivroRepository;
import com.example.projetolex.repository.EscritorRepository;
import com.example.projetolex.service.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LivroServiceTest {

    private LivroRepository livroRepository;
    private EscritorRepository escritorRepository;
    private LivroService livroService;

    @BeforeEach
    void setUp() {
        livroRepository = mock(LivroRepository.class);
        escritorRepository = mock(EscritorRepository.class);
        livroService = new LivroService(livroRepository, escritorRepository);
    }

    @Test
    void deveAdicionarLivroValido() {
        LivroDTO dto = new LivroDTO();
        dto.setTitulo("Dom Casmurro");
        dto.setPreco(39.90);
        dto.setEditora("Editora Brasileira");
        dto.setAnoPublicacao(1899);
        dto.setEscritor(1);

        Escritor escritor = new Escritor();
        escritor.setId(1);

        when(escritorRepository.findById(1)).thenReturn(java.util.Optional.of(escritor));
        when(livroRepository.findByTituloContainingIgnoreCase(anyString())).thenReturn(null);

        Livro salvo = livroService.adicionarLivro(dto);

        assertEquals("Dom Casmurro", salvo.getTitulo());
        verify(livroRepository, times(1)).save(any(Livro.class));
    }

    @Test
    void deveLancarExcecaoQuandoTituloJaCadastrado() {
        LivroDTO dto = new LivroDTO();
        dto.setTitulo("Dom Casmurro");
        dto.setPreco(39.90);
        dto.setEditora("Editora Brasileira");
        dto.setAnoPublicacao(1899);
        dto.setEscritor(1);

        when(livroRepository.findByTituloContainingIgnoreCase(anyString())).thenReturn((List<Livro>) new Livro());

        assertThrows(DadoInvalidoException.class, () -> livroService.adicionarLivro(dto));
    }
}