package br.com.uniciv.gestaotarefas.configuracoes;

import br.com.uniciv.gestaotarefas.modelos.Role;
import br.com.uniciv.gestaotarefas.modelos.Tarefa;
import br.com.uniciv.gestaotarefas.modelos.TarefaCategoria;
import br.com.uniciv.gestaotarefas.modelos.Usuario;
import br.com.uniciv.gestaotarefas.modelos.enuns.ERole;
import br.com.uniciv.gestaotarefas.modelos.enuns.TarefaStatus;
import br.com.uniciv.gestaotarefas.repositorios.RoleRepository;
import br.com.uniciv.gestaotarefas.repositorios.TarefaCategoriaRepositorio;
import br.com.uniciv.gestaotarefas.repositorios.TarefaRepositorio;
import br.com.uniciv.gestaotarefas.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDate;
import java.util.Set;

@Configuration
@Profile("dev")
public class CarregaBaseDeDados {

  @Autowired
  private UsuarioRepositorio usuarioRepositorio;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private TarefaCategoriaRepositorio tarefaCategoriaRepositorio;

  @Autowired
  private TarefaRepositorio tarefaRepositorio;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Bean
  CommandLineRunner executar() {
    return args -> {
      var roleAdmin = Role.builder()
        .name(ERole.ROLE_ADMIN)
        .build();
      roleAdmin = this.roleRepository.save(roleAdmin);

      var usuario = Usuario.builder()
        .nome("Admin")
        .senha(this.passwordEncoder.encode("123456"))
        .roles(Set.of(roleAdmin))
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

      var tarefa2 = Tarefa.builder()
        .descricao("Aprender Spring Data Jpa")
        .dataEntrega(LocalDate.now().plusDays(10))
        .tarefaStatus(TarefaStatus.ABERTO)
        .visivel(true)
        .tarefaCategoria(tarefaCategoria)
        .usuario(usuario)
        .build();
      tarefaRepositorio.save(tarefa2);
    };
  }
}

