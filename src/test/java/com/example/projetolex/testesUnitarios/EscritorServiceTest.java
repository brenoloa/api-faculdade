package com.example.projetolex.testesUnitarios;

import com.example.projetolex.domain.Escritor;
import com.example.projetolex.execption.DadoInvalidoException;
import com.example.projetolex.repository.EscritorRepository;
import com.example.projetolex.service.EscritorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EscritorServiceTest {

    private EscritorRepository repository;
    private EscritorService service;

    @BeforeEach
    void setUp() {
        repository = mock(EscritorRepository.class);
        service = new EscritorService(repository);
    }

    @Test
    void deveAdicionarEscritorValido() {
        Escritor escritor = new Escritor();
        escritor.setNome("Nome Teste");
        escritor.setCpf("123.456.789-00");
        escritor.setEmail("teste@email.com");
        escritor.setIdade(30);

        when(repository.findByCpf(anyString())).thenReturn(null);
        when(repository.findByEmail(anyString())).thenReturn(null);

        Escritor salvo = service.adicionarEscritor(escritor);

        assertEquals("Nome Teste", salvo.getNome());
        verify(repository, times(1)).save(escritor);
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaCadastrado() {
        Escritor escritor = new Escritor();
        escritor.setNome("Nome Teste");
        escritor.setCpf("123.456.789-00");
        escritor.setEmail("teste@email.com");
        escritor.setIdade(30);

        when(repository.findByCpf(anyString())).thenReturn(new Escritor());

        assertThrows(DadoInvalidoException.class, () -> service.adicionarEscritor(escritor));
    }
}