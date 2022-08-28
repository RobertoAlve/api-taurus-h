package com.taurusmagister.taurusmagister.requisicao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taurusmagister.taurusmagister.entidade.UsuarioBasico;

import java.time.LocalDateTime;

public class MensagemFrontEnd {

    private String mensagem;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDateTime date;
    private UsuarioBasico fkUsuarioMentor;
    private UsuarioBasico fkUsuarioMentorado;

    public MensagemFrontEnd(String mensagem, LocalDateTime date, int fkUsuarioMentor, int fkUsuarioMentorado) {
        this.mensagem = mensagem;
        this.date = date;
        this.fkUsuarioMentor = new UsuarioBasico();
        this.fkUsuarioMentor.setIdUsuario(fkUsuarioMentor);
        this.fkUsuarioMentorado = new UsuarioBasico();
        this.fkUsuarioMentorado.setIdUsuario(fkUsuarioMentorado);
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

    public UsuarioBasico getFkUsuarioMentor() {
        return fkUsuarioMentor;
    }

    public void setFkUsuarioMentor(UsuarioBasico fkUsuarioMentor) {
        this.fkUsuarioMentor = fkUsuarioMentor;
    }

    public UsuarioBasico getGetFkUsuarioMentorado() {
        return fkUsuarioMentorado;
    }

    public void setGetFkUsuarioMentorado(UsuarioBasico getFkUsuarioMentorado) {
        this.fkUsuarioMentorado = getFkUsuarioMentorado;
    }
}
