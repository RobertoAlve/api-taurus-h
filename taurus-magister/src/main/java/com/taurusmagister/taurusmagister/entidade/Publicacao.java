package com.taurusmagister.taurusmagister.entidade;

import com.taurusmagister.taurusmagister.enums.ANDAMENTO;
import com.taurusmagister.taurusmagister.requisicao.PublicacaoFrontEnd;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPublicacao;
    private String titulo;
    private String descricao;
    private String dataPublicacao;
    private String proposta;

    @ManyToOne
    private UsuarioBasico fkUsuario;

    @NotBlank
    private String andamento = ANDAMENTO.DEFAULT;

    @NotBlank
    private String plataforma;

    public Publicacao() {
    }

    public Publicacao(String titulo, String descricao, String proposta, int idUsuario, String plataforma) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.proposta = proposta;
        this.fkUsuario = new UsuarioBasico();
        this.fkUsuario.idUsuario = idUsuario;
        this.plataforma = plataforma;
    }

    public Publicacao(PublicacaoFrontEnd publicacao) {
        this.titulo = publicacao.getTitulo();
        this.descricao = publicacao.getDescricao();
        this.dataPublicacao = "teste";
        this.proposta = publicacao.getProposta();
        this.plataforma = publicacao.getPlataforma();
    }

    public int getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(int idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return dataPublicacao;
    }

    public void setData(String data) {
        this.dataPublicacao = data;
    }

    public String getProposta() {
        return proposta;
    }

    public void setProposta(String proposta) {
        this.proposta = proposta;
    }

    public UsuarioBasico getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(UsuarioBasico fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public String getAndamento() {
        return andamento;
    }

    public void setAndamento(String andamento) {
        this.andamento = andamento;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public String toString() {
        return "Publicacao{" +
                "idPublicacao=" + idPublicacao +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data='" + dataPublicacao + '\'' +
                ", proposta='" + proposta + '\'' +
                ", fkUsuario=" + fkUsuario +
                '}';
    }
}
