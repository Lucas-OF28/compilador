package Analisadores;

import Classes.ExceptionsCompilador;
import Classes.GramaticaCodificada;
import Classes.TabelaParsing;
import Classes.Token;
import java.util.ArrayList;
import java.util.Collections;

public class AnalisadorSintatico {

  private ArrayList<Token> tokensDaSentenca = new ArrayList<Token>();
  private TabelaParsing tabParsing = new TabelaParsing();
  private GramaticaCodificada gramaticaCodificada = new GramaticaCodificada();


  //Gus: o que é 'x' e 'a'?
  public ArrayList<Token> analisarTreta(Token x, Token a, ArrayList<Token> pilha, AnalisadorSemantico funcAcoesSemantica)
      throws ExceptionsCompilador {
    do {
      //Gus: Imprimir a pilha de parsing a cada iteração
      System.out.println("--- Parsing ---"); 
      for (int i = 0; i < pilha.size(); i++) {
         
        System.out.println("Cod " + pilha.get(i).getCodToken() + " Token " + pilha.get(i).getToken() + " nome: "
          + pilha.get(i).getNome());
      }
      System.out.println("--- Parsing ---");

      if (x.getCodToken() == 15) {
        pilha.remove(0);
        x = pilha.get(0);
      } else if (x.getCodToken() <= 47 || pilha.get(0).getCodToken() >= 100) {

        //Gus: imagino que nesse caso 'x' é o token atual e 'a' é a pilha de previsão do sintático.
        //     O que torna essa parte problemática, porque nunca vai ter um token 'x' acima de 100, e a regra possui 100
        //     Sempre que cair em uma regra acima de 100, vai dar erro.
        if (x.getCodToken() == a.getCodToken()) {
          pilha.remove(0);
        }
        
        //Gus: Roda todas as semanticas em sequência, evita perder o index.
        while (pilha.get(0).getCodToken() >= 100) {
          funcAcoesSemantica.AcoesSemantica(a, pilha.get(0).getCodToken());
          pilha.remove(0);
        }
        return pilha;
        // } else {
        //   //Gus: Essa mensagem de erro é mais sem sal que série de vampiro adolescente

        //   //throw new ExceptionsCompilador("[Erro Sintatico] Erro na linha: " + a.getLinha() + "\nCom o texto "
        //   //    + a.getNome() + " um " + a.getToken());

        //   throw new ExceptionsCompilador("[Erro Sintático] Linha " + a.getLinha() + 
        //   ": Esperado " + x.getCodToken() + " Obteve: " + a.getCodToken());
        // }
      } else {
        if (tabParsing.getRegra(x.getCodToken(), a.getCodToken()) != 0) {
          int numeroRegra = tabParsing.getRegra(x.getCodToken(), a.getCodToken());
          pilha.remove(0);
          ArrayList<Token> regra = gramaticaCodificada.getRegras().get(numeroRegra).getRegra();
          ArrayList<Token> aux = pilha;

          for (int i = 0; i < aux.size(); i++) {
            regra.add(aux.get(i));
          }

          pilha = regra;
          x = pilha.get(0);
          
          //Gus: Roda todas as semanticas em sequência, evita perder o index.
          while (pilha.get(0).getCodToken() >= 100) {
            funcAcoesSemantica.AcoesSemantica(a, pilha.get(0).getCodToken());
            pilha.remove(0);
          }

        } else {
          //Gus: Outro exception ruim
          //throw new ExceptionsCompilador("[Erro Sintatico] Erro na linha: " + a.getLinha() + "\nCom o texto "
          //    + a.getNome() + " um " + a.getToken());

          throw new ExceptionsCompilador("[Erro Sintático] Erro na linha: " + a.getLinha() + ": Regra chave " + x.getCodToken() + ":" + a.getCodToken() + " não existe na base de regras");
        }
      }
    } while (x.getCodToken() != 44);
    return pilha;
  }
}