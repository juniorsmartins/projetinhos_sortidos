package br.com.uniciv.gestaotarefas.security.models;

import br.com.uniciv.gestaotarefas.modelos.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(of = {"id"})
public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;

  private Integer id;

  private String username;

  @JsonIgnore
  private String senha;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(Integer id, String username, String senha, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.senha = senha;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(Usuario usuario) {
    List<GrantedAuthority> authorities = usuario.getRoles()
      .stream()
      .map(role -> new SimpleGrantedAuthority(role.getName().name()))
      .collect(Collectors.toList());

    return new UserDetailsImpl(usuario.getId(), usuario.getNome(), usuario.getSenha(), authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.senha;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
