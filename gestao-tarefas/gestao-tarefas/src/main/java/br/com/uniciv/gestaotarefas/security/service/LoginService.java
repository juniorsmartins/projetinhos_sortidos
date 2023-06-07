package br.com.uniciv.gestaotarefas.security.service;

import br.com.uniciv.gestaotarefas.security.controller.dtos.JwtResponse;
import br.com.uniciv.gestaotarefas.security.models.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LoginService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtils jwtUtils;

  public JwtResponse autenticarUsuario(String nome, String senha) {
    var authentication = this.authenticationManager
      .authenticate(new UsernamePasswordAuthenticationToken(nome, senha));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    var jwt = this.jwtUtils.generateJwtToken(authentication);

    var userDetails = (UserDetailsImpl) authentication.getPrincipal();
    var roles = userDetails.getAuthorities()
      .stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.toList());

    return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
  }
}

