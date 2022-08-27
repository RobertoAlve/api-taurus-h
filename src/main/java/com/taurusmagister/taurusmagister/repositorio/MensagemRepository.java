package com.taurusmagister.taurusmagister.repositorio;

import com.taurusmagister.taurusmagister.entidade.Mensagem;
import com.taurusmagister.taurusmagister.resposta.MensagemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

    @Query("select new com.taurusmagister.taurusmagister.resposta.MensagemInfo(m.idMensagem, m.mensagem, m.date) from Mensagem m where m.fkUsuarioMentor.idUsuario = ?1 and m.fkUsuarioMentorado.idUsuario = ?2")
    List<MensagemInfo> getMensagens(int idMentor, int idMentorado);
}
