package com.taurusmagister.taurusmagister.controller;

import com.taurusmagister.taurusmagister.entidade.Chat;
import com.taurusmagister.taurusmagister.entidade.FilaString;
import com.taurusmagister.taurusmagister.entidade.Mensagem;
import com.taurusmagister.taurusmagister.entidade.UsuarioBasico;
import com.taurusmagister.taurusmagister.repositorio.ChatRepository;
import com.taurusmagister.taurusmagister.repositorio.MensagemRepository;
import com.taurusmagister.taurusmagister.repositorio.UsuarioBasicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    UsuarioBasicoRepository usuarioBasicoRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MensagemRepository mensagemRepository;

    @GetMapping
    ResponseEntity getChats(@RequestBody UsuarioBasico usuarioBasico) {
        if (chatRepository.count() == 0) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(chatRepository.getChatsByUser(usuarioBasico));
    }

    @PostMapping("/{fkUsuario1}/{fkUsuario2}")
    ResponseEntity postChat(@PathVariable Integer fkUsuario1, @PathVariable Integer fkUsuario2) {
        Optional<UsuarioBasico> usuarioBasico1 = usuarioBasicoRepository.findById(fkUsuario1);
        Optional<UsuarioBasico> usuarioBasico2 = usuarioBasicoRepository.findById(fkUsuario2);

        Chat chat = new Chat();

        if (usuarioBasico1.isPresent() && usuarioBasico2.isPresent()) {
            UsuarioBasico user1 = usuarioBasico1.get();
            UsuarioBasico user2 = usuarioBasico2.get();
            chat.setFkUsuario1(user1);
            chat.setFkUsuario2(user2);
        }

        chatRepository.save(chat);

        return ResponseEntity.status(200).build();
    }

    @GetMapping("/mensagens/ultima/{fkChat}")
    ResponseEntity getUltimaMensagem(@PathVariable Integer fkChat) {
        FilaString<Mensagem> fila = mensagemRepository.findAllByFkChat(fkChat);
        if (fila.isEmpty())
            return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(fila.peek());
    }

    @GetMapping("/mensagens/{fkChat}")
    ResponseEntity getMensagens(@PathVariable Integer fkChat) {
        List<Mensagem> mensagens = (List<Mensagem>) mensagemRepository.findAllByFkChat(fkChat);
        if (mensagens.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(mensagens);
    }

    @PostMapping("/mensagens")
    ResponseEntity postMensagens(@RequestBody Mensagem mensagem) {
        if (mensagem == null) {
            return ResponseEntity.status(404).build();
        }
        mensagemRepository.save(mensagem);
        return ResponseEntity.status(200).build();
    }
}
