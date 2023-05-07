package br.com.uniciv.gestaotarefas.controladores.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TarefaCategoriaResumo {

  @NotNull(message = "{tarefaCategoria.id.not-null}")
  @PositiveOrZero(message = "{tarefaCategoria.id.positive-or-zero}")
  private Integer id;

  @NotBlank(message = "{tarefaCategoria.nome.not-blank}")
  @Size(min = 1, max = 50, message = "{tarefaCategoria.nome.size}")
  private String nome;
}

