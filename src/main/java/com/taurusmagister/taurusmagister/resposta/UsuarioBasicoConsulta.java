package com.taurusmagister.taurusmagister.resposta;

import com.taurusmagister.taurusmagister.entidade.Cargo;

public class UsuarioBasicoConsulta {

    private String nome;
    private String email;
    private boolean autenticado;
    private Cargo cargo;

    public UsuarioBasicoConsulta(String nome, String email, boolean autenticado, Cargo cargo) {
        this.nome = nome;
        this.email = email;
        this.autenticado = autenticado;
        this.cargo = cargo;
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

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "UsuarioBasicoConsulta{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", autenticado=" + autenticado +
                ", cargo=" + cargo +
                '}';
    }
}
