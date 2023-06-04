package br.com.uniciv.gestaotarefas.controladores.hateoas;

import br.com.uniciv.gestaotarefas.controladores.TarefaCategoriaControlador;
import br.com.uniciv.gestaotarefas.controladores.dtos.response.TarefaCategoriaResponse;
import br.com.uniciv.gestaotarefas.modelos.TarefaCategoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class TarefaCategoriaHateoas implements RepresentationModelAssembler<TarefaCategoria, EntityModel<TarefaCategoriaResponse>> {

  @Autowired
  private ModelMapper mapper;

  @Override
  public EntityModel<TarefaCategoriaResponse> toModel(TarefaCategoria categoria) {

    var dtoResponse = this.mapper.map(categoria, TarefaCategoriaResponse.class);

    EntityModel<TarefaCategoriaResponse> dtoComHateoas = EntityModel.of(dtoResponse,
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaCategoriaControlador.class)
        .consultarPorId(dtoResponse.getId())).withSelfRel()
    );

    return dtoComHateoas;
  }
}

