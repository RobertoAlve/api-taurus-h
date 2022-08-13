package com.taurusmagister.taurusmagister.resposta;

public class UsuarioImagem {

    private byte[] imagem;

    public UsuarioImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
