package com.taurusmagister.taurusmagister.repositorio;

import com.taurusmagister.taurusmagister.entidade.Area;
import com.taurusmagister.taurusmagister.entidade.Cargo;
import com.taurusmagister.taurusmagister.entidade.Pilha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AreaRepository extends JpaRepository<Area, Integer> {

    @Query("select a from Area a where a.idArea = ?1")
    List<Area> getArea(int idArea);
}
