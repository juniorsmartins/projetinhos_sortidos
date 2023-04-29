package br.com.uniciv.gestaotarefas.modelos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

  @Column(name = "senha", length = 50, nullable = false)
  private String senha;

  @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
  private List<Tarefa> tarefas;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "usuarios_roles",
    joinColumns = @JoinColumn(name = "usuario_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
  private Set<Role> roles = new HashSet<>();
}

