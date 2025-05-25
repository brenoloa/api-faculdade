package com.example.projetolex.util;

public class ValidadorDados {


    public static boolean validarNome(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    public static boolean validarCpf(String cpf, boolean validarReal) {
        if (cpf == null) return false;
        cpf = cpf.replaceAll("[\\.\\-]", "");
        if (!cpf.matches("\\d{11}")) return false;
        if (!validarReal) return true;
        int soma = 0, resto;
        for (int i = 1; i <= 9; i++)
            soma += Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);
        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) resto = 0;
        if (resto != Integer.parseInt(cpf.substring(9, 10))) return false;
        soma = 0;
        for (int i = 1; i <= 10; i++)
            soma += Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);
        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) resto = 0;
        return resto == Integer.parseInt(cpf.substring(10, 11));
    }

    public static boolean validarEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean validarIdade(Integer idade) {
        return idade != null && idade > 0;
    }
}
