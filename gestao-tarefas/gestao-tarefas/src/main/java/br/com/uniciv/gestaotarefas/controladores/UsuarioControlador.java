package br.com.uniciv.gestaotarefas.controladores;

import br.com.uniciv.gestaotarefas.controladores.dtos.request.UsuarioRequest;
import br.com.uniciv.gestaotarefas.controladores.dtos.response.UsuarioResponse;
import br.com.uniciv.gestaotarefas.controladores.hateoas.UsuarioHateoas;
import br.com.uniciv.gestaotarefas.modelos.Usuario;
import br.com.uniciv.gestaotarefas.servicos.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = {"/usuarios"})
public class UsuarioControlador {

  @Autowired
  private UsuarioService usuarioService;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private UsuarioHateoas hateoas;

  @GetMapping(path = "/{id}")
  public EntityModel<UsuarioResponse> consultarPorId(@PathVariable Integer id) {

    var usuario = this.usuarioService.consultarPorId(id);
    var usuarioComHateoas = this.hateoas.toModel(usuario);

    return usuarioComHateoas;
  }

  @PostMapping
  public ResponseEntity<EntityModel<UsuarioResponse>> cadastrar(@RequestBody @Valid final UsuarioRequest dtoRequest) {

    var response = Optional.of(dtoRequest)
      .map(dto -> this.mapper.map(dto, Usuario.class))
      .map(user -> this.usuarioService.salvar(user))
      .map(this.hateoas::toModel)
      .orElseThrow();

    return ResponseEntity
      .created(response.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(response);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<EntityModel<UsuarioResponse>> atualizar(@PathVariable(name = "id") final Integer id,
                                                                @RequestBody @Valid final UsuarioRequest dtoRequest) {

    var response = Optional.of(dtoRequest)
      .map(dto -> this.mapper.map(dto, Usuario.class))
      .map(user -> this.usuarioService.atualizar(id, user))
      .map(this.hateoas::toModel)
      .orElseThrow();

    return ResponseEntity
      .ok()
      .body(response);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Object> deletar(@PathVariable(name = "id") final Integer id) {

    this.usuarioService.deletarPorId(id);

    return ResponseEntity
      .noContent()
      .build();
  }
}

