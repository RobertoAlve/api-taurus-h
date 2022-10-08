package com.taurusmagister.taurusmagister.controller;

import com.taurusmagister.taurusmagister.entidade.NotificationListener;
import com.taurusmagister.taurusmagister.entidade.Pilha;
import com.taurusmagister.taurusmagister.entidade.Publicacao;
import com.taurusmagister.taurusmagister.entidade.UsuarioBasico;
import com.taurusmagister.taurusmagister.enums.ANDAMENTO;
import com.taurusmagister.taurusmagister.repositorio.PublicacaoRepository;
import com.taurusmagister.taurusmagister.repositorio.TransacaoRepository;
import com.taurusmagister.taurusmagister.requisicao.PublicacaoFrontEnd;
import com.taurusmagister.taurusmagister.resposta.ConsultaUsuariosAjudados;
import com.taurusmagister.taurusmagister.resposta.PublicacaoFront;
import com.taurusmagister.taurusmagister.resposta.PublicacaoFrontEmAndamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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

    @GetMapping("/emandamento/{idUsuario}")
    public ResponseEntity<List<PublicacaoFrontEmAndamento>> publicacoesEmAndamento(@PathVariable int idUsuario) {
        return ResponseEntity.status(200).body(publicacaoRepository.publicacoesEmAndamento(idUsuario));
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

    @PatchMapping("/finalizar/{idPublicacao}")
    public ResponseEntity finalizarPublicacao(@PathVariable int idPublicacao) {
        publicacaoRepository.finalizarPublicacao(idPublicacao);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/usuariosAjudados/{idUsuario}")
    public ResponseEntity<List<ConsultaUsuariosAjudados>> getUsuariosAjudados(@PathVariable int idUsuario) {
        return ResponseEntity.status(200).body(publicacaoRepository.getUsuariosAjudados(idUsuario, PageRequest.of(0, 3)));
    }

    @PatchMapping(value = "/upload/resolucao/{idPublicacao}", consumes = {MediaType.APPLICATION_PDF_VALUE, "application/pdf", "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"})
    public ResponseEntity<Void> pathResolucao(@PathVariable int idPublicacao, @RequestBody MultipartFile resolucao) throws IOException {
        if (!publicacaoRepository.existsById(idPublicacao)) {
            return ResponseEntity.status(404).build();
        }
        Publicacao publicacao = publicacaoRepository.findById(idPublicacao).get();
        publicacao.setResolucao(resolucao.getBytes());
        publicacaoRepository.save(publicacao);

        return ResponseEntity.status(200).build();
    }

    @GetMapping(value = "/download/resolucao/{idPublicacao}", produces = {MediaType.APPLICATION_PDF_VALUE, "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"})
    public ResponseEntity<byte[]> getResolucao(@PathVariable int idPublicacao) {
        if (!publicacaoRepository.existsById(idPublicacao)) {
            return ResponseEntity.status(404).build();
        }
        Publicacao publicacao = publicacaoRepository.findById(idPublicacao).get();
        return ResponseEntity.status(200).body(publicacao.getResolucao());
    }

}
