package com.taurusmagister.taurusmagister.repositorio;

import com.taurusmagister.taurusmagister.entidade.UsuarioBasico;
import com.taurusmagister.taurusmagister.resposta.UsuarioBasicoConsulta;
import com.taurusmagister.taurusmagister.resposta.UsuarioImagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UsuarioBasicoRepository extends JpaRepository<UsuarioBasico, Integer> {

    @Transactional
    @Modifying
    @Query("update UsuarioBasico u set u.autenticado = ?2 where u.email = ?1")
    void autentica(String email, boolean autenticado);

    @Transactional
    @Modifying
    @Query("update UsuarioBasico u set u.autenticado = ?2 where u.email = ?1")
    void logOff(String email, boolean autenticado);

    @Transactional
    @Modifying
    @Query("update UsuarioBasico u set u.cargo.idCargo = ?1 where u.idUsuario = ?2")
    void updateSqlCargo(int cargo, long idUsuario);

    @Transactional
    @Modifying
    @Query("update UsuarioBasico u set u.area.idArea = ?1 where u.idUsuario = ?2")
    void updateSqlArea(int area, long idUsuario);

    @Query("select new com.taurusmagister.taurusmagister.resposta.UsuarioBasicoConsulta(u.nome, u.email, u.autenticado, " +
            "u.cargo, u.idConferencia) from UsuarioBasico u")
    List<UsuarioBasicoConsulta> getUsuariosBasicos();

    UsuarioBasico findByEmailAndSenha(String Email, String senha);

    UsuarioBasico findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select new com.taurusmagister.taurusmagister.resposta.UsuarioImagem(u.imagem) from  UsuarioBasico u where " +
            " u.idUsuario = ?1")
    UsuarioImagem getImagem(int idUsuario);

    @Transactional
    @Modifying
    @Query("update UsuarioBasico u set u.idConferencia = ?2 where u.idUsuario = ?1")
    void alterarIdConferencia(int idUsuario, String idConferencia);

    @Query("select u.idConferencia from UsuarioBasico u where u.idUsuario = ?1")
    String getIdConferencia(int idUsuario);
}
