package br.com.uniciv.gestaotarefas.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  // O SecurityFilterChain define quais caminhos de URL devem ser protegidos e quais não devem.
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
      .authorizeHttpRequests((requests) -> requests
        .requestMatchers("/", "/home").permitAll()
        .anyRequest().authenticated())
      .formLogin((form) -> form
        .loginPage("/login")
        .permitAll())
      .logout((logout) -> logout
        .permitAll());

    return httpSecurity.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {

    UserDetails user = User.withDefaultPasswordEncoder()
      .username("user")
      .password("123")
      .roles("USER")
      .build();

    return new InMemoryUserDetailsManager(user);
  }
}
