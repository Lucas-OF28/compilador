
package Views;

import Util.FuncoesSalvarAbrir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Analisadores.AnalisadorLexico;

public class ActionManager implements ActionListener{

    private Compiler editor;
    private FuncoesSalvarAbrir funcoes = new FuncoesSalvarAbrir();

    public ActionManager(Compiler editor) {
        this.editor = editor;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if("novo".equals(e.getActionCommand()))
        {
            funcoes = editor.Novo();
        }
        
        if("abrir".equals(e.getActionCommand()))
        {
            funcoes = editor.abrir(funcoes);
        }
        
        
        if("salvar".equals(e.getActionCommand()))
        {
            funcoes = editor.salvar(funcoes);
        }
        
        if("sair".equals(e.getActionCommand()))
        {
            editor.Sair();
        }
        
        if("sobre".equals(e.getActionCommand()))
        {
            editor.sobre();
        }
        
        if("analise_lexico".equals(e.getActionCommand()))
        {
            AnalisadorLexico automato = new AnalisadorLexico(editor.pegarTexto());
            automato.verificaSentenca();
            editor.setLista(automato.getTokensDaSentenca());
            editor.adicionarLinhas();
        }
        
        if("compilar".equals(e.getActionCommand()))
        {
            AnalisadorLexico automato = new AnalisadorLexico(editor.pegarTexto());
            automato.analisarSintatico = true;
            automato.setEditor(this.editor);
            automato.verificaSentenca();
        }

        if("passo".equals(e.getActionCommand()))
        {
            AnalisadorLexico automato = new AnalisadorLexico(editor.pegarTexto());
        }
    }
}
