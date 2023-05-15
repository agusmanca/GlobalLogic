package com.globallogic.challenge.globallogicchallenge.dao;

import com.globallogic.challenge.globallogicchallenge.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UsuarioDao extends JpaRepository<Usuario, UUID> {
    Usuario findByEmail(String email);
}
