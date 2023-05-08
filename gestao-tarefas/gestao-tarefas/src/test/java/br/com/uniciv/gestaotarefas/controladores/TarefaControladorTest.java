package br.com.uniciv.gestaotarefas.controladores;

import br.com.uniciv.gestaotarefas.controladores.dtos.response.TarefaResponse;
import br.com.uniciv.gestaotarefas.modelos.Tarefa;
import br.com.uniciv.gestaotarefas.modelos.TarefaCategoria;
import br.com.uniciv.gestaotarefas.modelos.Usuario;
import br.com.uniciv.gestaotarefas.modelos.enuns.TarefaStatus;
import br.com.uniciv.gestaotarefas.servicos.TarefaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TarefaControladorTest {

  @Autowired
  private TarefaControlador tarefaControlador;

  @MockBean
  private TarefaService tarefaService;

  @Test
  void validaTarefaResponse() {
    int idTarefa = 999;

    Tarefa tarefa = new Tarefa();
    tarefa.setId(idTarefa);
    tarefa.setTarefaStatus(TarefaStatus.ABERTO);

    Usuario usuario = new Usuario();
    usuario.setId(1);
    tarefa.setUsuario(usuario);

    TarefaCategoria tarefaCategoria = new TarefaCategoria();
    tarefaCategoria.setId(2);
    tarefa.setTarefaCategoria(tarefaCategoria);

    Mockito.when(this.tarefaService.consultarPorId(idTarefa)).thenReturn(tarefa);

    EntityModel<TarefaResponse> response = this.tarefaControlador.consultarPorId(idTarefa);
    TarefaResponse tarefaResponse = response.getContent();

    Assertions.assertEquals(idTarefa, tarefaResponse.getId());
    Assertions.assertEquals(2, tarefaResponse.getTarefaCategoria().getId());
    Assertions.assertEquals(1, tarefaResponse.getUsuario().getId());
    Assertions.assertEquals(TarefaStatus.ABERTO.name(), tarefaResponse.getTarefaStatus().name());
  }

}

