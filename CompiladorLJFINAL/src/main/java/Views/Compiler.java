package Views;


//Gus: Alterado nome de editor para Compiler,
//     mover editor de texto para classe para incluir contagem de linhas em coluna

import Classes.Token;
import Util.FuncoesSalvarAbrir;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Compiler extends javax.swing.JFrame {


  //Gus: editorBotoes alterado para ActionManager, movido para a pasta de views já que opera diretamente aqui
  ActionManager botoes = new ActionManager(this);
  ArrayList<Token> lista;

  public void setLista(ArrayList<Token> lista) {
    this.lista = lista;
  }

  public Compiler() {
    initComponents();
    jmNovo.addActionListener(botoes);
    jmAbrir.addActionListener(botoes);
    jmSalvar.addActionListener(botoes);
    jmSair.addActionListener(botoes);
    jmSobre.addActionListener(botoes);
    jmAnalise.addActionListener(botoes);
    jCompilar.addActionListener(botoes);
  }

  public FuncoesSalvarAbrir Novo() {
    FuncoesSalvarAbrir funcoes = new FuncoesSalvarAbrir();
    jeditArea.setText("");
    return funcoes;
  }

  public FuncoesSalvarAbrir abrir(FuncoesSalvarAbrir funcoes) {
    pegarCaminhoArquivo(funcoes);
    return funcoes;
  }

  public FuncoesSalvarAbrir salvar(FuncoesSalvarAbrir funcoes) {

    if (funcoes.getCaminhoDoArquivo().equals("")) {
      PegarCaminhoESalvar(funcoes);
    } else {
      gravarArquivo(funcoes.getCaminhoDoArquivo(), jeditArea.getText());
    }
    return funcoes;
  }

  public void Sair() {
    this.dispose();
  }

  public void sobre() {
    String texto = "Compilador Jean & Lucas 2022/2";
    JOptionPane.showMessageDialog(null, texto, "Sobre", JOptionPane.INFORMATION_MESSAGE);
  }

  private void PegarCaminhoESalvar(FuncoesSalvarAbrir funcoes) {
    JFileChooser fc = new JFileChooser();
    fc.showSaveDialog(this);
    File selFile = fc.getSelectedFile();

    if (selFile != null) {
      funcoes.setCaminhoDoArquivo(selFile.getAbsolutePath());
      gravarArquivo(funcoes.getCaminhoDoArquivo(), jeditArea.getText());
    }
  }

  private void gravarArquivo(String nomeArquivo, String textoArquivo) {
    textoArquivo = textoArquivo.replaceAll("\n", "\r\n");

    FileWriter fileWriter = null;
    BufferedWriter bufferedWriter = null;
    try {
      fileWriter = new FileWriter(nomeArquivo, false);
      bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(textoArquivo);
      bufferedWriter.flush();
      JOptionPane.showMessageDialog(this, "Salvo com sucesso");
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo: " + ex.getMessage());
    } finally {
      if (bufferedWriter != null) {
        try {
          bufferedWriter.close();
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo: "
              + ex.getMessage());
        }
      }
      if (fileWriter != null) {
        try {
          fileWriter.close();
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo: "
              + ex.getMessage());
        }
      }
    }
  }

  private void pegarCaminhoArquivo(FuncoesSalvarAbrir funcoes) {
    JFileChooser fc = new JFileChooser();
    fc.showOpenDialog(this);
    File selFile = fc.getSelectedFile();
    if (selFile != null) {
      if (!funcoes.getCaminhoDoArquivo().equals(selFile.getAbsolutePath())) {
        funcoes.setCaminhoDoArquivo(selFile.getAbsolutePath());
      }
      jeditArea.setText(lerArquivo(selFile.getAbsolutePath()));
    }
  }

  private String lerArquivo(String nomeArquivo) {
    FileReader fileReader = null;
    BufferedReader bufferedReader = null;
    try {
      fileReader = new FileReader(nomeArquivo);
      bufferedReader = new BufferedReader(fileReader);
      StringBuilder sb = new StringBuilder();
      while (bufferedReader.ready()) {
        sb.append(bufferedReader.readLine());
        sb.append("\n");
      }
      return sb.toString();
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(this, "Erro ao abrir o arquivo: "
          + ex.getMessage());
    } finally {
      if (bufferedReader != null) {
        try {
          bufferedReader.close();
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(this, "Erro ao abrir o arquivo: "
              + ex.getMessage());
        }
      }
      if (fileReader != null) {
        try {
          fileReader.close();
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(this, "Erro ao abrir o arquivo: "
              + ex.getMessage());
        }
      }
    }
    return null;
  }

  public String pegarTexto() {
    return jeditArea.getText();
  }

  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    jeditArea = new TextEditField();
    jScrollPane2 = new javax.swing.JScrollPane();
    tabelaAutomato = new javax.swing.JTable();
    jMenuBar1 = new javax.swing.JMenuBar();
    jmNovo = new javax.swing.JMenuItem();
    jmAbrir = new javax.swing.JMenuItem();
    jmSalvar = new javax.swing.JMenuItem();
    jmSair = new javax.swing.JMenuItem();
    jmenu3 = new javax.swing.JMenu();
    jmAnalise = new javax.swing.JMenuItem();
    jCompilar = new javax.swing.JMenuItem();
    jMenu2 = new javax.swing.JMenu();
    jmSobre = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Compilador");

    jScrollPane1.setViewportView(jeditArea);

    tabelaAutomato.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {

        },
        new String[] {
            "Código", "Token", "Linha"
        }) {
      boolean[] canEdit = new boolean[] {
          false, true, false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
      }
    });
    jScrollPane2.setViewportView(tabelaAutomato);
    if (tabelaAutomato.getColumnModel().getColumnCount() > 0) {
      tabelaAutomato.getColumnModel().getColumn(0).setResizable(false);
      tabelaAutomato.getColumnModel().getColumn(1).setResizable(false);
      tabelaAutomato.getColumnModel().getColumn(2).setResizable(false);
    }

   // jMenu1.setText("Arquivo");

   // jmNovo.setAccelerator(
   //     javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
   // jmNovo.setText("Novo");
   // jmNovo.setActionCommand("novo");
   // jMenu1.add(jmNovo);

   // jmAbrir.setAccelerator(
   //     javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
   // jmAbrir.setText("Abrir");
   // jmAbrir.setActionCommand("abrir");
   // jMenu1.add(jmAbrir);

   // jmSalvar.setAccelerator(
   //     javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
   // jmSalvar.setText("Salvar");
   // jmSalvar.setActionCommand("salvar");
   // jMenu1.add(jmSalvar);

    //jmSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
    //jmSair.setText("Sair");
    //jmSair.setActionCommand("sair");
    //jMenu1.add(jmSair);

   // jMenuBar1.add(jMenu1);

    jmenu3.setText("Executar");

    jmAnalise.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
    jmAnalise.setText("Analise Léxica");
    jmAnalise.setActionCommand("analise_lexico");
    jmenu3.add(jmAnalise);

    jCompilar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
    jCompilar.setText("Compilar");
    jCompilar.setActionCommand("compilar");
    jmenu3.add(jCompilar);

    jmenu3.setActionCommand("analise");

    jMenuBar1.add(jmenu3);

    jMenu2.setText("Sobre");

    jmSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
    jmSobre.setText("Sobre o Editor");
    jmSobre.setActionCommand("sobre");
    jMenu2.add(jmSobre);

    jMenuBar1.add(jMenu2);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING));

    pack();
    setLocationRelativeTo(null);
  }

  public static void main(String args[]) {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(Compiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(Compiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(Compiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Compiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Compiler().setVisible(true);
      }
    });
  }

  public void adicionarLinhas() {
    DefaultTableModel model = (DefaultTableModel) tabelaAutomato.getModel();
    Object rowData[] = new Object[3];
    model.setRowCount(1);
    for (int i = 0; i < lista.size(); i++) {
      rowData[0] = lista.get(i).getCodToken();
      rowData[1] = lista.get(i).getToken();
      rowData[2] = lista.get(i).getLinha();
      model.addRow(rowData);
    }

    // System.out.println("--- Tokens ---");
    // for (int i = 0; i < lista.size(); i++) {
    //   System.out.println("Cod " + lista.get(i).getCodToken() + " Token " + lista.get(i).getToken() + " nome: "
    //       + lista.get(i).getNome());
    // }
    // System.out.println("--- Tokens ---");
  }

  private javax.swing.JMenuItem jCompilar;
  private javax.swing.JMenu jMenu2;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JTextPane jeditArea;
  private javax.swing.JMenuItem jmAbrir;
  private javax.swing.JMenuItem jmAnalise;
  private javax.swing.JMenuItem jmNovo;
  private javax.swing.JMenuItem jmSair;
  private javax.swing.JMenuItem jmSalvar;
  private javax.swing.JMenuItem jmSobre;
  private javax.swing.JMenu jmenu3;
  private javax.swing.JTable tabelaAutomato;
}