package com.taurusmagister.taurusmagister.entidade;

import javax.persistence.*;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTransacao;

    @ManyToOne
    private Publicacao publicacao;

    @ManyToOne
    private UsuarioBasico fkMentorado;

    @ManyToOne
    private UsuarioBasico fkMentor;

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public UsuarioBasico getFkMentorado() {
        return fkMentorado;
    }

    public void setFkMentorado(UsuarioBasico fkMentorado) {
        this.fkMentorado = fkMentorado;
    }

    public UsuarioBasico getFkMentor() {
        return fkMentor;
    }

    public void setFkMentor(UsuarioBasico fkMentor) {
        this.fkMentor = fkMentor;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "idTransacao=" + idTransacao +
                ", publicacao=" + publicacao +
                ", fkMentorado=" + fkMentorado +
                ", fkMentor=" + fkMentor +
                '}';
    }
}
