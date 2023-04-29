package br.com.uniciv.gestaotarefas.repositorios;

import br.com.uniciv.gestaotarefas.modelos.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepositorio extends JpaRepository<Tarefa, Integer> { }

