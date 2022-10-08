package com.taurusmagister.taurusmagister.resposta;

public class ConsultaPlataformasMaisRequisitadas {

    private long qtdPublicacoes;
    private String titulo;

    public long getQtdPublicacoes() {
        return qtdPublicacoes;
    }

    public void setQtdPublicacoes(int qtdPublicacoes) {
        this.qtdPublicacoes = qtdPublicacoes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ConsultaPlataformasMaisRequisitadas(long qtdPublicacoes, String titulo) {
        this.qtdPublicacoes = qtdPublicacoes;
        this.titulo = titulo;
    }
}
