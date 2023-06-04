package br.com.uniciv.gestaotarefas.servicos;

import br.com.uniciv.gestaotarefas.modelos.Usuario;
import br.com.uniciv.gestaotarefas.repositorios.RoleRepository;
import br.com.uniciv.gestaotarefas.repositorios.UsuarioRepositorio;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepositorio usuarioRepositorio;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public Usuario consultarPorId(Integer id) {
    return this.usuarioRepositorio.findById(id)
      .orElseThrow(() -> new EntityNotFoundException());
  }

  public Usuario salvar(Usuario usuario) {
    return Optional.of(usuario)
      .map(this::encodarSenha)
      .map(this::getRoles)
      .map(this.usuarioRepositorio::save)
      .orElseThrow();
  }

  private Usuario encodarSenha(Usuario usuario) {
    var senha = usuario.getSenha();
    var senhaEncode = this.passwordEncoder.encode(senha);
    usuario.setSenha(senhaEncode);
    return usuario;
  }

  private Usuario getRoles(Usuario usuario) {
    var rolesBanco = usuario.getRoles()
      .stream()
      .map(role -> this.roleRepository.findByName(role.getName()).orElseThrow())
      .collect(Collectors.toSet());

      usuario.setRoles(rolesBanco);
    return usuario;
  }
}

