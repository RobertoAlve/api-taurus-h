package com.taurusmagister.taurusmagister.controller;

import com.taurusmagister.taurusmagister.entidade.Filtro;
import com.taurusmagister.taurusmagister.entidade.FiltrosIterator;
import com.taurusmagister.taurusmagister.entidade.Publicacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filtros")
public class FiltrosController {

    @PostMapping
    public ResponseEntity getPublicacoes(@RequestBody @Valid Filtro filtro) {
        FiltrosIterator filtrosIterator = new FiltrosIterator(filtro);
        List<Object> publicacoes = new ArrayList<>();
        while (filtrosIterator.hasNext()) {
            publicacoes.add(filtrosIterator.getNext());
        }
        return ResponseEntity.status(200).body(publicacoes);
    }

}
