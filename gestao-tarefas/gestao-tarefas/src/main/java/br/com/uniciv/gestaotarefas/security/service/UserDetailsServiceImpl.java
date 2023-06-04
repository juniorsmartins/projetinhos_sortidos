package br.com.uniciv.gestaotarefas.security.service;

import br.com.uniciv.gestaotarefas.repositorios.UsuarioRepositorio;
import br.com.uniciv.gestaotarefas.security.models.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UsuarioRepositorio usuarioRepositorio;

  /**
   * Método para buscar usuário por username no banco de dados
   */
  @Transactional
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    var usuario  = this.usuarioRepositorio.findByNome(username)
      .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

    return UserDetailsImpl.build(usuario);
  }
}

