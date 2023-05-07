package br.com.uniciv.gestaotarefas.repositorios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TarefaRepositorioUnitTest {

  @Autowired
  private TarefaRepositorio tarefaRepositorio;

  @Test
  void testeFindByNomeCategoria() {
    var tarefas = this.tarefaRepositorio.findByNomeCategoria("Estudos");

    Assertions.assertEquals(2, tarefas.size());
  }

  @Test
  void testeTarefasPorCategoria() {
    var tarefas = this.tarefaRepositorio.tarefasPorCategoria("Estudos");

    Assertions.assertEquals(2, tarefas.size());
  }

  @Test
  void testeFindByNomeDeCategoria() {
    var tarefas = this.tarefaRepositorio.findByNomeDeCategoria("Estudos");

    Assertions.assertEquals(2, tarefas.size());
  }
}