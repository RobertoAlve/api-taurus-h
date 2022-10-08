package com.taurusmagister.taurusmagister.entidade;

import com.taurusmagister.taurusmagister.resposta.ConsultaPlataformasMaisRequisitadas;

import java.util.ArrayList;
import java.util.List;

public class Recursividade {

    private ArrayList<ConsultaPlataformasMaisRequisitadas> lista;

    public Recursividade() {
        lista = new ArrayList<>();
    }

    public void getPlataformasMaisRequisitadasOrdenadas(ArrayList<ConsultaPlataformasMaisRequisitadas> lista, int inicio, int fim) {
        if( inicio < fim )
        {
            int l = inicio + 1;
            int r = fim;
            long p = lista.get(inicio).getQtdPublicacoes();

            while( l < r )
            {
                if( lista.get(l).getQtdPublicacoes() <= p )
                {
                    l++;
                }
                else if( lista.get(r).getQtdPublicacoes() >= p )
                {
                    r--;
                }
                else
                {
                    ConsultaPlataformasMaisRequisitadas aux = lista.get(l);
                    lista.set(l, lista.get(r));
                    lista.set(r, aux);
                }
            }

            if( lista.get(l).getQtdPublicacoes() < p )
            {
                ConsultaPlataformasMaisRequisitadas aux = lista.get(l);
                lista.set(l, lista.get(inicio));
                lista.set(inicio, aux);
                l--;
            }
            else
            {
                l--;
                ConsultaPlataformasMaisRequisitadas aux = lista.get(l);
                lista.set(l, lista.get(inicio));
                lista.set(inicio, aux);
            }

            getPlataformasMaisRequisitadasOrdenadas( lista, inicio, l );
            getPlataformasMaisRequisitadasOrdenadas( lista, r, fim );
        }
    }
}
