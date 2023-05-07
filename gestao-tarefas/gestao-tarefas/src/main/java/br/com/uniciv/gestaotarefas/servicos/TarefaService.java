package br.com.uniciv.gestaotarefas.servicos;

import br.com.uniciv.gestaotarefas.excecoes.TarefaStatusException;
import br.com.uniciv.gestaotarefas.modelos.Tarefa;
import br.com.uniciv.gestaotarefas.modelos.enuns.TarefaStatus;
import br.com.uniciv.gestaotarefas.repositorios.TarefaRepositorio;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

  @Autowired
  private TarefaRepositorio tarefaRepositorio;

  public List<Tarefa> listar() {
    return this.tarefaRepositorio.findAll();
  }

  public List<Tarefa> consultarPorDescricao(String descricao) {
    return this.tarefaRepositorio.findByDescricaoLike("%" + descricao + "%");
  }

  public Tarefa consultarPorId(Integer id) {
    return this.tarefaRepositorio.findById(id)
        .orElseThrow(() -> new EntityNotFoundException());
  }

  public Tarefa salvar(Tarefa tarefa) {
    return this.tarefaRepositorio.save(tarefa);
  }

  public void deletarPorId(Integer id) {
    this.tarefaRepositorio.deleteById(id);
  }

  public Tarefa iniciarTarefaPorId(Integer id) {
    Tarefa tarefa = this.consultarPorId(id);

    if (!TarefaStatus.ABERTO.equals(tarefa.getTarefaStatus())) {
      throw new TarefaStatusException();
    }

    tarefa.setTarefaStatus(TarefaStatus.PROGRESSO);

    return this.salvar(tarefa);
  }

  public Tarefa concluirTarefaPorId(Integer id) {
    Tarefa tarefa = this.consultarPorId(id);

    if (TarefaStatus.CANCELADO.equals(tarefa.getTarefaStatus())) {
      throw new TarefaStatusException();
    }

    tarefa.setTarefaStatus(TarefaStatus.CONCLUIDO);

    return this.salvar(tarefa);
  }
}

