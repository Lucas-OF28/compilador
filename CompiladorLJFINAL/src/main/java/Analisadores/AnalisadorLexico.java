package Analisadores;

import Classes.Dicionario;
import Classes.Token;
import Classes.ExceptionsCompilador;
import Views.Compiler;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class AnalisadorLexico {

  private char[] sentenca;
  private ArrayList<Token> tokensDaSentenca = new ArrayList<Token>();
  private Dicionario dicionario = new Dicionario();
  public boolean analisarSintatico = false;
  private Compiler editor;
  private AnalisadorSemantico acoesSemantica = new AnalisadorSemantico();

  public AnalisadorLexico(String sentenca) {
      //Transforma quebra linha em /n
    sentenca = sentenca.replaceAll("\r\n", "\n");
    this.sentenca = sentenca.toCharArray();
    UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 15));
  }

  public void verificaSentenca() {
    boolean verificaErro = false;
    int index = 0;
    int indexAnterior;

    if (analisarSintatico && sentenca.length > 0) {
      Token tokenSalvar = dicionario.retornaTokenDicionarioCodigo(48);
      tokensDaSentenca.add(tokenSalvar);
      Token tokenSentenca = dicionario.retornaTokenDicionario("$");
      tokensDaSentenca
          .add(new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), "$", pegarNumeroLinha(index)));

    }

    while (this.sentenca.length > index) {
      atualizarGrid();
      indexAnterior = index;
      try { //espaço em branco e quebra linha  ignora
        if (this.sentenca[index] == ' ' || this.sentenca[index] == '\n') {
          index++;
        } else {

          index = automatoVariavel(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoIntegerFloat(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatochar(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoString(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoLiteral(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoPalavraReservada(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoSoma(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoSubtracao(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoDivisao(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoMultiplicacao(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoAtribuicaoEComparacao(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoDiferente(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoCaracteresEspeciais(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoMaior(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoMenor(index);
          if (this.sentenca.length == index) {
            break;
          }

          index = automatoComentario(index);
          if (this.sentenca.length == index) {
            break;
          }
        }

        if (indexAnterior == index) {//se chgar aqui o index nao mudou
          verificaErro = true;
          JOptionPane.showMessageDialog(null, "Erro na linha: " + pegarNumeroLinha(index) + "\nCaracter \" "
              + this.sentenca[index] + " \" desconhecido inserido.", "Compilador C-Hala",
              JOptionPane.INFORMATION_MESSAGE);
          break;
        }

      } catch (ExceptionsCompilador ex) {
        verificaErro = true;
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Compilador C-Hala", JOptionPane.INFORMATION_MESSAGE);
        break;
      }

    }

    if (!verificaErro) {

      Token tokenSentenca = dicionario.retornaTokenDicionario("$");
      Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), "$",
          pegarNumeroLinha(index));
      if (!analisarSintatico) {
        tokensDaSentenca.add(tokenSalvar);
        JOptionPane.showMessageDialog(null, "Analise efetuada com sucesso Sem erros", "Compilador C-Hala",
            JOptionPane.INFORMATION_MESSAGE);

      } else {
        JOptionPane.showMessageDialog(null, "Compilação efetuada com sucesso Sem erros", "Compilador C-Hala",
            JOptionPane.INFORMATION_MESSAGE);
      }//entro aqui ta funcionado
    }
  }
//Abaixo saoos automatos
  private int automatoIntegerFloat(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    String valor = "";
    Boolean possuiVirgula = false;

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (Character.isDigit(this.sentenca[index])) {
          valor += this.sentenca[index];
          index++;
        } else {
          return index;
        }
      } else {
        if (this.sentenca.length != index) {//se a variavel estiver no final da sentença nao acessa o proximo index
          if (Character.isDigit(this.sentenca[index])) {
            valor += this.sentenca[index];
            index++;
          } else if (this.sentenca[index] == '.') {
            if (!possuiVirgula) {//verifica a virgura
              possuiVirgula = true;
              valor += this.sentenca[index];
              index++;
            } else {
              throw new ExceptionsCompilador(
                  "Numero com duas virgulas não permitido\nErro na linha: " + pegarNumeroLinha(index));
            }
          } else {
            if (possuiVirgula) {
              if (dicionario.retornaTokenDicionario("numeroFloat") != null) {
                Token tokenSentenca = dicionario.retornaTokenDicionario("numeroFloat");
                Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                    pegarNumeroLinha(index));
                if (analisarSintatico) {
                  AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                  this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                      this.tokensDaSentenca, this.acoesSemantica);
                  atualizarGrid();
                } else {
                  tokensDaSentenca.add(tokenSalvar);
                }
                return index;
              } else {
                throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
              }

            } else {
              if (dicionario.retornaTokenDicionario("numeroInteiro") != null) {
                Token tokenSentenca = dicionario.retornaTokenDicionario("numeroInteiro");
                Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                    pegarNumeroLinha(index));
                if (analisarSintatico) {
                  AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                  this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                      this.tokensDaSentenca, this.acoesSemantica);
                  atualizarGrid();
                } else {
                  tokensDaSentenca.add(tokenSalvar);
                }
                return index;
              } else {
                throw new ExceptionsCompilador("Tipo Não encontrado no dicionario");
              }
            }
          }
        } else {//Final do arquivo vazio
          if (possuiVirgula) {
            if (dicionario.retornaTokenDicionario("numeroFloat") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("numeroFloat");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();
              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
            }

          } else {

            if (dicionario.retornaTokenDicionario("numeroInteiro") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("numeroInteiro");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();
              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
            }
          }
        }
      }
    }

    throw new ExceptionsCompilador("Fim de Sentença");
  }

  private int automatochar(int index) throws ExceptionsCompilador {//indentifica o char
    int primeiroIndex = index;
    String valor = "";

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (this.sentenca[index] == '#') {
          valor += this.sentenca[index];
          index++;
        } else {
          return index;
        }

      } else {

        if (primeiroIndex + 1 == index && index != this.sentenca.length) {
          valor += sentenca[index];
          index++;
        } else if (this.sentenca.length == index) {
          throw new ExceptionsCompilador(
              " Sentença errada #.\nErro na linha: "
                  + pegarNumeroLinha(index));
        }

        if (primeiroIndex + 2 == index && index != this.sentenca.length) {
          if (this.sentenca[index] == '#') {
            valor += this.sentenca[index];
            index++;

            if (dicionario.retornaTokenDicionario("nomeDoChar") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("nomeDoChar");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Não encontraro no dicionario");
            }

          } else {
            throw new ExceptionsCompilador(
                "Char incorreto #\nErro na linha: "
                    + pegarNumeroLinha(index));
          }
        } else {
          throw new ExceptionsCompilador(
              "Fim da Sentença, Char Incorreto #.\nErro na linha: "
                  + pegarNumeroLinha(index));
        }
      }
    }

    throw new ExceptionsCompilador("Fim da Sentença");

  }
//Verifica se é string
  private int automatoString(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    String valor = "";
    boolean fechamento = false;

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (this.sentenca[index] == '%') {
          valor += this.sentenca[index];
          index++;
        } else {
          return index;
        }
      } else if (primeiroIndex + 1 == index && index != this.sentenca.length) {
        if (this.sentenca[index] == '%') {
          valor += this.sentenca[index];
          index++;
        } else {
          throw new ExceptionsCompilador(
              "String incorreto.\nErro na linha: " + pegarNumeroLinha(index));
        }

      } else {
        if (!fechamento && index != this.sentenca.length) {
          if (this.sentenca[index] != '%') {
            valor += this.sentenca[index];
            index++;
          } else {
            valor += this.sentenca[index];
            index++;
            fechamento = true;
          }
        } else {
          if (this.sentenca.length != index) {
            if (this.sentenca[index] == '%') {
              valor += this.sentenca[index];
              index++;

              if (dicionario.retornaTokenDicionario("nomeDaString") != null) {
                Token tokenSentenca = dicionario.retornaTokenDicionario("nomeDaString");
                Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                    pegarNumeroLinha(index));
                if (analisarSintatico) {
                  AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                  this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                      this.tokensDaSentenca, this.acoesSemantica);
                  atualizarGrid();
                } else {
                  tokensDaSentenca.add(tokenSalvar);
                }
                return index;
              } else {
                throw new ExceptionsCompilador("Não encontraro no dicionario");
              }

            } else {
              throw new ExceptionsCompilador(
                  "String incorreto.\nErro na linha: "
                      + pegarNumeroLinha(index));
            }
          } else {
            throw new ExceptionsCompilador(
                "String errado.\nErro na linha: "
                    + pegarNumeroLinha(index));
          }
        }
      }

    }

    throw new ExceptionsCompilador("Fim da Sentença");
  }

  private int automatoLiteral(int index) throws ExceptionsCompilador {//verificaçao Literal
    int primeiroIndex = index;
    String valor = "";

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (this.sentenca[index] == '"') {
          valor += this.sentenca[index];
          index++;
        } else {
          return index;
        }
      } else if (this.sentenca.length != index) {
        if (this.sentenca[index] != '"') {
          valor += this.sentenca[index];
          index++;
        } else {
          valor += this.sentenca[index];
          index++;
          if (dicionario.retornaTokenDicionario("literal") != null) {
            Token tokenSentenca = dicionario.retornaTokenDicionario("literal");
            Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                pegarNumeroLinha(index));
            if (analisarSintatico) {
              AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
              this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                  this.tokensDaSentenca, this.acoesSemantica);
              atualizarGrid();

            } else {
              tokensDaSentenca.add(tokenSalvar);
            }
            return index;
          } else {
            throw new ExceptionsCompilador("Não encontraro no dicionario");
          }
        }
      } else {
        throw new ExceptionsCompilador(
            "Fim da Sentenca, Literal não foi fechado com \" ele incia na linha  " + pegarNumeroLinha(primeiroIndex));
      }

    }

    throw new ExceptionsCompilador("Fim da Sentença");
  }
//Palavras reservadas verificaçao
  private int automatoPalavraReservada(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    String valor = "";

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (Character.isLetter(this.sentenca[index])) {
          valor += this.sentenca[index];
          index++;
        } else {
          return index;
        }

      } else {
        if (this.sentenca.length != index) {

          if (Character.isLetter(this.sentenca[index])) {
            valor += this.sentenca[index];
            index++;
          } else {
            if (dicionario.retornaTokenDicionario(valor) != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario(valor);
              if (dicionario.verificaPalavraReservada(tokenSentenca.getCodToken())) {

                Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                    pegarNumeroLinha(index));
                if (analisarSintatico) {
                  AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                  this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                      this.tokensDaSentenca, this.acoesSemantica);
                  atualizarGrid();
                } else {
                  tokensDaSentenca.add(tokenSalvar);
                }
                return index;

              } else {
                throw new ExceptionsCompilador("Palavra reservada \" " + valor
                    + " \" não foi encontrada no dicionário\nErro na linha: " + pegarNumeroLinha(index));
              }
            } else {
              throw new ExceptionsCompilador("Palavra reservada \" " + valor
                  + " \" não foi encontrada no dicionário\nErro na linha: " + pegarNumeroLinha(index));
            }
          }

        } else {
          if (dicionario.retornaTokenDicionario(valor) != null) {
            Token tokenSentenca = dicionario.retornaTokenDicionario(valor);
            if (dicionario.verificaPalavraReservada(tokenSentenca.getCodToken())) {
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Palavra reservada \" " + valor
                  + " \" não foi encontrada no dicionário\nErro na linha: " + pegarNumeroLinha(index));
            }
          } else {
            throw new ExceptionsCompilador("Palavra reservada \" " + valor
                + " \" não foi encontrada no dicionário\nErro na linha: " + pegarNumeroLinha(index));
          }
        }
      }

    }

    throw new ExceptionsCompilador("Fim da Sentença");
  }

  private int automatoVariavel(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    String valor = "";

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (this.sentenca[index] == '$') {//Se nao começa com $ nao é variavel
          valor += this.sentenca[index];
          index++;
        } else {
          return index;
        }
      } else {
          
                // variavel no sim da sentenca finzalizar o nome da variavel para nao acessar o index
                
        if (this.sentenca.length != index) {
          if (Character.isLetter(this.sentenca[index])) {
            valor += this.sentenca[index];
            index++;

          } else if (Character.isDigit(this.sentenca[index])) {
            valor += this.sentenca[index];
            index++;
          } else {
            if (dicionario.retornaTokenDicionario("nomeVariavel") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("nomeVariavel");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }

              return index;
            } else {
              throw new ExceptionsCompilador("Não encontraro no dicionario");
            }
          }
        } else {
          if (dicionario.retornaTokenDicionario("nomeVariavel") != null) {
            Token tokenSentenca = dicionario.retornaTokenDicionario("nomeVariavel");
            Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                pegarNumeroLinha(index));
            if (analisarSintatico) {
              AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
              this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                  this.tokensDaSentenca, this.acoesSemantica);
              atualizarGrid();

            } else {
              tokensDaSentenca.add(tokenSalvar);
            }

            return index;
          } else {
            throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
          }
        }
      }
    }

    throw new ExceptionsCompilador("Fim da Sentença");

  }
//Verifica se é variavel
  private int automatoFuncao(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    String valor = "";

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (this.sentenca[index] == '[') {
          valor += this.sentenca[index];
          index++;
        } else {
          return index;
        }
      } else {
        if (this.sentenca.length != index) {
          if (Character.isLetter(this.sentenca[index])) {
            valor += this.sentenca[index];
            index++;

          } else if (Character.isDigit(this.sentenca[index])) {
            valor += this.sentenca[index];
            index++;
          } else {
            if (dicionario.retornaTokenDicionario("callFuncao") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("callFuncao");
              tokensDaSentenca.add(
                  new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor, pegarNumeroLinha(index)));
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
            }
          }
        } else {
          if (dicionario.retornaTokenDicionario("callFuncao") != null) {
            Token tokenSentenca = dicionario.retornaTokenDicionario("callFuncao");
            tokensDaSentenca
                .add(new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor, pegarNumeroLinha(index)));
            return index;
          } else {
            throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
          }
        }
      }
    }

    throw new ExceptionsCompilador("Fim da Sentença");

  }

  public char[] getSentenca() {
    return sentenca;
  }

  private int automatoSoma(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    String valor = "";

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (this.sentenca[index] == '+') {
          valor += this.sentenca[index];
          index++;
        } else {
          return index;
        }
      } else {
        if (this.sentenca.length != index) {
          if (this.sentenca[index] == '+') {
            valor += this.sentenca[index];
            index++;

            if (dicionario.retornaTokenDicionario("++") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("++");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

            }
          } else {
            if (dicionario.retornaTokenDicionario("+") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("+");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
            }
          }

        } else {
          if (dicionario.retornaTokenDicionario("+") != null) {
            Token tokenSentenca = dicionario.retornaTokenDicionario("+");
            Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                pegarNumeroLinha(index));
            if (analisarSintatico) {
              AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
              this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                  this.tokensDaSentenca, this.acoesSemantica);
              atualizarGrid();

            } else {
              tokensDaSentenca.add(tokenSalvar);
            }
            return index;
          } else {
            throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
          }
        }
      }
    }

    throw new ExceptionsCompilador("Fim da Sentença");
  }

  private int automatoSubtracao(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    String valor = "";

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (this.sentenca[index] == '-') {
          valor += this.sentenca[index];
          index++;
        } else {
          return index;
        }
      } else {
        if (this.sentenca.length != index) {
          if (this.sentenca[index] == '-') {
            valor += this.sentenca[index];
            index++;

            if (dicionario.retornaTokenDicionario("--") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("--");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

            }
          } else {
            if (dicionario.retornaTokenDicionario("-") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("-");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
            }
          }

        } else {
          if (dicionario.retornaTokenDicionario("-") != null) {
            Token tokenSentenca = dicionario.retornaTokenDicionario("-");
            Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                pegarNumeroLinha(index));
            if (analisarSintatico) {
              AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
              this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                  this.tokensDaSentenca, this.acoesSemantica);
              atualizarGrid();

            } else {
              tokensDaSentenca.add(tokenSalvar);
            }
            return index;
          } else {
            throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
          }
        }
      }
    }

    throw new ExceptionsCompilador("Fim da Sentença");
  }

  private int automatoDivisao(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    String valor = "";

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (this.sentenca[index] == '/') {
          valor += this.sentenca[index];
          index++;

          if (dicionario.retornaTokenDicionario("/") != null) {
            Token tokenSentenca = dicionario.retornaTokenDicionario("/");
            Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                pegarNumeroLinha(index));
            if (analisarSintatico) {
              AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
              this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                  this.tokensDaSentenca, this.acoesSemantica);
              atualizarGrid();

            } else {
              tokensDaSentenca.add(tokenSalvar);
            }
            return index;
          } else {
            throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
          }

        } else {
          return index;
        }
      }
    }

    throw new ExceptionsCompilador("Fim da Sentença");
  }

  private int automatoMultiplicacao(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    String valor = "";

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (this.sentenca[index] == '*') {
          valor += this.sentenca[index];
          index++;

          if (dicionario.retornaTokenDicionario("*") != null) {
            Token tokenSentenca = dicionario.retornaTokenDicionario("*");
            Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                pegarNumeroLinha(index));
            if (analisarSintatico) {
              AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
              this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                  this.tokensDaSentenca, this.acoesSemantica);
              atualizarGrid();

            } else {
              tokensDaSentenca.add(tokenSalvar);
            }
            return index;
          } else {
            throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
          }

        } else {
          return index;
        }
      }
    }

    throw new ExceptionsCompilador("Fim da Sentença");
  }

  private int automatoAtribuicaoEComparacao(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    String valor = "";

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (this.sentenca[index] == '=') {
          valor += this.sentenca[index];
          index++;
        } else {
          return index;
        }
      } else {
        if (this.sentenca.length != index) {
          if (this.sentenca[index] == '=') {
            valor += this.sentenca[index];
            index++;

            if (dicionario.retornaTokenDicionario("==") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("==");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

            }
          } else {
            if (dicionario.retornaTokenDicionario("=") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("=");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
            }
          }

        } else {
          if (dicionario.retornaTokenDicionario("=") != null) {
            Token tokenSentenca = dicionario.retornaTokenDicionario("=");
            Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                pegarNumeroLinha(index));
            if (analisarSintatico) {
              AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
              this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                  this.tokensDaSentenca, this.acoesSemantica);
              atualizarGrid();

            } else {
              tokensDaSentenca.add(tokenSalvar);
            }
            return index;
          } else {
            throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
          }
        }
      }
    }

    throw new ExceptionsCompilador("Fim da Sentença");
  }

  private int automatoDiferente(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    String valor = "";

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (this.sentenca[index] == '!') {
          valor += "!";
          index++;
        } else {
          return index;
        }
      } else {
        if (this.sentenca.length != index) {
          if (this.sentenca[index] == '=') {
            valor += this.sentenca[index];
            index++;

            if (dicionario.retornaTokenDicionario("!=") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("!=");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

            }
          }
        } else {
          throw new ExceptionsCompilador(
              "Fim da Sentença. operador diferente escrito errado, o correto é !=\nErro na linha: "
                  + pegarNumeroLinha(index));
        }
      }
    }

    throw new ExceptionsCompilador("Fim da Sentença");
  }

  private int automatoCaracteresEspeciais(int index) throws ExceptionsCompilador {
    if (this.sentenca[index] == '{') {

      if (dicionario.retornaTokenDicionario("{") != null) {
        index++;
        Token tokenSentenca = dicionario.retornaTokenDicionario("{");
        Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), "{",
            pegarNumeroLinha(index));
        if (analisarSintatico) {
          AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
          this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
              this.tokensDaSentenca, this.acoesSemantica);
          atualizarGrid();

        } else {
          tokensDaSentenca.add(tokenSalvar);
        }
        return index;
      } else {
        throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

      }
    }

    if (this.sentenca[index] == '}') {

      if (dicionario.retornaTokenDicionario("}") != null) {
        index++;
        Token tokenSentenca = dicionario.retornaTokenDicionario("}");
        Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), "}",
            pegarNumeroLinha(index));
        if (analisarSintatico) {
          AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
          this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
              this.tokensDaSentenca, this.acoesSemantica);
          atualizarGrid();

        } else {
          tokensDaSentenca.add(tokenSalvar);
        }
        return index;
      } else {
        throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

      }
    }

    if (this.sentenca[index] == '(') {

      if (dicionario.retornaTokenDicionario("(") != null) {
        index++;
        Token tokenSentenca = dicionario.retornaTokenDicionario("(");
        Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), "(",
            pegarNumeroLinha(index));
        if (analisarSintatico) {
          AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
          this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
              this.tokensDaSentenca, this.acoesSemantica);
          atualizarGrid();

        } else {
          tokensDaSentenca.add(tokenSalvar);
        }
        return index;
      } else {
        throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

      }
    }

    if (this.sentenca[index] == ')') {

      if (dicionario.retornaTokenDicionario(")") != null) {
        index++;
        Token tokenSentenca = dicionario.retornaTokenDicionario(")");
        Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), ")",
            pegarNumeroLinha(index));
        if (analisarSintatico) {
          AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
          this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
              this.tokensDaSentenca, this.acoesSemantica);
          atualizarGrid();

        } else {
          tokensDaSentenca.add(tokenSalvar);
        }
        return index;
      } else {
        throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

      }
    }

    if (this.sentenca[index] == ';') {

      if (dicionario.retornaTokenDicionario(";") != null) {
        index++;
        Token tokenSentenca = dicionario.retornaTokenDicionario(";");
        Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), ";",
            pegarNumeroLinha(index));
        if (analisarSintatico) {
          AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
          this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
              this.tokensDaSentenca, this.acoesSemantica);
          atualizarGrid();

        } else {
          tokensDaSentenca.add(tokenSalvar);
        }
        return index;
      } else {
        throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

      }
    }

    if (this.sentenca[index] == ':') {

      if (dicionario.retornaTokenDicionario(":") != null) {
        index++;
        Token tokenSentenca = dicionario.retornaTokenDicionario(":");
        Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), ":",
            pegarNumeroLinha(index));
        if (analisarSintatico) {
          AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
          this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
              this.tokensDaSentenca, this.acoesSemantica);
          atualizarGrid();

        } else {
          tokensDaSentenca.add(tokenSalvar);
        }
        return index;
      } else {
        throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

      }
    }

    if (this.sentenca[index] == ',') {

      if (dicionario.retornaTokenDicionario(",") != null) {
        index++;
        Token tokenSentenca = dicionario.retornaTokenDicionario(",");
        Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), ",",
            pegarNumeroLinha(index));
        if (analisarSintatico) {
          AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
          this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
              this.tokensDaSentenca, this.acoesSemantica);
          atualizarGrid();

        } else {
          tokensDaSentenca.add(tokenSalvar);
        }
        return index;
      } else {
        throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

      }
    }
    return index;
  }

  private int automatoMaior(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    String valor = "";

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (this.sentenca[index] == '>') {
          valor += this.sentenca[index];
          index++;
        } else {
          return index;
        }
      } else {
        if (this.sentenca.length != index) {
          if (this.sentenca[index] == '=') {
            valor += this.sentenca[index];
            index++;

            if (dicionario.retornaTokenDicionario(">=") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario(">=");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

            }
          } else if (this.sentenca[index] == '>') {
            valor += this.sentenca[index];
            index++;

            if (dicionario.retornaTokenDicionario(">>") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario(">>");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

            }
          } else {

            if (dicionario.retornaTokenDicionario(">") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario(">");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

            }
          }
        } else {

          if (dicionario.retornaTokenDicionario(">") != null) {
            Token tokenSentenca = dicionario.retornaTokenDicionario(">");
            Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                pegarNumeroLinha(index));
            if (analisarSintatico) {
              AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
              this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                  this.tokensDaSentenca, this.acoesSemantica);
              atualizarGrid();

            } else {
              tokensDaSentenca.add(tokenSalvar);
            }
            return index;
          } else {
            throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");
          }
        }
      }
    }
    throw new ExceptionsCompilador("Fim da Sentença");
  }

  private int automatoMenor(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    String valor = "";

    while (this.sentenca.length >= index) {
      if (primeiroIndex == index) {
        if (this.sentenca[index] == '<') {
          valor += this.sentenca[index];
          index++;
        } else {
          return index;
        }
      } else {
        if (this.sentenca.length != index) {
          if (this.sentenca[index] == '=') {
            valor += this.sentenca[index];
            index++;

            if (dicionario.retornaTokenDicionario("<=") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("<=");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

            }
          } else if (this.sentenca[index] == '<') {
            valor += this.sentenca[index];
            index++;

            if (dicionario.retornaTokenDicionario("<<") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("<<");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

            }
          } else {

            if (dicionario.retornaTokenDicionario("<") != null) {
              Token tokenSentenca = dicionario.retornaTokenDicionario("<");
              Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                  pegarNumeroLinha(index));
              if (analisarSintatico) {
                AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
                this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                    this.tokensDaSentenca, this.acoesSemantica);
                atualizarGrid();

              } else {
                tokensDaSentenca.add(tokenSalvar);
              }
              return index;
            } else {
              throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

            }
          }
        } else {

          if (dicionario.retornaTokenDicionario("<") != null) {
            Token tokenSentenca = dicionario.retornaTokenDicionario("<");
            Token tokenSalvar = new Token(tokenSentenca.getCodToken(), tokenSentenca.getToken(), valor,
                pegarNumeroLinha(index));
            if (analisarSintatico) {
              AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
              this.tokensDaSentenca = analisadorSintatico.analisarTreta(this.tokensDaSentenca.get(0), tokenSalvar,
                  this.tokensDaSentenca, this.acoesSemantica);
              atualizarGrid();

            } else {
              tokensDaSentenca.add(tokenSalvar);
            }
            return index;
          } else {
            throw new ExceptionsCompilador("Tipo Não encontraro no dicionario");

          }
        }

      }

    }

    throw new ExceptionsCompilador("Fim da Sentença");
  }

  private int automatoComentario(int index) throws ExceptionsCompilador {
    int primeiroIndex = index;
    boolean comentarioLinha = false;
    boolean fechando = false;

    while (this.sentenca.length >= index) {

      if (primeiroIndex == index) {
        if (this.sentenca[index] == '@') {
          index++;

        } else {
          return index;
        }

        if (this.sentenca.length != index) {
          if (this.sentenca[index] != '@') {
            comentarioLinha = true;
          } else {
            index++;
          }
        } else {
          comentarioLinha = true;
        }
      } else {
        if (comentarioLinha) {
          if (this.sentenca.length != index) {
            if (this.sentenca[index] == '\n') {
              index++;
              return index;
            } else {
              index++;
            }

          } else {
            return index;
          }
        } else {
          if (this.sentenca.length != index) {
            if (!fechando) {
              if (this.sentenca[index] == '@') {
                index++;
                fechando = true;
              } else {
                index++;
              }
            } else {
              if (this.sentenca[index] == '@') {
                index++;
                return index;
              } else {
                index++;
                fechando = false;
              }
            }
          } else {
            throw new ExceptionsCompilador(
                "Comentario de block não foi fechado corretamente.\nErro na linha: " + pegarNumeroLinha(index));
          }
        }

      }

    }
    throw new ExceptionsCompilador("Fim da Sentença");
  }

  public ArrayList<Token> getTokensDaSentenca() {
    return tokensDaSentenca;
  }

  private int pegarNumeroLinha(int index) {
    //Gus: Alterado para iniciar na linha 1, assim bate com a visualização
    int linha = 1;

    for (int i = 0; i < this.sentenca.length; i++) {
      if (index == i) {
        break;
      }
      if (this.sentenca[i] == '\n') {
        linha++;
      }

    }

    return linha;
  }

  public void setEditor(Compiler editor) {
    this.editor = editor;
  }

  private void atualizarGrid() {
    if (editor != null) {
      editor.setLista(this.tokensDaSentenca);
      editor.adicionarLinhas();
    }
  }
}