package com.example.projetolex.domain.objectsCalistenicos;

import com.example.projetolex.execption.DadoInvalidoException;
import com.example.projetolex.repository.EscritorRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class EmailVO {
    private final EscritorRepository escritorRepository;
    private String email;


    public EmailVO(EscritorRepository repository, String email) {
        this.escritorRepository = repository;
        if (!validarEmail(email)) {
            throw new DadoInvalidoException("Email inválido: " + email);
        }
        if (repository.findByEmail(email) != null) {
            throw new DadoInvalidoException("E-mail já cadastrado: " + email);
        }
        this.email = email;
    }

    public static boolean validarEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}
