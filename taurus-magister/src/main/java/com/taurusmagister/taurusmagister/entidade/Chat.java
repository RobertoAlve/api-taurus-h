package com.taurusmagister.taurusmagister.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idChat")
    private int idChat;

    @ManyToOne
    @NotNull
    private UsuarioBasico fkUsuario1;

    @ManyToOne
    @NotNull
    private UsuarioBasico fkUsuario2;

    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    public UsuarioBasico getFkUsuario1() {
        return fkUsuario1;
    }

    public void setFkUsuario1(UsuarioBasico fkUsuario1) {
        this.fkUsuario1 = fkUsuario1;
    }

    public UsuarioBasico getFkUsuario2() {
        return fkUsuario2;
    }

    public void setFkUsuario2(UsuarioBasico fkUsuario2) {
        this.fkUsuario2 = fkUsuario2;
    }
}
