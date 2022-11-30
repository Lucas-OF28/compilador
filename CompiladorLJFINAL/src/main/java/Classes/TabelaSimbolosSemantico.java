package Classes;

public class TabelaSimbolosSemantico {

    Token tokenVariavel;
    Token tokenTipoVariavel;

    public TabelaSimbolosSemantico() {
    }

    public TabelaSimbolosSemantico(Token variavel, Token tipo) {
        this.tokenTipoVariavel = variavel;
        this.tokenTipoVariavel = tipo;
    }

    public Token getTokenVariavel() {
        return tokenVariavel;
    }

    public void setTokenVariavel(Token tokenVariavel) {
        this.tokenVariavel = tokenVariavel;
    }

    public Token getTokenTipoVariavel() {
        return tokenTipoVariavel;
    }

    public void setTokenTipoVariavel(Token tokenTipoVariavel) {
        this.tokenTipoVariavel = tokenTipoVariavel;
    }
}
