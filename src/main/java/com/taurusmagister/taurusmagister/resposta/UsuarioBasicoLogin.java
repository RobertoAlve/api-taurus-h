package com.taurusmagister.taurusmagister.resposta;

import java.util.ArrayList;
import java.util.List;

public class UsuarioBasicoLogin {

    private int idUsuario;
    private String nome;
    private String email;
    private byte[] imagem;
    private byte[] imagemCapa;
    private boolean autenticado;
    private String area;
    private String cargo;

    public UsuarioBasicoLogin(int idUsuario, String nome, String email, byte[] imagem, byte[] imagemCapa, boolean autenticado,
                              String area, String cargo) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.imagem = imagem;
        this.imagemCapa = imagemCapa;
        this.autenticado = autenticado;
        this.area = area;
        this.cargo = cargo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public byte[] getImagemCapa() {
        return imagemCapa;
    }

    public void setImagemCapa(byte[] imagemCapa) {
        this.imagemCapa = imagemCapa;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public List<String> exibeInformacoes(List<Object> usuarios) {
        List<String> informacoes = new ArrayList<>();
        informacoes.add(this.toString());
        return informacoes;
    }

}
