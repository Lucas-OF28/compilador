package Analisadores;

import java.util.ArrayList;

import Classes.ExceptionsCompilador;
import Classes.TabelaSimbolosSemantico;
import Classes.Token;

public class AnalisadorSemantico {

  ArrayList<TabelaSimbolosSemantico> lista = new ArrayList<>();
  ArrayList<Token> listaComparacao = new ArrayList<>();

  public void AcoesSemantica(Token tokenAtual, int regraSemantica) throws ExceptionsCompilador {
    switch (regraSemantica) {
      case 100:
        regraSemantica100(tokenAtual);
        break;
      case 101:
        regraSemantica101(tokenAtual);
        break;
      case 102:
        regraSemantica102(tokenAtual);
        break;
      case 103:
        regraSemantica103();
        break;
      case 104:
        regraSemantica104(tokenAtual);
        break;
      default:
        throw new ExceptionsCompilador("[Erro Semantico] regra nao existe");
    }
  }

  private void regraSemantica100(Token token) throws ExceptionsCompilador {
    TabelaSimbolosSemantico simboloTabela = new TabelaSimbolosSemantico();

    if (lista.isEmpty()) {

      simboloTabela.setTokenVariavel(token);

      lista.add(simboloTabela);
    } else {
      if (pesquisaNomeToken(token)) {
        throw new ExceptionsCompilador("[Erro Semantico] variavel Ambigua: " + token.getNome());
      } else {
        simboloTabela.setTokenVariavel(token);
        lista.add(simboloTabela);
      }
    }
  }

  private void regraSemantica101(Token token) throws ExceptionsCompilador {
         //token vai ser um tipo e as variavem sem tipo recebem tipo
    colocarTipoTodasVariaveisSemTipo(token);
  }

  private void regraSemantica102(Token token) throws ExceptionsCompilador {
    this.listaComparacao.add(token);
  }

  private void regraSemantica103() throws ExceptionsCompilador {
      //pega  itens da lista ,pegar o tipo na matriz e armazena o tipo        
      
    verificaTipoListaComparacao();
  }

  private void regraSemantica104(Token token) throws ExceptionsCompilador {
    for (int i = 0; i < lista.size(); i++) {
      if (lista.get(i).getTokenVariavel().getNome().equals(token.getNome())) {
        return;
      }
    }

    throw new ExceptionsCompilador("[Erro Semantico] variavel precisa ser declarada: " + token.getNome());
  }

  private Boolean pesquisaNomeToken(Token token) {// token na lista com mesmo nome retornar true
    for (int i = 0; i < lista.size(); i++) {
      if (lista.get(i).getTokenVariavel().getNome().equals(token.getNome())) {
        return true;
      }
    }
    return false;
  }

  private void colocarTipoTodasVariaveisSemTipo(Token token) {
    for (int i = 0; i < lista.size(); i++) {
      if (lista.get(i).getTokenTipoVariavel() == null) {
        lista.get(i).setTokenTipoVariavel(token);
      }
    }
  }

  //Gus: Inserido switch case para eventos onde não é uma variável, mas sim um identificador direto
  private void verificaTipoListaComparacao() throws ExceptionsCompilador {
    String tipo = "";
    String tipoAtual;

    for (int i = 0; i < listaComparacao.size(); i++) {//pega o tipo das variaveis
      if (buscaTipoToken(listaComparacao.get(i)) != null && tipo.equals("")) { //coloca o tipo na variavel
        tipo = buscaTipoToken(listaComparacao.get(i)).getToken();
      } else if (buscaTipoToken(listaComparacao.get(i)) != null) {// buscar um tipo e não vir o mesmo da uma excetion
        if (!buscaTipoToken(listaComparacao.get(i)).getToken().equals(tipo)) {
          throw new ExceptionsCompilador(
              "[Erro Semantico] use apenas um tipo de variavel");
        }
      } else{
        tipoAtual = listaComparacao.get(i).getToken();
        switch (tipoAtual){
          case "numeroInteiro":
            tipoAtual = "integer";
            break;
          case "numeroFloat":
            tipoAtual = "float";
            break;
          case "nomeDaString":
            tipoAtual = "string";
          case "nomeDoChar":
            tipoAtual = "char";
        }
        if (tipoAtual.equals(tipo)){
          continue;
        } else if (!tipoAtual.equals(tipo)){
          throw new ExceptionsCompilador(
              "[Erro Semantico] use apenas um tipo de variavel");
        } else {
          throw new ExceptionsCompilador("[Erro Semantico] Variavel nula");
        }  
      }
    }
    listaComparacao.clear();
  }

  private Token buscaTipoToken(Token token) {
    for (int i = 0; i < lista.size(); i++) {
      if (lista.get(i).getTokenVariavel().getNome().equals(token.getNome())) {
        return lista.get(i).getTokenTipoVariavel();
      }
    }
    return null;
  }
}
