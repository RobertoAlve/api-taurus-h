package com.taurusmagister.taurusmagister.repositorio;

import com.taurusmagister.taurusmagister.entidade.Publicacao;
import com.taurusmagister.taurusmagister.resposta.PublicacaoFront;
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
            "p.fkUsuario.nome, p.fkUsuario.imagem, p.titulo, p.plataforma, p.descricao, p.proposta) from Publicacao p")
    List<PublicacaoFront> getPublicacoes();

    @Query("select new com.taurusmagister.taurusmagister.resposta.PublicacaoFront(p.idPublicacao, p.fkUsuario.idUsuario, " +
            "p.fkUsuario.nome, p.fkUsuario.imagem, p.titulo, p.plataforma, p.descricao, p.proposta) from Publicacao p where p.andamento = 'Em andamento'")
    List<PublicacaoFront> publicacoesEmAndamento();

}
