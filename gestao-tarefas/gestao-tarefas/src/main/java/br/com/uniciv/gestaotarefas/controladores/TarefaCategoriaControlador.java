package br.com.uniciv.gestaotarefas.controladores;

import br.com.uniciv.gestaotarefas.controladores.dtos.response.TarefaCategoriaResponse;
import br.com.uniciv.gestaotarefas.modelos.TarefaCategoria;
import br.com.uniciv.gestaotarefas.servicos.TarefaCategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = {"/categorias"})
public class TarefaCategoriaControlador {

  @Autowired
  private TarefaCategoriaService tarefaCategoriaService;

  @Autowired
  private ModelMapper mapper;

  @GetMapping
  public List<TarefaCategoriaResponse> listar() {
    List<TarefaCategoria> categorias = this.tarefaCategoriaService.listar();
    return categorias.stream()
      .map(categoria -> this.mapper.map(categoria, TarefaCategoriaResponse.class))
      .toList();
  }

  @GetMapping(path = "/{id}")
  public TarefaCategoriaResponse consultarPorId(@PathVariable Integer id) {
    return this.mapper
      .map(this.tarefaCategoriaService.consultarPorId(id), TarefaCategoriaResponse.class);
  }


}

