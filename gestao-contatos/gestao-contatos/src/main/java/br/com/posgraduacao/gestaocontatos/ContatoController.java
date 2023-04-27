package br.com.posgraduacao.gestaocontatos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContatoController {

  @Autowired
  private ContatoRepository repository;

  @GetMapping(path = {"/contatos"})
  public List<Contato> todosContatos() {
    return this.repository.findAll();
  }

  @PostMapping(path = {"/contatos"})
  public Contato salvar(@RequestBody Contato contato) {
    return this.repository.save(contato);
  }
}

