package br.com.uniciv.gestaotarefas.controladores;

import br.com.uniciv.gestaotarefas.controladores.dtos.request.TarefaRequest;
import br.com.uniciv.gestaotarefas.controladores.dtos.response.TarefaResponse;
import br.com.uniciv.gestaotarefas.modelos.Tarefa;
import br.com.uniciv.gestaotarefas.servicos.TarefaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
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

  @GetMapping
  public List<TarefaResponse> listar(@RequestParam Map<String, String> parametros) {

    if (parametros.isEmpty()) {
      return this.tarefaService.listar().stream()
          .map(entidade -> this.mapper.map(entidade, TarefaResponse.class))
          .sorted(Comparator.comparing(TarefaResponse::getId).reversed())
          .toList();
    }

    var descricao = parametros.get("descricao");
    var lista = this.tarefaService.consultarPorDescricao(descricao);

    return lista.stream()
        .map(entidade -> this.mapper.map(entidade, TarefaResponse.class))
        .sorted(Comparator.comparing(TarefaResponse::getId).reversed())
        .toList();
  }

  @GetMapping("/{id}")
  public EntityModel<TarefaResponse> consultarPorId(@PathVariable Integer id) {
    var tarefa = this.tarefaService.consultarPorId(id);
    var resultado = this.mapper.map(tarefa, TarefaResponse.class);

    EntityModel<TarefaResponse> response = EntityModel.of(resultado, WebMvcLinkBuilder
      .linkTo(WebMvcLinkBuilder.methodOn(TarefaControlador.class).consultarPorId(id)).withSelfRel(),
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
        TarefaControlador.class).listar(new HashMap<>())).withRel("tarefas"),
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
        TarefaCategoriaControlador.class).consultarPorId(tarefa.getTarefaCategoria().getId())).withRel("categoria"),
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
        UsuarioControlador.class).consultarPorId(tarefa.getUsuario().getId())).withRel("usuario"));

    return response;
  }

  @PostMapping
  public TarefaResponse salvar(@RequestBody @Valid TarefaRequest tarefaRequest) {
    var tarefa = this.mapper.map(tarefaRequest, Tarefa.class);

    tarefa = this.tarefaService.salvar(tarefa);

    return this.mapper.map(tarefa, TarefaResponse.class);
  }

  @DeleteMapping("/{id}")
  public void excluir(@PathVariable Integer id) {
    this.tarefaService.deletarPorId(id);
  }
}

