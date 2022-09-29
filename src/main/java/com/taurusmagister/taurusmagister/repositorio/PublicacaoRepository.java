package com.taurusmagister.taurusmagister.repositorio;

import com.taurusmagister.taurusmagister.entidade.Publicacao;
import com.taurusmagister.taurusmagister.resposta.ConsultaUsuariosAjudados;
import com.taurusmagister.taurusmagister.resposta.PublicacaoFront;
import com.taurusmagister.taurusmagister.resposta.PublicacaoFrontEmAndamento;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {

    @Transactional
    @Modifying
    @Query("update Publicacao p set p.andamento = ?2 where p.idPublicacao = ?1")
    void updateAndamentoPublicacao(Integer id, String andamento);

    @Query(value = "select count(p) from Publicacao p where p.andamento = 'Em andamento'")
    int contagem();

    @Query("select new com.taurusmagister.taurusmagister.resposta.PublicacaoFront(p.idPublicacao, p.fkUsuario.idUsuario, " +
            "p.fkUsuario.nome, p.fkUsuario.imagem, p.titulo, p.plataforma, p.descricao, p.proposta, p.andamento) " +
            "from Publicacao p where p.andamento = 'Não iniciada'")
    List<PublicacaoFront> getPublicacoes();

    @Query("select new com.taurusmagister.taurusmagister.resposta.PublicacaoFrontEmAndamento(p.idPublicacao, p.fkUsuario.idUsuario, " +
            "p.fkUsuario.nome, p.fkUsuario.imagem, p.titulo, p.plataforma, p.descricao, p.proposta, p.andamento, p.fkMentor.nome) " +
            "from Publicacao p where p.andamento = 'Em andamento' and p.fkUsuario.idUsuario = ?1 or p.fkMentor.idUsuario = ?1")
    List<PublicacaoFrontEmAndamento> publicacoesEmAndamento(int idUsuario);

    @Transactional
    @Modifying
    @Query("update Publicacao p set p.andamento = 'Finalizada' where p.idPublicacao = ?1")
    void finalizarPublicacao(int idPublicacao);

    @Transactional
    @Modifying
    @Query("update Publicacao p set p.fkMentor.idUsuario = ?1 where p.idPublicacao = ?2")
    void setFkMentorPublicacao(int idMentor, int idPublicacao);

    @Query("select distinct new com.taurusmagister.taurusmagister.resposta.ConsultaUsuariosAjudados(p.fkUsuario.nome, p.fkUsuario.imagem," +
            "p.fkUsuario.habilidades) from Publicacao p where p.fkMentor.idUsuario = ?1")
    List<ConsultaUsuariosAjudados> getUsuariosAjudados(int idUsuario, PageRequest pageable);

}
