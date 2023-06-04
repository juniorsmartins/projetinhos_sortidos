package br.com.uniciv.gestaotarefas.controladores.dtos.response;

import br.com.uniciv.gestaotarefas.modelos.enuns.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RoleResponse {

  private ERole name;
}

