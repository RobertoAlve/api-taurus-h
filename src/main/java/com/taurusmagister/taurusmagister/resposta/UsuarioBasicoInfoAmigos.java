package com.taurusmagister.taurusmagister.resposta;

public class UsuarioBasicoInfoAmigos {

    private int idUsuario;
    private byte[] imagem;
    private String nome;

    public UsuarioBasicoInfoAmigos(int idUsuario, byte[] imagem, String nome) {
        this.idUsuario = idUsuario;
        this.imagem = imagem;
        this.nome = nome;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
