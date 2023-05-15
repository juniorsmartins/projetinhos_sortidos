package br.com.uniciv.gestaotarefas.controladores;

import br.com.uniciv.gestaotarefas.controladores.dtos.response.TarefaCategoriaResponse;
import br.com.uniciv.gestaotarefas.controladores.dtos.response.UsuarioResponse;
import br.com.uniciv.gestaotarefas.controladores.hateoas.UsuarioHateoas;
import br.com.uniciv.gestaotarefas.servicos.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

