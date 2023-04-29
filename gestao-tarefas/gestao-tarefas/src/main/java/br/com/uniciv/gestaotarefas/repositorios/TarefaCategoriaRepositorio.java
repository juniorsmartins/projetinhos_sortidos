package br.com.uniciv.gestaotarefas.repositorios;

import br.com.uniciv.gestaotarefas.modelos.TarefaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaCategoriaRepositorio extends JpaRepository<TarefaCategoria, Integer> { }

