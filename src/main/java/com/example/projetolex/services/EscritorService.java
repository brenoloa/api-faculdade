package com.example.projetolex.services;

import com.example.projetolex.domain.Escritor;
import com.example.projetolex.repository.EscritorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EscritorService {

    @Autowired
    private  EscritorRepository repository;

    public Escritor adicionarEscritor(Escritor escritor) {
        Escritor newEscritor = new Escritor();
        newEscritor.setNome(escritor.getNome());
        newEscritor.setEmail(escritor.getEmail());
        newEscritor.setCpf(escritor.getCpf());
        newEscritor.setIdade(escritor.getIdade());
        repository.save(newEscritor);
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

    public void atualizarEscritor(Escritor escritor) {
        repository.save(escritor);
    }

    public List<Escritor> buscarEscritoresPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }



}
