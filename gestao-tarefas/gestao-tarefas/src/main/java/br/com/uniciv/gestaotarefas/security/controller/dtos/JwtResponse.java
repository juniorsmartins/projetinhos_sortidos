package br.com.uniciv.gestaotarefas.security.controller.dtos;

import br.com.uniciv.gestaotarefas.modelos.enuns.ERole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {

  private String token;
  private String type = "Bearer";
  private Integer id;
  private String username;
  private List<String> roles;

  public JwtResponse(String token, Integer id, String username, List<String> roles) {
    this.token = token;
    this.type = type;
    this.id = id;
    this.username = username;
    this.roles = roles;
  }

  public boolean isAdmin() {
    return roles.contains(ERole.ROLE_ADMIN.name());
  }
}

