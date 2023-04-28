package br.com.uniciv.gestaotarefas.modelos;

import br.com.uniciv.gestaotarefas.modelos.enuns.TarefaStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Entity
@Table(name = "tarefas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class Tarefa {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "descricao", length = 200, nullable = false)
  private String descricao;

  @Column(name = "data_entrega", nullable = false)
  private LocalDate dataEntrega;

  @Column(name = "visivel", nullable = false)
  private boolean visivel;

  @Column(name = "tarefa_status", nullable = false)
  private TarefaStatus tarefaStatus;

//  private TarefaCategoria tarefaCategoria;
//
//  private Usuario usuario;
}

