package br.com.uniciv.gestaotarefas.repositorios;

import br.com.uniciv.gestaotarefas.modelos.Role;
import br.com.uniciv.gestaotarefas.modelos.enuns.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

  Optional<Role> findByName(ERole name);
}

