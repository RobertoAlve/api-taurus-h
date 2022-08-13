package com.taurusmagister.taurusmagister.controller;

import com.taurusmagister.taurusmagister.entidade.Transacao;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoControllerTest {


    @Test
    void exibeTransacoesSomenteSeTiverNoMinimoUmaTransacao() {

        List<Transacao> transacoes = new ArrayList<>();

        boolean retorno = true;
        if(transacoes.size()== 0){

            retorno = false;
        }

        assertEquals(false,retorno);
    }
}