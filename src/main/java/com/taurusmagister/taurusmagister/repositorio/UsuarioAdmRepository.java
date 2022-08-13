package com.taurusmagister.taurusmagister.repositorio;

import com.taurusmagister.taurusmagister.entidade.UsuarioAdm;
import com.taurusmagister.taurusmagister.resposta.UsuarioAdmConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsuarioAdmRepository extends JpaRepository<UsuarioAdm, Integer> {

    @Transactional
    @Modifying
    @Query("update UsuarioAdm u set u.autenticado = ?2 where u.email = ?1")
    void autentica(String email, boolean autenticado);

    @Transactional
    @Modifying
    @Query("update UsuarioAdm u set u.autenticado = ?2 where u.email = ?1")
    void logOff(String email, boolean autenticado);

    @Query("select new com.taurusmagister.taurusmagister.resposta.UsuarioAdmConsulta(u.nome, u.email, u.autenticado)" +
            " from UsuarioAdm u")
    List<UsuarioAdmConsulta> getUsuariosAdm();

    UsuarioAdm findByEmailAndSenha(String email, String senha);

    UsuarioAdm findByEmail(String email);

    boolean existsByEmail(String email);
}
