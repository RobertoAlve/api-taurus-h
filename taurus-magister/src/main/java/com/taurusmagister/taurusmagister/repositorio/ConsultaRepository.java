package com.taurusmagister.taurusmagister.repositorio;

import com.taurusmagister.taurusmagister.entidade.UsuarioBasico;
import com.taurusmagister.taurusmagister.resposta.ConsultaMaioresPublicadores;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<UsuarioBasico, Integer> {

    @Query("select new com.taurusmagister.taurusmagister.resposta.ConsultaMaioresPublicadores(count(p.fkUsuario.idUsuario)" +
            ", max(u.idUsuario), u.nome, max(u.imagem), max(u.habilidades)) from Publicacao p inner join UsuarioBasico " +
            "u on u.idUsuario = p.fkUsuario.idUsuario group by u.nome order by count(p.fkUsuario.idUsuario) desc")
    List<ConsultaMaioresPublicadores> getMaisPublicacoes(PageRequest pageable);

    @Query("select count(t.idTransacao) as qtd, t.fkMentorado.nome, t.fkMentorado.imagem, t.fkMentorado.habilidades from Transacao t where " +
            "t.fkMentor.idUsuario = ?1 group by t.idTransacao order by qtd desc")
    List<Object> getUsuarioAjudados(int id);

    @Query("select new com.taurusmagister.taurusmagister.resposta.PublicacaoFinalizadaFront(t.idTransacao, t.publicacao.titulo," +
            "t.publicacao.plataforma,  t.publicacao.descricao, t.fkMentor.nome) from Transacao t where " +
            "t.fkMentorado.idUsuario = ?1 or t.fkMentor.idUsuario = ?1 and t.publicacao.andamento = 'Finalizada'")
    List<Object> getPublicacoesFinalizadas(int id);
}
