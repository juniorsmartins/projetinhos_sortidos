package br.com.uniciv.gestaotarefas.controladores.dtos.request;

import br.com.uniciv.gestaotarefas.modelos.enuns.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RoleRequest {

  @NotNull
  @Enumerated(EnumType.STRING)
  private ERole name;
}

