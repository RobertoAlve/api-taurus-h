package com.taurusmagister.taurusmagister.entidade;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@MappedSuperclass
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int idUsuario;
    protected String nome;

    @Column(length = 50_000_000)
    private byte[] imagem;

    @Column(length = 50_000_000)
    private byte[] imagemCapa;
    protected String email;
    protected String senha;
    protected boolean autenticado;
    protected String habilidades;

    public Usuario(String email, String nome, String senha) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    protected Usuario() {
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

    public byte[] getImagemCapa() {
        return imagemCapa;
    }

    public void setImagemCapa(byte[] imagemCapa) {
        this.imagemCapa = imagemCapa;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

    public boolean autenticaUsuario(String email, String senha) {
        return email.equals(this.email) && senha.equals(this.senha);
    }

    public boolean logoff(String email) {
        return this.email.equals(email);
    }

    public abstract List<String> exibeInformacoes(List<Object> u);

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", imagem=" + Arrays.toString(imagem) +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", autenticado=" + autenticado +
                ", habilidades=" + habilidades +
                '}';
    }
}
