package com.taurusmagister.taurusmagister.resposta;

import java.time.LocalDateTime;

public class MensagemInfo {

    private int id;
    private String mensagem;
    private LocalDateTime date;

    public MensagemInfo(int id, String mensagem, LocalDateTime date) {
        this.id = id;
        this.mensagem = mensagem;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
