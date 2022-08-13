package com.taurusmagister.taurusmagister.resposta;

public class PublicacaoFront {

    private int idPublicacao;
    private int idUsuario;
    private String nome;
    private byte[] imagem;
    private String titulo;
    private String plataforma;
    private String descricao;
    private String proposta;

    public PublicacaoFront(int idPublicacao, int idUsuario, String nome, byte[] imagem, String titulo,
                           String plataforma, String descricao, String proposta) {
        this.idPublicacao = idPublicacao;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.imagem = imagem;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.descricao = descricao;
        this.proposta = proposta;
    }

    public int getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(int idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
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

    public String getProposta() {
        return proposta;
    }

    public void setProposta(String proposta) {
        this.proposta = proposta;
    }
}
