package Classes;

import java.util.ArrayList;

public class Dicionario {

  ArrayList<Token> dicionario = new ArrayList<Token>();
  int[] CodigosReservados = {1,2,3,4,10,13,14,16,17,18,19,20,21,12,23,22,24};

  public ArrayList<Token> getDicionario() {
    return dicionario;
  }

  public Dicionario() {
    dicionario.add(new Token(1, "while"));
    dicionario.add(new Token(2, "void"));
    dicionario.add(new Token(3, "string"));
    dicionario.add(new Token(4, "return"));
    dicionario.add(new Token(5, "numeroInteiro"));
    dicionario.add(new Token(6, "numeroFloat"));
    dicionario.add(new Token(7, "nomeVariavel"));
    dicionario.add(new Token(8, "nomeDoChar"));
    dicionario.add(new Token(9, "nomeDaString"));
    dicionario.add(new Token(10, "main"));
    dicionario.add(new Token(11, "literal"));
    dicionario.add(new Token(12, "integer"));
    dicionario.add(new Token(13, "inicio"));
    dicionario.add(new Token(14, "if"));
    dicionario.add(new Token(15, "î"));
    dicionario.add(new Token(16, "for"));
    dicionario.add(new Token(17, "float"));
    dicionario.add(new Token(18, "fim"));
    dicionario.add(new Token(19, "else"));
    dicionario.add(new Token(20, "do"));
    dicionario.add(new Token(21, "cout"));
    dicionario.add(new Token(22, "cin"));
    dicionario.add(new Token(23, "char"));
    dicionario.add(new Token(24, "callFuncao"));
    dicionario.add(new Token(25, ">>"));
    dicionario.add(new Token(26, ">="));
    dicionario.add(new Token(27, ">"));
    dicionario.add(new Token(28, "=="));
    dicionario.add(new Token(29, "="));
    dicionario.add(new Token(30, "<="));
    dicionario.add(new Token(31, "<<"));
    dicionario.add(new Token(32, "<"));
    dicionario.add(new Token(33, "++"));
    dicionario.add(new Token(34, "+"));
    dicionario.add(new Token(35, "}"));
    dicionario.add(new Token(36, "{"));
    dicionario.add(new Token(37, ";"));
    dicionario.add(new Token(38, ":"));
    dicionario.add(new Token(39, "/"));
    dicionario.add(new Token(40, ","));
    dicionario.add(new Token(41, "*"));
    dicionario.add(new Token(42, ")"));
    dicionario.add(new Token(43, "("));
    dicionario.add(new Token(44, "$"));
    dicionario.add(new Token(45, "!="));
    dicionario.add(new Token(46, "--"));
    dicionario.add(new Token(47, "-"));

    dicionario.add(new Token(48, "BLOCO"));
    dicionario.add(new Token(49, "DCLVAR"));
    dicionario.add(new Token(50, "DCLFUNC"));
    dicionario.add(new Token(51, "CORPO"));
    dicionario.add(new Token(52, "REPIDENT"));
    dicionario.add(new Token(53, "TIPO"));
    dicionario.add(new Token(54, "LDVAR"));
    dicionario.add(new Token(55, "LID"));
    dicionario.add(new Token(56, "TIPO_RETORNO"));
    dicionario.add(new Token(57, "DEFPAR"));
    dicionario.add(new Token(58, "VALORRETORNO"));
    dicionario.add(new Token(59, "PARAM"));
    dicionario.add(new Token(60, "LPARAM"));
    dicionario.add(new Token(61, "COMANDO"));
    dicionario.add(new Token(62, "REPCOMANDO"));
    dicionario.add(new Token(63, "EXPRESSAO"));
    dicionario.add(new Token(64, "PARAMETROS"));
    dicionario.add(new Token(65, "TPARAM"));
    dicionario.add(new Token(66, "REPPAR"));
    dicionario.add(new Token(67, "COMPARACAO"));
    dicionario.add(new Token(68, "ELSEPARTE"));
    dicionario.add(new Token(69, "CONTCOMPARACAO"));
    dicionario.add(new Token(70, "INCREMENTO"));
    dicionario.add(new Token(71, "SEQCOUT"));
    dicionario.add(new Token(72, "SEQUENCIA"));
    dicionario.add(new Token(73, "TERMO"));
    dicionario.add(new Token(74, "REPEXP"));
    dicionario.add(new Token(75, "FATOR"));
    dicionario.add(new Token(76, "REPTERMO"));

    // ações semanticas
    dicionario.add(new Token(100, "SEMANTICA 100"));
    dicionario.add(new Token(101, "SEMANTICA 101"));
    dicionario.add(new Token(102, "SEMANTICA 102"));
    dicionario.add(new Token(103, "SEMANTICA 103"));
    dicionario.add(new Token(104, "SEMANTICA 104"));

    // palavras reservadas
    
    // Gus: Deprecado, adição de códigos movido para declaração de array

    // CodigosReservados[0] = 1;
    // CodigosReservados[1] = 2;
    // CodigosReservados[2] = 3;
    // CodigosReservados[3] = 4;
    // CodigosReservados[4] = 10;
    // CodigosReservados[5] = 13;
    // CodigosReservados[6] = 14;
    // CodigosReservados[7] = 16;
    // CodigosReservados[8] = 17;
    // CodigosReservados[9] = 18;
    // CodigosReservados[10] = 19;
    // CodigosReservados[11] = 20;
    // CodigosReservados[12] = 21;
    // CodigosReservados[13] = 12;
    // CodigosReservados[14] = 23;
    // CodigosReservados[15] = 22;
    // CodigosReservados[16] = 24;
  }

  public Token retornaTokenDicionario(String tipo) {
    for (int i = 0; i < dicionario.size(); i++) {
      if (dicionario.get(i).getToken().equals(tipo)) {
        return dicionario.get(i);
      }
    }
    return null;
  }

  public Token retornaTokenDicionarioCodigo(int codigo) {
    for (int i = 0; i < dicionario.size(); i++) {
      if (dicionario.get(i).getCodToken() == codigo) {
        return dicionario.get(i);
      }
    }
    return null;
  }

  public boolean verificaPalavraReservada(int codToken) {
    for (int i = 0; i < CodigosReservados.length; i++) {
      if (CodigosReservados[i] == codToken) {
        return true;
      }
    }
    return false;
  }
}
