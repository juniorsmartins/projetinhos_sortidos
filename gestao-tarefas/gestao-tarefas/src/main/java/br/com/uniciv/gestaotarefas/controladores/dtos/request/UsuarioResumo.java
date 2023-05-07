package br.com.uniciv.gestaotarefas.controladores.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResumo {

  @NotNull(message = "{usuario.id.not-null}")
  @PositiveOrZero(message = "{usuario.id.positive-or-zero}")
  private Integer id;

  @NotBlank(message = "{usuario.nome.not-blank}")
  @Size(min = 1, max = 100, message = "{usuario.nome.size}")
  private String nome;

  @NotBlank(message = "{usuario.nome.not-blank}")
  @Size(min = 10, max = 50, message = "{usuario.senha.size}")
  private String senha;
}

