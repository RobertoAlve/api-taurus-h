package com.taurusmagister.taurusmagister.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMensagem")
    private int idMensagem;

    @ManyToOne
    @NotNull
    private UsuarioBasico fkUsuarioMentor;

    @ManyToOne
    @NotNull
    private UsuarioBasico fkUsuarioMentorado;

    @ManyToOne
    @NotNull
    private Chat fkChat;

    private String mensagem;

    private LocalDateTime date;

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

    public Chat getFkChat() {
        return fkChat;
    }

    public void setFkChat(Chat fkChat) {
        this.fkChat = fkChat;
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
