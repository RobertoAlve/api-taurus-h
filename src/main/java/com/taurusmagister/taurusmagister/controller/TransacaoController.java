package com.taurusmagister.taurusmagister.controller;

import com.taurusmagister.taurusmagister.entidade.Transacao;
import com.taurusmagister.taurusmagister.repositorio.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @PostMapping
    public ResponseEntity adicionaTransacao(@RequestBody Transacao transacao) {
        transacaoRepository.save(transacao);
        return ResponseEntity.status(200).build();
    }

    @GetMapping
    public ResponseEntity exibeTransacoes() {
        List<Transacao> transacoes = transacaoRepository.findAll();
        if (transacoes.size() <= 0) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(transacoes);
        }
    }
}
