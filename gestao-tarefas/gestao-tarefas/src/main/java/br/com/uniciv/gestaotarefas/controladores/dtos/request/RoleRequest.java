package br.com.uniciv.gestaotarefas.controladores.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RoleRequest {

  @NotBlank
  private String name;
}

