package br.com.uniciv.gestaotarefas.modelos;

import br.com.uniciv.gestaotarefas.modelos.enuns.TarefaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"descricao"})
public class Tarefa {

  private String descricao;

  private LocalDate dataDeEntrega;

  private boolean visivel;

  private TarefaStatus tarefaStatus;

  private TarefaCategoria tarefaCategoria;

  private Usuario usuario;
}

