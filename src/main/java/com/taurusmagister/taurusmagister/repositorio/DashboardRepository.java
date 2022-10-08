package com.taurusmagister.taurusmagister.repositorio;

import com.taurusmagister.taurusmagister.entidade.Publicacao;
import com.taurusmagister.taurusmagister.resposta.ConsultaPlataformasMaisRequisitadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface DashboardRepository extends JpaRepository<Publicacao, Integer> {

    @Query("select new com.taurusmagister.taurusmagister.resposta.ConsultaPlataformasMaisRequisitadas(count(p.titulo), p.titulo) from Publicacao p group by p.titulo")
    ArrayList<ConsultaPlataformasMaisRequisitadas> getPlataformasMaisRequisitadas();
}
