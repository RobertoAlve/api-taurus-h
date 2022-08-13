package com.taurusmagister.taurusmagister.requisicao;

import com.taurusmagister.taurusmagister.entidade.UsuarioBasico;

public class PublicacaoFrontEnd {

    private String titulo;
    private String descricao;
    private String proposta;
    private String plataforma;
    private Integer idUsuario;
    private UsuarioBasico usuario;

    public PublicacaoFrontEnd(String titulo, String descricao, String proposta, String plataforma, Integer idUsuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.proposta = proposta;
        this.plataforma = plataforma;
        this.usuario = new UsuarioBasico();
        this.usuario.setIdUsuario(idUsuario);
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

    public String getProposta() {
        return proposta;
    }

    public void setProposta(String proposta) {
        this.proposta = proposta;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public UsuarioBasico getIdUsuario() {
        return usuario;
    }

    public void setIdUsuario(UsuarioBasico idUsuario) {
        this.usuario = idUsuario;
    }
}
