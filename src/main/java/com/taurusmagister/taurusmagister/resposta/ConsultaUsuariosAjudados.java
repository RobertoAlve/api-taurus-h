package com.taurusmagister.taurusmagister.resposta;

public class ConsultaUsuariosAjudados {

    private String nome;
    private byte[] imagem;
    private String habilidades;

    public ConsultaUsuariosAjudados(String nome, byte[] imagem, String habilidades) {
        this.nome = nome;
        this.imagem = imagem;
        this.habilidades = habilidades;
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
}
