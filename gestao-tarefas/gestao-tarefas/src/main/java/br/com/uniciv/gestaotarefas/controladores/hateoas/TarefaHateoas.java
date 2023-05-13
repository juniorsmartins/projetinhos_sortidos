package br.com.uniciv.gestaotarefas.controladores.hateoas;

import br.com.uniciv.gestaotarefas.controladores.TarefaCategoriaControlador;
import br.com.uniciv.gestaotarefas.controladores.TarefaControlador;
import br.com.uniciv.gestaotarefas.controladores.UsuarioControlador;
import br.com.uniciv.gestaotarefas.controladores.dtos.response.TarefaResponse;
import br.com.uniciv.gestaotarefas.modelos.Tarefa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TarefaHateoas implements RepresentationModelAssembler<Tarefa, EntityModel<TarefaResponse>> {

  @Autowired
  private ModelMapper mapper;

  @Override
  public EntityModel<TarefaResponse> toModel(Tarefa tarefa) {

    var dtoResponse = this.mapper.map(tarefa, TarefaResponse.class);

    EntityModel<TarefaResponse> dtoComHateoas = EntityModel.of(dtoResponse,
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaControlador.class).consultarPorId(dtoResponse.getId())).withSelfRel(),
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaControlador.class).listar(new HashMap<>())).withRel("tarefas"),
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaCategoriaControlador.class).consultarPorId(tarefa.getTarefaCategoria().getId())).withRel("categoria"),
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControlador.class).consultarPorId(tarefa.getUsuario().getId())).withRel("usuario"));

    return dtoComHateoas;
  }
}

