package br.com.uniciv.gestaotarefas.security.config;

import br.com.uniciv.gestaotarefas.security.service.AuthEntryPointJwt;
import br.com.uniciv.gestaotarefas.security.service.AuthTokenFilter;
import br.com.uniciv.gestaotarefas.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] PATHS = new String[] {"/tarefas/**", "/categorias/**", "/usuarios/**", "/"};

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  /**
   *  AUTENTICAÇÃO
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  /**
   *  AUTORIZAÇÃO
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
      .cors()
      .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
        .authorizeRequests()
        .antMatchers("/api/auth/**").permitAll()
        .antMatchers(HttpMethod.POST, PATHS)
          .hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, PATHS)
          .hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH, PATHS)
          .hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, PATHS)
          .hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, PATHS)
          .hasAnyRole("ADMIN", "USER")
        .antMatchers("/h2-console/**").permitAll()
        .anyRequest().authenticated()
      .and()
        .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    var configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:8090")); // origens permitidas
    configuration.setAllowedMethods(Arrays.asList("*")); // O asterisco * permite todos os verbos http (get, post, put ...)
    configuration.setAllowedHeaders(Arrays.asList("*")); // informações de cabeçalhos permitidas (O * permite todas)

    var source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}

