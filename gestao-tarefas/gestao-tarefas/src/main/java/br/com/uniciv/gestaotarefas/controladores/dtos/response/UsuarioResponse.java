package br.com.uniciv.gestaotarefas.controladores.dtos.response;

import br.com.uniciv.gestaotarefas.controladores.dtos.request.RoleRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public final class UsuarioResponse {

  private Integer id;

  private String nome;

  private Set<RoleResponse> roles;
}

