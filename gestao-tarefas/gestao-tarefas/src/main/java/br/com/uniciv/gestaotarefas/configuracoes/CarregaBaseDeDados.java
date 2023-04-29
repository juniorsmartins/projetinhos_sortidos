package br.com.uniciv.gestaotarefas.configuracoes;

import br.com.uniciv.gestaotarefas.modelos.Tarefa;
import br.com.uniciv.gestaotarefas.modelos.TarefaCategoria;
import br.com.uniciv.gestaotarefas.modelos.Usuario;
import br.com.uniciv.gestaotarefas.modelos.enuns.TarefaStatus;
import br.com.uniciv.gestaotarefas.repositorios.TarefaCategoriaRepositorio;
import br.com.uniciv.gestaotarefas.repositorios.TarefaRepositorio;
import br.com.uniciv.gestaotarefas.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
@Profile("dev")
public class CarregaBaseDeDados {

  @Autowired
  private UsuarioRepositorio usuarioRepositorio;

  @Autowired
  private TarefaCategoriaRepositorio tarefaCategoriaRepositorio;

  @Autowired
  private TarefaRepositorio tarefaRepositorio;

  @Bean
  CommandLineRunner executar() {
    return args -> {
      var usuario = Usuario.builder()
        .nome("Admin")
        .senha("123456")
        .build();
      usuarioRepositorio.save(usuario);

      var tarefaCategoria = TarefaCategoria.builder()
        .nome("Estudos")
        .build();
      tarefaCategoriaRepositorio.save(tarefaCategoria);

      var tarefa = Tarefa.builder()
        .descricao("Aprender Spring Boot")
        .dataEntrega(LocalDate.now().plusDays(1))
        .tarefaStatus(TarefaStatus.ABERTO)
        .visivel(true)
        .tarefaCategoria(tarefaCategoria)
        .usuario(usuario)
        .build();
      tarefaRepositorio.save(tarefa);
    };
  }
}

