package Classes;

public class Token {

    private int codToken;
    private String token;
    private String nome;
    private int linha;

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public Token(int codigo, String tipo) {
        this.codToken = codigo;
        this.token = tipo;
    }

    public Token(int codigo, String tipo, String sentenca) {
        this.codToken = codigo;
        this.token = tipo;
        this.nome = sentenca;
    }

    public Token(int codigo, String tipo, String sentenca, int linha) {
        this.codToken = codigo;
        this.token = tipo;
        this.nome = sentenca;
        this.linha = linha;
    }

    public int getCodToken() {
        return codToken;
    }

    public void setCodToken(int codToken) {
        this.codToken = codToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
