package br.com.uniciv.gestaotarefas.security.controller.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

  @NotBlank
  private String nome;

  @NotBlank
  private String senha;
}

