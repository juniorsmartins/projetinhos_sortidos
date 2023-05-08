package br.com.uniciv.gestaotarefas.servicos;

import br.com.uniciv.gestaotarefas.excecoes.TarefaStatusException;
import br.com.uniciv.gestaotarefas.modelos.enuns.TarefaStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TarefaServiceIntegrationTest {

  @Autowired
  private TarefaService tarefaService;

  @Test
  void deveIniciarTarefa() {
    var tarefa = this.tarefaService.iniciarTarefaPorId(1);
    Assertions.assertEquals(TarefaStatus.PROGRESSO, tarefa.getTarefaStatus());
  }

  @Test
  void naoDeveIniciarTarefaConcluida() {
    var tarefa = this.tarefaService.consultarPorId(1);
    tarefa.setTarefaStatus(TarefaStatus.CONCLUIDO);
    this.tarefaService.salvar(tarefa);

    Assertions.assertThrows(TarefaStatusException.class,
        () -> this.tarefaService.iniciarTarefaPorId(1));
  }
}

