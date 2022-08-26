package com.taurusmagister.taurusmagister.repositorio;

import com.taurusmagister.taurusmagister.entidade.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

    @Query("select m from Mensagem m where m.fkUsuarioMentor.idUsuario = ?1 and m.fkUsuarioMentorado.idUsuario = ?2")
    List<Mensagem> getMensagens(int idMentor, int idMentorado);
}
