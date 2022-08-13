package com.taurusmagister.taurusmagister.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCargo;
    private String nomeCargo;

    @ManyToOne
    private Area fkArea;

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public Area getFkArea() {
        return fkArea;
    }

    public void setFkArea(Area fkArea) {
        this.fkArea = fkArea;
    }
}
