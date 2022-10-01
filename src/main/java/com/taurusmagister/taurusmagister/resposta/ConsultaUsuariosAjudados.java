package com.taurusmagister.taurusmagister.resposta;

public class ConsultaUsuariosAjudados {

    private String nome;
    private byte[] imagem;
    private String habilidades;
    private boolean autenticado;

    public ConsultaUsuariosAjudados(String nome, byte[] imagem, String habilidades, boolean autenticado) {
        this.nome = nome;
        this.imagem = imagem;
        this.habilidades = habilidades;
        this.autenticado = autenticado;
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

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
}
