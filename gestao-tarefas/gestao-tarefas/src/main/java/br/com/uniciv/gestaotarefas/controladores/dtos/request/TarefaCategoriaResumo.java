package br.com.uniciv.gestaotarefas.controladores.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class TarefaCategoriaResumo {

  @NotNull(message = "{tarefaCategoria.id.not-null}")
  @PositiveOrZero(message = "{tarefaCategoria.id.positive-or-zero}")
  private Integer id;

  @NotBlank(message = "{tarefaCategoria.nome.not-blank}")
  @Size(min = 1, max = 50, message = "{tarefaCategoria.nome.size}")
  private String nome;
}

