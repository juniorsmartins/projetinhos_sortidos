package br.com.uniciv.gestaotarefas.controladores;

import br.com.uniciv.gestaotarefas.controladores.dtos.request.TarefaRequest;
import br.com.uniciv.gestaotarefas.controladores.dtos.response.TarefaResponse;
import br.com.uniciv.gestaotarefas.controladores.hateoas.TarefaHateoas;
import br.com.uniciv.gestaotarefas.modelos.Tarefa;
import br.com.uniciv.gestaotarefas.servicos.TarefaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = {"/tarefas"})
public class TarefaControlador {

  @Autowired
  private TarefaService tarefaService;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private TarefaHateoas tarefaHateoas;

  @GetMapping
  public CollectionModel<EntityModel<TarefaResponse>> listar(@RequestParam Map<String, String> parametros) {
    List<Tarefa> tarefas = new ArrayList<>();

    if (parametros.isEmpty()) {
      tarefas = this.tarefaService.listar();
    } else {
      var descricao = parametros.get("descricao");
      tarefas = this.tarefaService.consultarPorDescricao(descricao);
    }

    var tarefasHaeoas = tarefas.stream()
      .map(tarefaHateoas::toModel)
      .toList();

    return CollectionModel.of(tarefasHaeoas,
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaControlador.class)
        .listar(new HashMap<>())).withSelfRel());
  }

  @GetMapping("/{id}")
  public EntityModel<TarefaResponse> consultarPorId(@PathVariable Integer id) {
    var tarefa = this.tarefaService.consultarPorId(id);

    return this.tarefaHateoas.toModel(tarefa);
  }

  @PostMapping
  public TarefaResponse salvar(@RequestBody @Valid TarefaRequest tarefaRequest) {
    var tarefa = this.mapper.map(tarefaRequest, Tarefa.class);

    tarefa = this.tarefaService.salvar(tarefa);

    return this.mapper.map(tarefa, TarefaResponse.class);
  }

  @PutMapping(path = "/{id}/iniciar")
  public EntityModel<TarefaResponse> iniciarTarefa(@PathVariable("id") Integer tarefaId) {
    var tarefa = this.tarefaService.iniciarTarefaPorId(tarefaId);
    return this.tarefaHateoas.toModel(tarefa);
  }

  @PutMapping(path = "/{id}/concluir")
  public EntityModel<TarefaResponse> concluirTarefa(@PathVariable("id") Integer tarefaId) {
    var tarefa = this.tarefaService.concluirTarefaPorId(tarefaId);
    return this.tarefaHateoas.toModel(tarefa);
  }

  @PutMapping(path = "/{id}/cancelar")
  public EntityModel<TarefaResponse> cancelarTarefa(@PathVariable("id") Integer tarefaId) {
    var tarefa = this.tarefaService.cancelarTarefaPorId(tarefaId);
    return this.tarefaHateoas.toModel(tarefa);
  }

  @DeleteMapping("/{id}")
  public void excluir(@PathVariable Integer id) {
    this.tarefaService.deletarPorId(id);
  }
}

