package com.taurusmagister.taurusmagister.controller;

import com.taurusmagister.taurusmagister.entidade.Mensagem;
import com.taurusmagister.taurusmagister.entidade.UsuarioBasico;
import com.taurusmagister.taurusmagister.repositorio.MensagemRepository;
import com.taurusmagister.taurusmagister.repositorio.UsuarioBasicoRepository;
import com.taurusmagister.taurusmagister.requisicao.MensagemFrontEnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private UsuarioBasicoRepository usuarioBasicoRepository;

    @GetMapping("/{idUsuario}/{idAmigo}")
    public ResponseEntity<List<Mensagem>> getMensagens(@PathVariable int idUsuario, @PathVariable int idAmigo) {
        return ResponseEntity.status(200).body(mensagemRepository.getMensagens(idUsuario, idAmigo));
    }

    @PostMapping
    public ResponseEntity<Void> postMensagem(@RequestBody MensagemFrontEnd mensagem) {
        Mensagem mensagem1 = new Mensagem(mensagem);
        mensagem1.setFkUsuarioMentor(usuarioBasicoRepository.getById(mensagem.getFkUsuarioMentor().getIdUsuario()));
        mensagem1.setFkUsuarioMentorado(usuarioBasicoRepository.getById((mensagem.getGetFkUsuarioMentorado().getIdUsuario())));

        mensagemRepository.save(mensagem1);
        return ResponseEntity.status(200).build();
    }

}
