package com.taurusmagister.taurusmagister.repositorio;

import com.taurusmagister.taurusmagister.entidade.FilaString;
import com.taurusmagister.taurusmagister.entidade.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

    FilaString<Mensagem> findAllByFkChat(Integer fkChat);
}
