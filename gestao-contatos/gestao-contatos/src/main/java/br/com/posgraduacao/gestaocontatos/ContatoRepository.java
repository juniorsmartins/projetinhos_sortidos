package br.com.posgraduacao.gestaocontatos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> { }

