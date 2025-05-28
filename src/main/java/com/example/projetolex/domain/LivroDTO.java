package com.example.projetolex.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDTO {

    private int id;
    private String titulo;
    private int escritor;
    private Double preco;
    private String editora;
    private Integer anoPublicacao;
}
