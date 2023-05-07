package br.com.uniciv.gestaotarefas.excecoes;

public final class TarefaStatusException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public TarefaStatusException() {
    super();
  }

  public TarefaStatusException(String mensagem) {
    super(mensagem);
  }

  public TarefaStatusException(Throwable causa) {
    super(causa);
  }

  public TarefaStatusException(String mensagem, Throwable causa) {
    super(mensagem, causa);
  }
}

