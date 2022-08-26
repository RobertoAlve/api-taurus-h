package com.taurusmagister.taurusmagister.entidade;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taurusmagister.taurusmagister.requisicao.MensagemFrontEnd;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMensagem")
    private int idMensagem;

    @ManyToOne()
    @NotNull
    private UsuarioBasico fkUsuarioMentor;

    @ManyToOne()
    @NotNull
    private UsuarioBasico fkUsuarioMentorado;

    private String mensagem;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDateTime date;

    public Mensagem(MensagemFrontEnd mensagemFrontEnd) {
        this.mensagem = mensagemFrontEnd.getMensagem();
        this.date = mensagemFrontEnd.getDate();
    }

    public  Mensagem() {
    }

    public int getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(int idMensagem) {
        this.idMensagem = idMensagem;
    }

    public UsuarioBasico getFkUsuarioMentor() {
        return fkUsuarioMentor;
    }

    public void setFkUsuarioMentor(UsuarioBasico fkUsuarioMentor) {
        this.fkUsuarioMentor = fkUsuarioMentor;
    }

    public UsuarioBasico getFkUsuarioMentorado() {
        return fkUsuarioMentorado;
    }

    public void setFkUsuarioMentorado(UsuarioBasico fkUsuarioMentorado) {
        this.fkUsuarioMentorado = fkUsuarioMentorado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
