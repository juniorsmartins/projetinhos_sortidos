package br.com.uniciv.gestaotarefas.controladores.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class UsuarioRequest {

  @NotNull(message = "{usuario.id.not-null}")
  @PositiveOrZero(message = "{usuario.id.positive-or-zero}")
  private Integer id;

  @NotBlank(message = "{usuario.nome.not-blank}")
  @Size(min = 1, max = 100, message = "{usuario.nome.size}")
  private String nome;

  @NotBlank(message = "{usuario.nome.not-blank}")
  @Size(min = 10, max = 50, message = "{usuario.senha.size}")
  private String senha;

  @NotNull
  @Valid
  private Set<RoleRequest> roles;
}

