package br.com.uniciv.gestaotarefas.security.controller;

import br.com.uniciv.gestaotarefas.security.controller.dtos.LoginRequest;
import br.com.uniciv.gestaotarefas.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

  @Autowired
  private LoginService loginService;

  @PostMapping(path = "/login")
  public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {

    var jwtResponse = this.loginService.autenticarUsuario(loginRequest.getNome(), loginRequest.getSenha());

    return ResponseEntity
      .ok(jwtResponse);
  }
}

