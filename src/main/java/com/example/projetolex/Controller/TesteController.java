package com.example.projetolex.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping
    public String teste() {
        return "Teste de conexão com o backend";
    }
}
