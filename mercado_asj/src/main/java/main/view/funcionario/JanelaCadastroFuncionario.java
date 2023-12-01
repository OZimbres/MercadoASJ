package main.view.funcionario;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import main.control.FuncionariosControl;

public class JanelaCadastroFuncionario extends JDialog {
    //-----===| ATRIBUTOS |===-----//
    // Criando componentes
    private JPanel mainPanel = new JPanel();

    //---=| Cadastro |=---//
    // Título Cadastro
    private JLabel tituloCadastro = new JLabel("Cadastro de funcionario");

    // funcionario CPF
    private JLabel cpfLabel = new JLabel("CPF:");
    private JTextField cpfInput = new JTextField(20);

    // funcionario Nome
    private JLabel nomeLabel = new JLabel("Nome:");
    private JTextField nomeInput = new JTextField(20);

    // funcionario Telefone
    private JLabel telefoneLabel = new JLabel("Telefone:");
    private JTextField telefoneInput = new JTextField(20);

    // funcionario Rua
    private JLabel ruaLabel = new JLabel("Rua:");
    private JTextField ruaInput = new JTextField(20);

    // funcionario Número
    private JLabel numeroLabel = new JLabel("Número:");
    private JTextField numeroInput = new JTextField(20);

    // funcionario CEP
    private JLabel cepLabel = new JLabel("CEP:");
    private JTextField cepInput = new JTextField(20);

    //funcionario Senha
    private JLabel senhaLabel = new JLabel("Senha:");
    private JTextField senhaInput = new JTextField(20);

    //Nivel acesso funcionario
    private JLabel nivelAcessoLabel = new JLabel("Nivel Acesso Funcionario:");
    private JComboBox<String> nivelAcessoComboBox = new JComboBox<>();

    // Botão Cadastrar/Cancelar
    private JButton buttonCadastrar = new JButton("Cadastrar");
    private JButton buttonCancelar = new JButton("Cancelar");


    private FuncionariosControl funcionarioControl;

    // ArrayList dos componentes a serem exibidos
    ArrayList<JComponent> componentes = new ArrayList<JComponent>(){
        {
            //-=| Título |=-//
            add(tituloCadastro);

            //-=| Campos |=-//
            // CPF
            add(cpfLabel);
            add(cpfInput);
            // Nome
            add(nomeLabel);
            add(nomeInput);
            // Telefone
            add(telefoneLabel);
            add(telefoneInput);
            // Rua
            add(ruaLabel);
            add(ruaInput);
            // Número
            add(numeroLabel);
            add(numeroInput);
            // CEP
            add(cepLabel);
            add(cepInput);
            //Senha
            add(senhaLabel);
            add(senhaInput);
            //nivel acesso
            add(nivelAcessoLabel);
            add(nivelAcessoComboBox);

            //-=| Botões |=-//
            add(buttonCadastrar);
            add(buttonCancelar);
        }
    };

    Insets insets; // Chamando insets para estilização do GridBaglayout

    //-----===| CONSTRUTOR |===-----///
    public JanelaCadastroFuncionario(JPanel parent, List<main.model.Funcionario> funcionarios, DefaultTableModel tableModel, JTable table){
        super((JFrame) SwingUtilities.getWindowAncestor(parent), "Cadastrar funcionario", true);
        // Adicionando mainPanel ao JFrame
        this.add(mainPanel);
        // Setando layout
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Criando a tela
        JPanel cadastrar = criarCadastrar(funcionarios, tableModel, table);

        // Adicionando tela ao Painel de telas
        mainPanel.add(cadastrar);

        nivelAcessoComboBox.addItem("Operador");
        nivelAcessoComboBox.addItem("Gerente");

        //---=| Setando o Frame |=---//
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setModal(true);
    }

    //-----===| MÉTODOS |===-----//
    //---=| Janela Cadastrar |=---//
    private JPanel criarCadastrar(List<main.model.Funcionario> funcionarios, DefaultTableModel tableModel, JTable table){
        JPanel telaCadastrar = new JPanel();
        // Setando layout
        telaCadastrar.setLayout(new GridBagLayout());
        // Setando Background
        telaCadastrar.setBackground(Color.DARK_GRAY);

        // Configurando Painel
        GridBagConstraints elemento = new GridBagConstraints(); // Variável de controle de exibição de cada elemento

        // Declarando os itens a serem utilizados (sua posição no ArrayList)
        int[] posicaoNoArray = {
            0, // Título
            1, 2, // CPF
            3, 4, // Nome
            5, 6, // Telefone
            7, 8, // Rua
            9, 10, // Número
            11, 12, // CEP
            13, 14, // Botões
            15, 16, //Senha
            17, 18, //nivel acesso

        };
        // Declarando os valores de cada item
        int[][] posicaoComponentes = {
            {0, 0, 2, 1, 2, 1, 5, 5, 5, 5}, // Título Login

            {0, 1, 1, 1, 1, 1, 5, 5, 5, 5}, // label CPF
            {1, 1, 1, 1, 1, 1, 5, 5, 5, 5}, // input CPF

            {0, 2, 1, 1, 1, 1, 5, 5, 5, 5}, // label Nome
            {1, 2, 1, 1, 1, 1, 5, 5, 5, 5}, // input Nome

            {0, 3, 1, 1, 1, 1, 5, 5, 5, 5}, // label Telefone
            {1, 3, 1, 1, 1, 1, 5, 5, 5, 5}, // input Telefone

            {0, 4, 1, 1, 1, 1, 5, 5, 5, 5}, // label Rua
            {1, 4, 1, 1, 1, 1, 5, 5, 5, 5}, // input Rua

            {0, 5, 1, 1, 1, 1, 5, 5, 5, 5}, // label Número
            {1, 5, 1, 1, 1, 1, 5, 5, 5, 5}, // input Número

            {0, 6, 1, 1, 1, 1, 5, 5, 5, 5}, // label CEP
            {1, 6, 1, 1, 1, 1, 5, 5, 5, 5}, // input CEP

            {0, 7, 1, 1, 1, 1, 5, 5, 5, 5}, // label senha
            {1, 7, 1, 1, 1, 1, 5, 5, 5, 5}, // input senha

            {0, 8, 1, 1, 1, 1, 5, 5, 5, 5}, // label nivel acesso
            {1, 8, 1, 1, 1, 1, 5, 5, 5, 5}, // input nivel acesso

            {0, 9, 1, 1, 1, 1, 5, 5, 5, 5}, // botão CADASTRAR
            {1, 9, 1, 1, 1, 1, 5, 5, 5, 5}, // botão CANCELAR
        };

        // Configurand modelo de exibição
        for(int i = 0; i < 19; i++){ // Menor que 15 pois 15 é a quantidade de item da janela
            elemento.gridx = posicaoComponentes[i][0];
            elemento.gridy = posicaoComponentes[i][1];
            elemento.gridwidth = posicaoComponentes[i][2];
            elemento.gridheight = posicaoComponentes[i][3];
            elemento.weightx = posicaoComponentes[i][4];
            elemento.weighty = posicaoComponentes[i][5];
            elemento.insets = new Insets(posicaoComponentes[i][6], posicaoComponentes[i][7], posicaoComponentes[i][8], posicaoComponentes[i][9]);

            telaCadastrar.add(componentes.get(posicaoNoArray[i]), elemento);
        }

        //---=| Tratamento de Evento |=---//
        // Botão cadastrar
        buttonCadastrar.addActionListener(e ->{
            funcionarioControl = new FuncionariosControl(funcionarios, tableModel, table);

            if(funcionarioControl.checkFuncionarioCampos(-1, "cadastrar", cpfInput.getText(), nomeInput.getText(), telefoneInput.getText(), ruaInput.getText(), numeroInput.getText(), cepInput.getText(), senhaInput.getText(), String.valueOf(nivelAcessoComboBox.getSelectedItem()).toLowerCase())){
                // "Resetando" campos
                cpfInput.setText("");
                nomeInput.setText("");
                telefoneInput.setText("");
                ruaInput.setText("");
                numeroInput.setText("");
                cepInput.setText("");
                senhaInput.setText("");
    
                dispose(); // Fecha a janela
            }
        });
        // Botão cancelar
        buttonCancelar.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(null, "Cancelar Cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if(resposta == JOptionPane.YES_OPTION){
                // "Resetando" campos
                cpfInput.setText("");
                nomeInput.setText("");
                telefoneInput.setText("");
                ruaInput.setText("");
                numeroInput.setText("");
                cepInput.setText("");
    
                dispose(); // Fecha a janela
            }
        });

        return telaCadastrar;
    }
}
