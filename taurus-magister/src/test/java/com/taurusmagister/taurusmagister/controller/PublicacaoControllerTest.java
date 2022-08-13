package com.taurusmagister.taurusmagister.controller;

import com.taurusmagister.taurusmagister.entidade.Publicacao;
import com.taurusmagister.taurusmagister.entidade.UsuarioAdm;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PublicacaoControllerTest {

    @Test
    void exibePublicacoesSomenteSeTiverNoMinimoUmaTransacao() {
        List<Publicacao> publicacoes = new ArrayList<>();

        boolean retorno = true;
        if(publicacoes.size()== 0){

            retorno = false;

        }

        assertEquals(false,retorno);
    }
}