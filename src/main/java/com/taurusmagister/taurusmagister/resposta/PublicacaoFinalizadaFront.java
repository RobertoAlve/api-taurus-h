package com.taurusmagister.taurusmagister.resposta;

public class PublicacaoFinalizadaFront {

    private int idTransacao;
    private String titulo;
    private String plataforma;
    private String descricao;
    private String nomeMentor;

    public PublicacaoFinalizadaFront(int idTransacao, String titulo, String plataforma, String descricao,
                                     String nomeMentor) {
        this.idTransacao = idTransacao;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.descricao = descricao;
        this.nomeMentor = nomeMentor;
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeMentor() {
        return nomeMentor;
    }

    public void setNomeMentor(String nomeMentor) {
        this.nomeMentor = nomeMentor;
    }
}
