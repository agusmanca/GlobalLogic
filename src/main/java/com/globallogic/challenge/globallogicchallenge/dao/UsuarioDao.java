package com.globallogic.challenge.globallogicchallenge.dao;

import com.globallogic.challenge.globallogicchallenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UsuarioDao extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
