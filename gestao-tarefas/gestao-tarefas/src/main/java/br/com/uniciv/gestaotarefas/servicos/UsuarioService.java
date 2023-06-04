package br.com.uniciv.gestaotarefas.servicos;

import br.com.uniciv.gestaotarefas.modelos.Usuario;
import br.com.uniciv.gestaotarefas.repositorios.UsuarioRepositorio;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepositorio usuarioRepositorio;

  public Usuario consultarPorId(Integer id) {
    return this.usuarioRepositorio.findById(id)
      .orElseThrow(() -> new EntityNotFoundException());
  }
}

