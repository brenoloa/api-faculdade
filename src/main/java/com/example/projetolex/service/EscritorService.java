package com.example.projetolex.service;

import com.example.projetolex.domain.Escritor;
import com.example.projetolex.domain.EscritorDTO;
import com.example.projetolex.domain.objectsCalistenicos.CpfVO;
import com.example.projetolex.domain.objectsCalistenicos.EmailVO;
import com.example.projetolex.execption.DadoInvalidoException;
import com.example.projetolex.repository.EscritorRepository;
import com.example.projetolex.util.ValidadorDados;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.projetolex.util.ValidadorDados.validarNome;
import static com.example.projetolex.util.ValidadorDados.validarNumero;
@Getter
@Setter
@RequiredArgsConstructor
@Service
public class EscritorService {

    private CpfVO cpf;
    private EmailVO email;
    private final EscritorRepository repository;

    public Escritor adicionarEscritor(Escritor escritor) {
        validarDadosEscritor(escritor);
        salvarEscritor(escritor);
        return escritor;
    }

    public Escritor editarEscritor(EscritorDTO dto) {
        Escritor escritor = buscarEscritorPorId(dto.getId());
        if (escritor == null) throw new DadoInvalidoException("Escritor não encontrado.");
        if (dto.getCpf() != null) throw new DadoInvalidoException("CPF não pode ser alterado.");
        if (dto.getNome() != null && !validarNome(dto.getNome()) && dto.getNome() != null) throw new DadoInvalidoException("Nome inválido.");
        if (dto.getIdade() != null && !validarNumero(dto.getIdade())) throw new DadoInvalidoException("Idade inválida.");

        escritor.setNome(dto.getNome());
        escritor.setIdade(dto.getIdade());
        salvarEscritor(escritor);
        return escritor;
    }

    public String removerEscritor(int id) {
        if (buscarEscritorPorId(id) == null) throw new DadoInvalidoException("Escritor não encontrado.");
        repository.deleteById(id);
        return "Escritor removido com sucesso!";
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
        if (escritor == null || escritor.getNome() == null || escritor.getCpf() == null || escritor.getEmail() == null || escritor.getIdade() == null) {
            throw new DadoInvalidoException("Objeto não preenchido corretamente.");
        }

        if (!validarNome(escritor.getNome())) {
            throw new DadoInvalidoException("Nome inválido.");
        }
        cpf = new CpfVO(repository, escritor.getCpf());
        email = new EmailVO(repository, escritor.getEmail());
        if (!validarNumero(escritor.getIdade())) {
            throw new DadoInvalidoException("Idade inválida.");
        }

    }
}
