package com.taurusmagister.taurusmagister.repositorio;

import com.taurusmagister.taurusmagister.entidade.Cargo;
import com.taurusmagister.taurusmagister.entidade.Pilha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CargoRepository extends JpaRepository<Cargo, Integer> {

    @Query("select c from Cargo c where c.fkArea.idArea = ?1")
    List<Cargo> getCargos(int idArea);

    Cargo findByNomeCargo(String cargo);
}
