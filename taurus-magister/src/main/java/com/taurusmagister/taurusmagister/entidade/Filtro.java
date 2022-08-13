package com.taurusmagister.taurusmagister.entidade;

public class Filtro {

    private String plataforma;
    private String andamento;

    public Filtro(String plataforma, String andamento) {
        this.plataforma = plataforma;
        this.andamento = andamento;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getAndamento() {
        return andamento;
    }

    public void setAndamento(String andamento) {
        this.andamento = andamento;
    }
}
