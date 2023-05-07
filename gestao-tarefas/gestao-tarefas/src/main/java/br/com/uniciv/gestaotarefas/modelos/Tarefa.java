package br.com.uniciv.gestaotarefas.modelos;

import br.com.uniciv.gestaotarefas.modelos.enuns.TarefaStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

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

  @NotBlank(message = "{tarefa.descricao.not-blank}")
  @Size(min = 5, max = 150, message = "{tarefa.descricao.size}")
  @Column(name = "descricao", length = 150, nullable = false)
  private String descricao;

  @FutureOrPresent(message = "{tarefa.dataEntrega.future-or-present}")
  @Column(name = "data_entrega", nullable = false)
  private LocalDate dataEntrega;

  @Column(name = "visivel", nullable = false)
  private boolean visivel;

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

