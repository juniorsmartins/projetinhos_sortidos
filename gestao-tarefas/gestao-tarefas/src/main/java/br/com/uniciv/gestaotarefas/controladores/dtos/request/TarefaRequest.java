package br.com.uniciv.gestaotarefas.controladores.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TarefaRequest {

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

