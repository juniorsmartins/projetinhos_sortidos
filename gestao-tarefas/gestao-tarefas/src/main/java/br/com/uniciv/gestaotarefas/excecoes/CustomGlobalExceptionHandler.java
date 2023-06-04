package br.com.uniciv.gestaotarefas.excecoes;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;

@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public MensagemRetornoErro entityNotFoundHandler(EntityNotFoundException entityNotFoundException) {

    return MensagemRetornoErro.builder()
      .mensagem("Recurso não encontrado. Id inexistente!")
      .dataHora(OffsetDateTime.now())
      .build();
  }

  @ExceptionHandler(TarefaStatusException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public ResponseEntity<?> alteraStatusTarefaHandler(TarefaStatusException tarefaStatusException) {

    var status = HttpStatus.METHOD_NOT_ALLOWED;
    var header = HttpHeaders.CONTENT_TYPE;
    var mediaTypes = MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE;
    var problema = Problem.create().withTitle("Método não permitido.")
      .withDetail("Você não pode realizar esta operação: " + tarefaStatusException.getMessage());

    return ResponseEntity
      .status(status)
      .header(header, mediaTypes)
      .body(problema);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                HttpStatusCode status, WebRequest request) {

    var erros = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(erro -> MensagemRetornoErro.builder()
        .anotacao(erro.getCode())
        .campo(erro.getField())
        .mensagem(erro.getDefaultMessage())
        .dataHora(OffsetDateTime.now())
        .build())
      .toList();

    return ResponseEntity
      .badRequest()
      .body(erros);
  }
}

