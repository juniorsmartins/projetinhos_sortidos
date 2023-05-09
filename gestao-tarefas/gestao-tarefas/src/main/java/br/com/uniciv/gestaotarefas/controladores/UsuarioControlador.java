package br.com.uniciv.gestaotarefas.controladores;

import br.com.uniciv.gestaotarefas.controladores.dtos.response.TarefaCategoriaResponse;
import br.com.uniciv.gestaotarefas.controladores.dtos.response.UsuarioResponse;
import br.com.uniciv.gestaotarefas.servicos.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

  @GetMapping(path = "/{id}")
  public UsuarioResponse consultarPorId(@PathVariable Integer id) {
    return this.mapper
      .map(this.usuarioService.consultarPorId(id), UsuarioResponse.class);
  }
}

