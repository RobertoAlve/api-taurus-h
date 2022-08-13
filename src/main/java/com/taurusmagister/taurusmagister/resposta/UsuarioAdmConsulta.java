package com.taurusmagister.taurusmagister.resposta;

public class UsuarioAdmConsulta {

    private String nome;
    private String email;
    private boolean autenticado;

    public UsuarioAdmConsulta(String nome, String email, boolean autenticado) {
        this.nome = nome;
        this.email = email;
        this.autenticado = autenticado;
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

    @Override
    public String toString() {
        return "UsuarioAdmConsulta{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", autenticado=" + autenticado +
                '}';
    }
}
