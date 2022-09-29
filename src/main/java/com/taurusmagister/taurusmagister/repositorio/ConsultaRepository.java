package com.taurusmagister.taurusmagister.repositorio;

import com.taurusmagister.taurusmagister.entidade.UsuarioBasico;
import com.taurusmagister.taurusmagister.resposta.ConsultaMaioresPublicadores;
import com.taurusmagister.taurusmagister.resposta.PublicacaoFront;
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

    @Query("select new com.taurusmagister.taurusmagister.resposta.PublicacaoFinalizadaFront(p.idPublicacao, p.titulo," +
            "p.plataforma,  p.descricao, p.fkMentor.nome) from Publicacao p where " +
            "p.fkUsuario.idUsuario = ?1 or p.fkMentor.idUsuario = ?1 and p.andamento = 'Finalizada'")
    List<Object> getPublicacoesFinalizadas(int id);

    @Query("select new com.taurusmagister.taurusmagister.resposta.PublicacaoFront(p.idPublicacao, p.fkUsuario.idUsuario, " +
            "p.fkUsuario.nome, p.fkUsuario.imagem, p.titulo, p.plataforma, p.descricao, p.proposta, p.andamento) from Publicacao p where " +
            "p.plataforma like CONCAT(?1, '%')")
    List<PublicacaoFront> getPublicacoesFiltroPlataforma(String nomePlataforma);
}
