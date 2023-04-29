package br.com.uniciv.gestaotarefas.controladores;

import br.com.uniciv.gestaotarefas.modelos.Tarefa;
import br.com.uniciv.gestaotarefas.repositorios.TarefaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TarefaControlador {

  @Autowired
  private TarefaRepositorio tarefaRepositorio;

  @GetMapping("/tarefas")
  public List<Tarefa> listar() {
    return this.tarefaRepositorio.findAll();
  }

  @GetMapping("/tarefas/{id}")
  public Tarefa consultarPorId(@PathVariable Integer id) {
    return this.tarefaRepositorio.findById(id)
        .orElse(null);
  }

  @PostMapping("/tarefas")
  public Tarefa salvar(@RequestBody Tarefa tarefa) {
    return this.tarefaRepositorio.save(tarefa);
  }

  @DeleteMapping("/tarefas/{id}")
  public void excluir(@PathVariable Integer id) {
    this.tarefaRepositorio.deleteById(id);
  }
}

