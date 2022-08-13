package com.taurusmagister.taurusmagister.controller;

import com.taurusmagister.taurusmagister.repositorio.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/engajamento")
public class DashboardController {

    public int[] engajamento = new int[10];
    int qtdDados = 0;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @GetMapping("/contagem")
    public ResponseEntity adicionaPublicacao() {
        if (qtdDados > 10) {
            return ResponseEntity.status(400).build();
        }
        engajamento[qtdDados] = publicacaoRepository.contagem();
        qtdDados++;
        return ResponseEntity.status(200).build();
    }

    @GetMapping
    public ResponseEntity exibeDadosEngajamento() {
        if (engajamento.length <= 0) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(engajamento);
        }
    }
}
