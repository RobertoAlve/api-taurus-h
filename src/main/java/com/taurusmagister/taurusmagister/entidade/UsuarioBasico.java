package com.taurusmagister.taurusmagister.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UsuarioBasico extends Usuario {

    @ManyToOne
    private Cargo cargo;

    @ManyToOne
    private Area area;

    @ManyToMany
    private List<UsuarioBasico> amigos = new ArrayList<>();

    public UsuarioBasico() {
    }

    public UsuarioBasico(String email, String nome, String senha, Cargo cargo, Area area) {
        super(email, nome, senha);
        this.cargo = cargo;
        this.area = area;
    }

    public UsuarioBasico(String email, String nome, String senha, int cargo) {
        super(email, nome, senha);
        this.cargo = new Cargo();
        this.cargo.setIdCargo(cargo);
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<String> exibeInformacoes(List<Object> usuarios) {
        List<String> informacoes = new ArrayList<>();
        informacoes.add(this.toString());
        return informacoes;
    }

    public List<UsuarioBasico> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<UsuarioBasico> amigos) {
        this.amigos = amigos;
    }

    @Override
    public String toString() {
        return String.format("%sUsuarioBasico{cargo='%s', area='%s'}", super.toString(), cargo, area);
    }

    public void adicionarAmigo(UsuarioBasico amigo) {
        amigos.add(amigo);
    }

}
