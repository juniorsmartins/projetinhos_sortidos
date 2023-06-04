package br.com.uniciv.gestaotarefas.controladores.hateoas;

import br.com.uniciv.gestaotarefas.controladores.UsuarioControlador;
import br.com.uniciv.gestaotarefas.controladores.dtos.response.UsuarioResponse;
import br.com.uniciv.gestaotarefas.modelos.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioHateoas implements RepresentationModelAssembler<Usuario, EntityModel<UsuarioResponse>> {

  @Autowired
  private ModelMapper mapper;


  @Override
  public EntityModel<UsuarioResponse> toModel(Usuario entity) {

    var dtoResponse = this.mapper.map(entity, UsuarioResponse.class);

    EntityModel<UsuarioResponse> dtoComHateoas = EntityModel.of(dtoResponse,
      WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControlador.class)
        .consultarPorId(dtoResponse.getId())).withSelfRel());

    return null;
  }
}

