package com.taurusmagister.taurusmagister.entidade;

import com.taurusmagister.taurusmagister.enums.ANDAMENTO;
import com.taurusmagister.taurusmagister.interfaces.EventListener;
import com.taurusmagister.taurusmagister.repositorio.PublicacaoRepository;

import java.util.ArrayList;
import java.util.List;

public class NotificationListener implements EventListener {

    private boolean changed;
    private String message;
    private String colorMessage;
    private List<Publicacao> publicacoes;

    public NotificationListener(String message, String colorMessage) {
        this.message = message;
        this.colorMessage = colorMessage;
        this.publicacoes = new ArrayList<>();
        this.changed = false;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getColorMessage() {
        return colorMessage;
    }

    public void setColorMessage(String colorMessage) {
        this.colorMessage = colorMessage;
    }

    public void addObservable(Publicacao p) {
        publicacoes.add(p);
    }

    public void setChanged() {
        changed = true;
    }

    public void notifyObservers(PublicacaoRepository publicacaoRepository) {
        if (changed) {
            for (Publicacao p:publicacoes) {
                this.update(p, publicacaoRepository);
            }
        }
        changed = false;
    }

    @Override
    public String update(Publicacao publicacao, PublicacaoRepository publicacaoRepository) {
        publicacaoRepository.updateAndamentoPublicacao(publicacao.getIdPublicacao(), ANDAMENTO.EM_ANDAMENTO);
        return String.format("%s - Publicação: %s", this.message, publicacao.getTitulo());
    }
}
