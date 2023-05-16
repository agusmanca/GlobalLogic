package com.globallogic.challenge.globallogicchallenge.service.impl;

import com.globallogic.challenge.globallogicchallenge.dao.UsuarioDao;
import com.globallogic.challenge.globallogicchallenge.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UsuarioDao usuarioDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      User user = usuarioDao.findByEmail(username);;

      if (Objects.isNull(user)) {
          throw new UsernameNotFoundException(username);
      }

      return org.springframework.security.core.userdetails.User
              .withUsername(username)
              .password(user.getPassword())
              .roles("ADMIN")
              .build();
      }
}
