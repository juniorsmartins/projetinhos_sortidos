package br.com.uniciv.gestaotarefas.controladores.dtos.response;

import br.com.uniciv.gestaotarefas.modelos.enuns.TarefaStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TarefaResponse {

  private Integer id;

  private String descricao;

  private TarefaStatus tarefaStatus;

  private LocalDate dataEntrega;

  private boolean visivel;

  public TarefaCategoriaResponse tarefaCategoria;

  private UsuarioResponse usuario;
}

