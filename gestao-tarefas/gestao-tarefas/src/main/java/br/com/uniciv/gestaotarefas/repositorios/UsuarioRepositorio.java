package br.com.uniciv.gestaotarefas.repositorios;

import br.com.uniciv.gestaotarefas.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

  Optional<Usuario> findByNome(String nome);
}

