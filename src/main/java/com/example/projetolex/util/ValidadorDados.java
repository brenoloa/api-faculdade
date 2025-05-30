package com.example.projetolex.util;

public class ValidadorDados {


    public static boolean validarNome(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }




    public static boolean validarNumero(Integer idade) {
        return idade != null && idade > 0;
    }

    public static boolean validarNumeroDouble(Double preco) {
        return preco != null && preco > 0;
    }

    public static boolean validarAnoPublicacao(Integer anoPublicacao) {
        int anoAtual = java.time.Year.now().getValue();
        return anoPublicacao != null && anoPublicacao >= 1600 && anoPublicacao <= anoAtual;
    }


}
