package com.example.projetolex.services;

import com.example.projetolex.domain.Escritor;
import com.example.projetolex.execption.DadoInvalidoException;
import com.example.projetolex.repository.EscritorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.projetolex.util.ValidadorDados.validarCpf;
import static com.example.projetolex.util.ValidadorDados.validarEmail;
import static com.example.projetolex.util.ValidadorDados.validarIdade;
import static com.example.projetolex.util.ValidadorDados.validarNome;

@RequiredArgsConstructor
@Service
public class EscritorService {

    @Autowired
    private EscritorRepository repository;

    public Escritor adicionarEscritor(Escritor escritor) {
        validarDadosEscritor(escritor);
        salvarEscritor(escritor);
        return escritor;
    }

    public void removerEscritor(int id) {
        repository.deleteById(id);
    }

    public Escritor buscarEscritorPorId(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Escritor> listarEscritores() {
        return repository.findAll();
    }

    public void salvarEscritor(Escritor escritor) {
        repository.save(escritor);
    }

    public List<Escritor> buscarEscritoresPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public void validarDadosEscritor(Escritor escritor) {
        if (!validarNome(escritor.getNome())) {
            throw new DadoInvalidoException("Nome inválido.");
        }
        if (!validarCpf(escritor.getCpf(), true)) {
            throw new DadoInvalidoException("CPF inválido.");
        }
        if (!validarEmail(escritor.getEmail())) {
            throw new DadoInvalidoException("E-mail inválido.");
        }
        if (!validarIdade(escritor.getIdade())) {
            throw new DadoInvalidoException("Idade inválida.");
        }
        if (repository.findByCpf(escritor.getCpf()) != null) {
            throw new DadoInvalidoException("CPF já cadastrado.");
        }
        if (repository.findByEmail(escritor.getEmail()) != null) {
            throw new DadoInvalidoException("E-mail já cadastrado.");
        }
    }

}
