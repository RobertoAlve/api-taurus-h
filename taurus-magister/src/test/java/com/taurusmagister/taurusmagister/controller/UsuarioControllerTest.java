package com.taurusmagister.taurusmagister.controller;

import com.taurusmagister.taurusmagister.entidade.Cargo;
import com.taurusmagister.taurusmagister.entidade.UsuarioAdm;
import com.taurusmagister.taurusmagister.entidade.UsuarioBasico;
import com.taurusmagister.taurusmagister.repositorio.UsuarioBasicoRepository;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioControllerTest {

    @Test
    void cadastraUsuario() {
        UsuarioBasico usuarioBasico = new UsuarioBasico();

        usuarioBasico.setNome("Cruz");
        usuarioBasico.setEmail("fc2369474@gmail.com");
        usuarioBasico.setSenha("Felipe123");
        usuarioBasico.setAutenticado(true);


        UsuarioBasico usuarioBasico2 = new UsuarioBasico();

        usuarioBasico.setNome("Joao");
        usuarioBasico.setEmail("fc2369474@gmail.com");
        usuarioBasico.setSenha("Joao123");


        boolean retorno = false;

        if(usuarioBasico.getEmail().equals(usuarioBasico2.getEmail())){
            retorno = true;
        }

        assertEquals(false, retorno);

    }

    @Test
    void exibeAutenticados() {

        UsuarioBasico usuarioBasico = new UsuarioBasico();

        usuarioBasico.setNome("Cruz");
        usuarioBasico.setEmail("fc2369474@gmail.com");
        usuarioBasico.setSenha("Felipe123");
        usuarioBasico.setAutenticado(true);



        assertEquals(true,usuarioBasico.isAutenticado());


    }

    @Test
    void exibeUsuariosAdmSemCadastroNaoExibir(){

        List<UsuarioAdm> usuarios = new ArrayList<>();
        boolean retorno = true;
        if(usuarios.size()== 0){

            retorno = false;

        }

        assertEquals(false,retorno);

    }

}