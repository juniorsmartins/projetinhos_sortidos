package br.com.uniciv.gestaotarefas.controladores;

import br.com.uniciv.gestaotarefas.modelos.Tarefa;
import br.com.uniciv.gestaotarefas.repositorios.TarefaRepositorio;
import br.com.uniciv.gestaotarefas.servicos.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TarefaControlador {

  @Autowired
  private TarefaService tarefaService;

  @GetMapping("/tarefas")
  public List<Tarefa> listar(@RequestParam Map<String, String> parametros) {

    if (parametros.isEmpty()) {
      return this.tarefaService.listar();
    }

    var descricao = parametros.get("descricao");
    return this.tarefaService.consultarPorDescricao(descricao);
  }

  @GetMapping("/tarefas/{id}")
  public Tarefa consultarPorId(@PathVariable Integer id) {
    return this.tarefaService.consultarPorId(id);
  }

  @PostMapping("/tarefas")
  public Tarefa salvar(@RequestBody @Valid Tarefa tarefa) {
    return this.tarefaService.salvar(tarefa);
  }

  @DeleteMapping("/tarefas/{id}")
  public void excluir(@PathVariable Integer id) {
    this.tarefaService.deletarPorId(id);
  }
}

