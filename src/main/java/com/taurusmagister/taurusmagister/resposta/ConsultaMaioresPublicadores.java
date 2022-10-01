package com.taurusmagister.taurusmagister.resposta;

import java.util.Arrays;

public class ConsultaMaioresPublicadores {

    private long qtdPublicacoes;
    private int idUsuario;
    private String nome;
    private byte[] imagem;
    private String habilidades;
    private boolean autenticado;

    public ConsultaMaioresPublicadores(long qtdPublicacoes, int idUsuario, String nome, byte[] imagem, String habilidades, boolean autenticado) {
        this.qtdPublicacoes = qtdPublicacoes;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.imagem = imagem;
        this.habilidades = habilidades;
        this.autenticado = autenticado;
    }

    public long getQtdPublicacoes() {
        return qtdPublicacoes;
    }

    public void setQtdPublicacoes(long qtdPublicacoes) {
        this.qtdPublicacoes = qtdPublicacoes;
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

    @Override
    public String toString() {
        return "ConsultaMaioresPublicadores{" +
                "qtdPublicacoes=" + qtdPublicacoes +
                ", idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", imagem=" + Arrays.toString(imagem) +
                ", habilidades='" + habilidades + '\'' +
                '}';
    }
}
