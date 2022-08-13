package com.taurusmagister.taurusmagister.entidade;

import com.taurusmagister.taurusmagister.interfaces.PublicacaoIterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class FiltrosIterator implements PublicacaoIterator {

    private Filtro filtro;
    private List<Object> publicacoes;
    private static int currentPosition = 0;

    public FiltrosIterator(Filtro filtro) {
        this.filtro = filtro;
        this.publicacoes = new ArrayList<>();
    }

    public void lazyLoad() {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "taurus" );
        EntityManager entitymanager = emfactory.createEntityManager();

        String query = "select p from Publicacao p";

        if (filtro.getPlataforma() != null) {
            query += " where p.plataforma = " + String.format("'%s'", filtro.getPlataforma());
        }

        if (filtro.getPlataforma() != null && filtro.getAndamento() != null) {
            query += " and p.andamento = " + String.format("'%s'", filtro.getAndamento());
        } else if (filtro.getAndamento() != null) {
            query += " where p.andamento = " + String.format("'%s'", filtro.getAndamento());
        }

        if (publicacoes.size() == 0) {
            List res = entitymanager.createQuery(query).getResultList();
            entitymanager.close();
            for (int i = 0; i < res.size(); i++) {
                publicacoes.add(res.get(i));
            }
        }
    }

    @Override
    public boolean hasNext() {
        lazyLoad();
        return currentPosition < publicacoes.size();
    }

    @Override
    public Object getNext() {
        if (!hasNext()) {
            return null;
        }

        Object publicacao = publicacoes.get(currentPosition);
        this.currentPosition++;
        System.out.println(currentPosition);
        return publicacao;
    }

    @Override
    public void reset() {
        currentPosition = 0;
    }
}
