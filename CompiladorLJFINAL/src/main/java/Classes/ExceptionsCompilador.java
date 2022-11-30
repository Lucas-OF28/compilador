package Classes;

public class ExceptionsCompilador extends Exception {

  public ExceptionsCompilador() {
    super("Erro");
  }

  public ExceptionsCompilador(String mensagem) {
    super(mensagem);
  }
}
