package br.com.uniciv.gestaotarefas.modelos;

import br.com.uniciv.gestaotarefas.modelos.enuns.TarefaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@NamedQuery(name = "Tarefa.tarefasPorCategoria", query = "select t from Tarefa t inner join t.tarefaCategoria c where c.nome = ?1")
public class Tarefa {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "descricao", length = 150, nullable = false)
  private String descricao;

  @Column(name = "data_entrega", nullable = false)
  private LocalDate dataEntrega;

  @Column(name = "visivel", nullable = false)
  private boolean visivel = Boolean.TRUE;

  @Enumerated(EnumType.STRING)
  @Column(name = "tarefa_status", nullable = false)
  private TarefaStatus tarefaStatus = TarefaStatus.ABERTO;

  @ManyToOne
  @JoinColumn(name = "tarefa_categoria_id", referencedColumnName = "id", columnDefinition = "INTEGER",
      nullable = false, foreignKey = @ForeignKey(name = "fk_tarefas_tarefa_categoria_id"))
  private TarefaCategoria tarefaCategoria;

  @ManyToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id", columnDefinition = "INTEGER",
      nullable = false, foreignKey = @ForeignKey(name = "fk_tarefas_usuario_id"))
  private Usuario usuario;
}

