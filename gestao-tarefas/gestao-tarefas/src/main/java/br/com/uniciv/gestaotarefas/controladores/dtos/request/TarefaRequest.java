package br.com.uniciv.gestaotarefas.controladores.dtos.request;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.uniciv.gestaotarefas.controladores.dtos.TarefaCategoriaResumo;
import br.com.uniciv.gestaotarefas.controladores.dtos.UsuarioResumo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public final class TarefaRequest {

  @NotBlank(message = "{tarefa.descricao.not-blank}")
  @Size(min = 5, max = 150, message = "{tarefa.descricao.size}")
  private String descricao;

  @FutureOrPresent(message = "{tarefa.dataEntrega.future-or-present}")
  private LocalDate dataEntrega;

  @NotNull(message = "{tarefa.tarefaCategoria.not-null}")
  @Valid
  public TarefaCategoriaResumo tarefaCategoria;

  @NotNull(message = "{tarefa.usuario.not-null}")
  @Valid
  private UsuarioResumo usuario;
}

