package main.view.venda;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import main.control.EstoquesControl;

public class JanelaConfirmarCompra extends JDialog {
    //-----===| ATRIBUTOS |===-----//
    // Criando componentes
    private JPanel mainPanel = new JPanel();

    //---=| Cadastro |=---//
    // Título Janela
    private JLabel tituloJanela = new JLabel("Confirmar Compra");

    // Cliente VIP
    private JLabel labelClienteVipInfo = new JLabel("Cliente VIP:");

    // Nome produto
    private JLabel nomeProdutoLabel = new JLabel("Nome Produto:");
    private JTextField nomeProdutoInput = new JTextField(20);

    // Descricao Produto
    private JLabel descricaoLabel = new JLabel("Descricao Produto:");
    private JTextField descricaoInput = new JTextField(20);

    // Nome Fornecedor produto
    private JLabel nomeFornecedorLabel = new JLabel("Nome Fornecedor:");
    private JTextField nomeFornecedorInput = new JTextField(20);

    // Preco produto
    private JLabel precoLabel = new JLabel("Preço Individual:");
    private JTextField precoInput = new JTextField(20);

    // Quantidade produto
    private JLabel quantidadeLabel = new JLabel("Quantidade:");
    private JTextField quantidadeInput = new JTextField(20);

    // Desconto VIP produto
    private JLabel descontoVipLabel = new JLabel("Desconto VIP:");
    private JTextField descontoVipInput = new JTextField(20);

    // Status produto
    private JLabel statusLabel = new JLabel("Status:");
    private JCheckBox statusCheckBox = new JCheckBox("Ativo");

    // Botão Cadastrar/Cancelar
    private JButton buttonCancelar = new JButton("Cancelar");
    private JButton buttonEditar = new JButton("Editar");

    private EstoquesControl estoquesControl;

    // ArrayList dos componentes a serem exibidos
    ArrayList<JComponent> componentes = new ArrayList<JComponent>(){
        {
            //-=| Título |=-//
            add(tituloCadastro);

            //-=| Campos |=-//
            // Código produto
            add(codigoLabel);
            add(codigoInput);
            // Nome produto
            add(nomeProdutoLabel);
            add(nomeProdutoInput);
            // Descrica produto
            add(descricaoLabel);
            add(descricaoInput);
            // Nome fornecedor
            add(nomeFornecedorLabel);
            add(nomeFornecedorInput);
            // Preco produto
            add(precoLabel);
            add(precoInput);
            // Quantidade produto
            add(quantidadeLabel);
            add(quantidadeInput);
            // Desconto VIP produto
            add(descontoVipLabel);
            add(descontoVipInput);
            // Status produto
            add(statusLabel);
            add(statusCheckBox);

            //-=| Botões |=-//
            add(buttonCancelar);
            add(buttonEditar);
        }
    };

    Insets insets; // Chamando insets para estilização do GridBaglayout

    //-----===| CONSTRUTOR |===-----///
    public JanelaConfirmarCompra(JPanel parent, List<main.model.Estoque> estoques, DefaultTableModel tableModel, JTable table, String cpfCliente, List<main.model.Estoque> produtosCompra){
        super((JFrame) SwingUtilities.getWindowAncestor(parent), "Confirmar Compra", true);
        // Adicionando mainPanel ao JFrame
        this.add(mainPanel);
        // Setando layout
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Criando a tela
        JPanel comprar = criarJanela(estoques, tableModel, table);
        // Atribuindo valores pegos aos inputs
        codigoInput.setText(codigo);
        nomeProdutoInput.setText(nomeProduto);
        descricaoInput.setText(descricao);
        nomeFornecedorInput.setText(nomeFornecedor);
        precoInput.setText(produtosCompra.get);
        quantidadeInput.setText(quantidade);
        descontoVipInput.setText(desconto);
        if(status.equals("ativo")){
            statusCheckBox.setSelected(true);
        }else{
            statusCheckBox.setSelected(false);
        }

        // Adicionando tela ao Painel de telas
        mainPanel.add(cadastrar);

        //---=| Setando o Frame |=---//
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setModal(true);
    }

    //-----===| MÉTODOS |===-----//
    //---=| Janela Cadastrar |=---//
    private JPanel criarEditar(List<main.model.Estoque> estoques, DefaultTableModel tableModel, JTable table, int linhaSelecionada){
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
            1, 2, // Codigo Produto
            3, 4, // Nome Produto
            5, 6, // Descricao produto
            7, 8, // Nome Fornecedor
            9, 10, // Preco Produto
            11, 12, // Quantidade Produto
            13, 14, // Desconto VIP
            15, 16, // Status Produto
            17, 18 // Botões
        };
        // Declarando os valores de cada item
        int[][] posicaoComponentes = {
            {0, 0, 2, 1, 2, 1, 5, 5, 5, 5}, // Título Login

            {0, 1, 1, 1, 1, 1, 5, 5, 5, 5}, // label codigo
            {1, 1, 1, 1, 1, 1, 5, 5, 5, 5}, // input codigo

            {0, 2, 1, 1, 1, 1, 5, 5, 5, 5}, // label nome produto
            {1, 2, 1, 1, 1, 1, 5, 5, 5, 5}, // input nome produto

            {0, 3, 1, 1, 1, 1, 5, 5, 5, 5}, // label descricao
            {1, 3, 1, 1, 1, 1, 5, 5, 5, 5}, // input descricao

            {0, 4, 1, 1, 1, 1, 5, 5, 5, 5}, // label nome fornecedor
            {1, 4, 1, 1, 1, 1, 5, 5, 5, 5}, // input nome fornecedor

            {0, 5, 1, 1, 1, 1, 5, 5, 5, 5}, // label preco
            {1, 5, 1, 1, 1, 1, 5, 5, 5, 5}, // input preco

            {0, 6, 1, 1, 1, 1, 5, 5, 5, 5}, // label quantidade
            {1, 6, 1, 1, 1, 1, 5, 5, 5, 5}, // input quantidade

            {0, 7, 1, 1, 1, 1, 5, 5, 5, 5}, // label desconto vip
            {1, 7, 1, 1, 1, 1, 5, 5, 5, 5}, // input desconto vip

            {0, 8, 1, 1, 1, 1, 5, 5, 5, 5}, // label status 
            {1, 8, 1, 1, 1, 1, 5, 5, 5, 5}, // checkbox status

            {0, 9, 1, 1, 1, 1, 5, 5, 5, 5}, // botão cancelar
            {1, 9, 1, 1, 1, 1, 5, 5, 5, 5}, // botão editar
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
        // Botão editar
        buttonEditar.addActionListener(e ->{
            estoquesControl = new EstoquesControl(estoques, tableModel, table);

            if(estoquesControl.checkEstoqueCampos(linhaSelecionada, "atualizar", codigoInput.getText(), nomeProdutoInput.getText(), descricaoInput.getText(), nomeFornecedorInput.getText(), precoInput.getText(), quantidadeInput.getText(), descontoVipInput.getText(), statusCheckBox.isSelected())){
                // "Resetando" campos
                codigoInput.setText("");
                nomeProdutoInput.setText("");
                descricaoInput.setText("");
                nomeFornecedorInput.setText("");
                precoInput.setText("");
                quantidadeInput.setText("");
                descontoVipInput.setText("");
                statusCheckBox.setSelected(false);
    
                dispose(); // Fecha a janela
            }
        });
        // Botão cancelar
        buttonCancelar.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(null, "Cancelar Edição?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if(resposta == JOptionPane.YES_OPTION){
                // "Resetando" campos
                codigoInput.setText("");
                nomeProdutoInput.setText("");
                descricaoInput.setText("");
                nomeFornecedorInput.setText("");
                precoInput.setText("");
                quantidadeInput.setText("");
                descontoVipInput.setText("");
                statusCheckBox.setSelected(false);
    
                dispose(); // Fecha a janela
            }
        });

        return telaCadastrar;
    }
}
