package com.taurusmagister.taurusmagister.interfaces;

import com.taurusmagister.taurusmagister.entidade.Publicacao;
import com.taurusmagister.taurusmagister.repositorio.PublicacaoRepository;

public interface EventListener {

    public String update(Publicacao publicacao, PublicacaoRepository publicacaoRepository);

}
