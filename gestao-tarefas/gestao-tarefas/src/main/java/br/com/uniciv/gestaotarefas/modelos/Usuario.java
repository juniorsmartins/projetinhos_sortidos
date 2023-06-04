package br.com.uniciv.gestaotarefas.modelos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuarios")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "nome", length = 100, nullable = false)
  private String nome;

  @Column(name = "senha", nullable = false)
  private String senha;

  @JsonIgnore
  @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
  private List<Tarefa> tarefas;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "usuarios_roles",
    joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", columnDefinition = "INTEGER",
        nullable = false, foreignKey = @ForeignKey(name = "fk_usuarios_roles_usuario_id")),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", columnDefinition = "INTEGER",
        nullable = false, foreignKey = @ForeignKey(name = "fk_usuarios_roles_role_id")))
  private Set<Role> roles = new HashSet<>();
}

