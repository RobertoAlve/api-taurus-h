package com.taurusmagister.taurusmagister.repositorio;

import com.taurusmagister.taurusmagister.entidade.Chat;
import com.taurusmagister.taurusmagister.entidade.UsuarioBasico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("select c from Chat c where c.fkUsuario1 = ?1 or c.fkUsuario2 = ?2")
    List<Chat> getChatsByUser(UsuarioBasico usuario);
}
