package br.com.uniciv.gestaotarefas.repositorios;

import br.com.uniciv.gestaotarefas.modelos.Tarefa;
import br.com.uniciv.gestaotarefas.modelos.TarefaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TarefaRepositorio extends JpaRepository<Tarefa, Integer> {

  // ---------- Derived Queries ---------- //
  List<Tarefa> findByDescricao(String descricao);

  List<Tarefa> findByDescricaoLike(String descricao);


  // ---------- JPQL ---------- //
  @Query("select t from Tarefa t inner join t.tarefaCategoria c where c.nome = ?1")
  List<Tarefa> findByNomeCategoria(String nomeCategoria);


  // ---------- Native Query ---------- //
  @Query(value = "select t.* from tarefas t inner join tarefas_categoria tc on t.tarefa_categoria_id = tc.id where tc.nome = ?1", nativeQuery = true)
  List<Tarefa> findByNomeDeCategoria(String nomeDeCategoria);


  // ---------- Named Query (anotação na classe) ---------- //
  List<Tarefa> tarefasPorCategoria(String tarefaCategoria);
}

