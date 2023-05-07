package br.com.uniciv.gestaotarefas.servicos;

import br.com.uniciv.gestaotarefas.excecoes.TarefaStatusException;
import br.com.uniciv.gestaotarefas.modelos.Tarefa;
import br.com.uniciv.gestaotarefas.modelos.enuns.TarefaStatus;
import br.com.uniciv.gestaotarefas.repositorios.TarefaRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TarefaServiceUnitTest {

  @Mock
  private TarefaRepositorio tarefaRepositorio;

  @InjectMocks // diz para injetar o Mock (acima) no service
  private TarefaService tarefaService;

  @Test
  void naoDeveConcluirTarefaCancelada() {

    var idTarefa = 1;
    var tarefa = new Tarefa();
    tarefa.setId(idTarefa);
    tarefa.setDescricao("Teste 01");
    tarefa.setTarefaStatus(TarefaStatus.CANCELADO);

    Mockito.when(this.tarefaRepositorio.findById(Mockito.any()))
        .thenReturn(Optional.of(tarefa));

    Assertions.assertThrows(TarefaStatusException.class,
        () -> this.tarefaService.concluirTarefaPorId(idTarefa));
  }

}