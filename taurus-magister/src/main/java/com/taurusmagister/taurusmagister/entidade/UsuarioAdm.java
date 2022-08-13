package com.taurusmagister.taurusmagister.entidade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UsuarioAdm extends Usuario{

    public UsuarioAdm() {
    }

    public List<String> exibeInformacoes(List<Object> usuarios) {

        List<String> informacoes = new ArrayList<>();

        for (Object usuario:  usuarios) {
            informacoes.add(usuario.toString());
        }

        return informacoes;
    }

    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
}
