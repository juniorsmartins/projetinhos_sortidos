package br.com.uniciv.gestaotarefas.servicos;

import br.com.uniciv.gestaotarefas.modelos.TarefaCategoria;
import br.com.uniciv.gestaotarefas.repositorios.TarefaCategoriaRepositorio;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaCategoriaService {

  @Autowired
  private TarefaCategoriaRepositorio tarefaCategoriaRepositorio;

  public List<TarefaCategoria> listar() {
    return this.tarefaCategoriaRepositorio.findAll();
  }

  public List<TarefaCategoria> consultarPorDescricao(String nome) {
    return this.tarefaCategoriaRepositorio.findByNomeLike("%" + nome + "%");
  }

  public TarefaCategoria consultarPorId(Integer id) {
    return this.tarefaCategoriaRepositorio.findById(id)
        .orElseThrow(() -> new EntityNotFoundException());
  }

  public TarefaCategoria salvar(TarefaCategoria tarefaCategoria) {
    return this.tarefaCategoriaRepositorio.save(tarefaCategoria);
  }

  public void deletarPorId(Integer id) {
    this.tarefaCategoriaRepositorio.deleteById(id);
  }
}

