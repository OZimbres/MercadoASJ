package main.view;

import javax.swing.JFrame;

import main.model.Login;

public class FrameApp extends JFrame {
    public FrameApp(Login logado) {
        
        if(logado.getNivelAcesso().equals("operador")){
            this.setTitle("MERCADO ASJ - Operador");
        }
        else{
            this.setTitle("MERCADO ASJ - Gerente");
        }
        
        this.add(new TabbedApp(logado));
        
        //Setando janela
        this.setBounds(550, 250, 850, 500);
        this.setDefaultCloseOperation(2);
        this.setVisible(true);
        this.setBackground(java.awt.Color.DARK_GRAY);
    }
}