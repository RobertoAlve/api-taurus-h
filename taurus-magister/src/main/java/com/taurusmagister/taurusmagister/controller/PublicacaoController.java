package com.taurusmagister.taurusmagister.controller;

import com.taurusmagister.taurusmagister.entidade.NotificationListener;
import com.taurusmagister.taurusmagister.entidade.Pilha;
import com.taurusmagister.taurusmagister.entidade.Publicacao;
import com.taurusmagister.taurusmagister.repositorio.PublicacaoRepository;
import com.taurusmagister.taurusmagister.repositorio.TransacaoRepository;
import com.taurusmagister.taurusmagister.requisicao.PublicacaoFrontEnd;
import com.taurusmagister.taurusmagister.resposta.PublicacaoFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    private final NotificationListener events = new NotificationListener("Nova publicação em andamento", "#fff");

    @PostMapping
    public ResponseEntity adicionaPublicacao(@RequestBody PublicacaoFrontEnd publicacaoF) {

        Publicacao publicacao = new Publicacao(publicacaoF);
        publicacao.setFkUsuario(publicacaoF.getIdUsuario());
        publicacaoRepository.save(publicacao);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity exibePublicacoes() {
        List<PublicacaoFront> publicacoes = publicacaoRepository.getPublicacoes();
        Pilha<PublicacaoFront> publicacaoPilha = new Pilha<>(publicacoes.size());
        if (publicacoes.size() <= 0) {
            return ResponseEntity.status(204).build();
        } else {
            for (int i=publicacoes.size() - 1; i >= 0; i--){
                publicacaoPilha.push(publicacoes.get(i));
            }
            Object[] publicacao = publicacaoPilha.getPilha();
            return ResponseEntity.status(200).body(publicacao);
        }
    }

    @PatchMapping("/andamento/{idPublicacao}")
    public ResponseEntity updateStatusPublicacao(
            @PathVariable int idPublicacao
    ) {
        if (publicacaoRepository.existsById(idPublicacao)) {
            events.addObservable(publicacaoRepository.getById(idPublicacao));
            events.setChanged();
            events.notifyObservers(publicacaoRepository);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

}
