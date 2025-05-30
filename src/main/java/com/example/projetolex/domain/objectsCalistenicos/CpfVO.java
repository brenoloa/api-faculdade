package com.example.projetolex.domain.objectsCalistenicos;

import com.example.projetolex.execption.DadoInvalidoException;
import com.example.projetolex.repository.EscritorRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CpfVO {

    private final EscritorRepository repository;
    private String cpf;

    public CpfVO(EscritorRepository repository, String cpf) {
        this.repository = repository;
        if (!validarCpf(cpf, false)) {
            throw new DadoInvalidoException("CPF inválido: " + cpf);
        }
        if (repository.findByCpf(cpf) != null) {
            throw new DadoInvalidoException("CPF já cadastrado.");
        }
        this.cpf = cpf;
    }


    public static boolean validarCpf(String cpf, boolean validarReal) {
        if (cpf == null) {
            return false;
        }
        cpf = cpf.replaceAll("[\\.\\-]", "");
        if (!cpf.matches("\\d{11}")) {
            return false;
        }
        if (!validarReal) {
            return true;
        }

        int soma = 0, resto;
        for (int i = 1; i <= 9; i++) {
            soma += Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) {
            resto = 0;
        }
        if (resto != Integer.parseInt(cpf.substring(9, 10))) {
            return false;
        }
        soma = 0;
        for (int i = 1; i <= 10; i++) {
            soma += Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) {
            resto = 0;
        }
        return resto == Integer.parseInt(cpf.substring(10, 11));
    }

}
