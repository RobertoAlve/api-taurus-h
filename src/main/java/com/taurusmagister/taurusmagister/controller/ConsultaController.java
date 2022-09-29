package com.taurusmagister.taurusmagister.controller;

import com.taurusmagister.taurusmagister.repositorio.ConsultaRepository;
import com.taurusmagister.taurusmagister.requisicao.PublicacaoFrontEnd;
import com.taurusmagister.taurusmagister.resposta.PublicacaoFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    ConsultaRepository consultaRepository;

    @GetMapping("/maioresPublicacoes")
    ResponseEntity getMaisPublicacoes() {
        return ResponseEntity.status(200).body(consultaRepository.getMaisPublicacoes(PageRequest.of(0, 3)));
    }

    @GetMapping("/usuariosAjudados/{id}")
    ResponseEntity getUsuariosAjudados(@PathVariable int id) {
        return ResponseEntity.status(200).body(consultaRepository.getUsuarioAjudados(id));
    }

    @GetMapping("/publicacoes/finalizadas/{id}")
    ResponseEntity getPiblucacoesFinalizadas(@PathVariable int id) {
        return ResponseEntity.status(200).body(consultaRepository.getPublicacoesFinalizadas(id));
    }

    @GetMapping("/publicacoes/filtro/plataforma/{plataforma}")
    ResponseEntity<List<PublicacaoFront>> getPublicacoesFiltroPlataforma(@PathVariable String plataforma) {
        return ResponseEntity.status(200).body(consultaRepository.getPublicacoesFiltroPlataforma(plataforma));
    }

}
