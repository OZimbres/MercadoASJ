package main.view.venda;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import main.control.EstoquesControl;
import main.dao.EstoquesDAO;
import main.model.Estoque;

public class JanelaConfirmarCompra extends JDialog {
    //-----===| ATRIBUTOS |===-----//
    // CONTROL
    private EstoquesControl estoquesControl;

    //-----===| Criando componentes |===-----//
    private JPanel mainPanel = new JPanel();

    //---=| Cadastro |=---//
    // Título Janela
    private JLabel tituloJanela = new JLabel("Confirmar Compra");

    // Cliente VIP
    private JLabel labelClienteVipTitulo = new JLabel("Cliente VIP:");
    private JLabel labelClienteVipInfo = new JLabel("");

    // Tabela resumindo Compra
    private JScrollPane scrollPane = new JScrollPane();
    private List<Estoque> produtosCompra;
    private JTable table;
    private DefaultTableModel tableModel;

    // Preço / Preço com DESCONTO
    private JLabel totalPreco = new JLabel("0.00");
    private JLabel totalPrecoDesconto = new JLabel("");

    // Método de Pagamento
    private JLabel labelMetodoDePagamento = new JLabel("Método de pagamento");
    private JComboBox<String> metodoDePagamentoComboBox = new JComboBox<>();

    // Botão Cadastrar/Cancelar
    private JButton buttonCancelar = new JButton("Cancelar");
    private JButton buttonComprar = new JButton("Comprar");

    // ArrayList dos componentes a serem exibidos
    ArrayList<JComponent> componentes = new ArrayList<JComponent>(){
        {
            //-=| Título |=-//
            add(tituloJanela);

            //-=| Cliente |=-//
            add(labelClienteVipTitulo);
            add(labelClienteVipInfo);

            //-=| ScrollPane |=-//
            add(scrollPane);

            //-=| Preço |=-//
            add(totalPreco);
            add(totalPrecoDesconto);

            //-=| Método de Pagamento |=-//
            add(labelMetodoDePagamento);
            add(metodoDePagamentoComboBox);

            //-=| Botões |=-//
            add(buttonCancelar);
            add(buttonComprar);
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
        JPanel comprar = criarJanela(produtosCompra, tableModel, table, estoques);

        // Adicionando tela ao Painel de telas
        mainPanel.add(comprar);

        //---=| Setando o Frame |=---//
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setModal(true);
    }

    //-----===| MÉTODOS |===-----//
    //---=| Janela Cadastrar |=---//
    private JPanel criarJanela(List<main.model.Estoque> produtosCompra, DefaultTableModel tableModel, JTable table, List<Estoque> estoqueProdutos){
        JPanel telaComprar = new JPanel();
        // Setando layout
        telaComprar.setLayout(new GridBagLayout());
        // Setando aparência básica
        telaComprar.setBackground(Color.DARK_GRAY);
        telaComprar.setForeground(Color.WHITE);

        // Configurando Painel
        GridBagConstraints elemento = new GridBagConstraints(); // Variável de controle de exibição de cada elemento

        // Declarando os itens a serem utilizados (sua posição no ArrayList)
        int[] posicaoNoArray = {
            0, // Título
            1, // Cliente VIP Título
            2, // Cliente VIP Info
            3, // ScrollPane
            4, // Total Preço
            5, // Total Preço Desconto VIP
            6, 7 // Botões
        };
        // Declarando os valores de cada item
        int[][] posicaoComponentes = {
            {0, 0, 2, 1, 2, 1, 5, 5, 5, 5}, // Título

            {0, 1, 2, 1, 2, 1, 5, 5, 5, 5}, // Cliente VIP Título
            {0, 2, 2, 1, 2, 1, 5, 5, 5, 5}, // Cliente VIP Info

            {0, 3, 2, 1, 2, 1, 5, 5, 5, 5}, // Scroll Pane

            {0, 4, 2, 1, 2, 1, 5, 5, 5, 5}, // Total Preço
            {0, 5, 2, 1, 2, 1, 5, 5, 5, 5}, // Total Preço Desconto VIP

            {0, 6, 1, 1, 1, 1, 5, 5, 5, 5}, // Botão Cancelar
            {1, 6, 1, 1, 1, 1, 5, 5, 5, 5}, // Botão Comprar
        };

        // Configurand modelo de exibição
        for(int i = 0; i < 8; i++){ // Menor que 8 pois 8 é a quantidade de item da janela
            elemento.gridx = posicaoComponentes[i][0];
            elemento.gridy = posicaoComponentes[i][1];
            elemento.gridwidth = posicaoComponentes[i][2];
            elemento.gridheight = posicaoComponentes[i][3];
            elemento.weightx = posicaoComponentes[i][4];
            elemento.weighty = posicaoComponentes[i][5];
            elemento.insets = new Insets(posicaoComponentes[i][6], posicaoComponentes[i][7], posicaoComponentes[i][8], posicaoComponentes[i][9]);

            telaComprar.add(componentes.get(posicaoNoArray[i]), elemento);
        }

        //---=| Tratamento de Evento |=---//
        // Botão editar
        buttonComprar.addActionListener(e ->{
            try{
                estoquesControl = new EstoquesControl(estoqueProdutos, tableModel, table);
                EstoquesDAO estoquesDAO = new EstoquesDAO();
    
                for (Estoque produto : produtosCompra) {
                    Integer quantidadeSobrouEstoque;
                    quantidadeSobrouEstoque = Integer.valueOf(estoquesDAO.read(produto.getCodigoProduto()).getQuantidadeProduto() - Integer.valueOf(produto.getQuantidadeProduto()));

                    Boolean statusProduto;
                    if(quantidadeSobrouEstoque > 0){
                        statusProduto = true;
                    } else{
                        statusProduto = false;
                    }
    
                    estoquesControl.checkEstoqueCampos(-1, "venda", produto.getCodigoProduto(), produto.getNomeProduto(), produto.getDescricaoProduto(), produto.getNomeFornecedor(), String.valueOf(produto.getPrecoProduto()), String.valueOf(quantidadeSobrouEstoque), String.valueOf(produto.getDescontoVip()), statusProduto);
                }
    
                dispose(); // Fecha a janela
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ ex);
            }
        });
        // Botão cancelar
        buttonCancelar.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(null, "Cancelar Edição?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if(resposta == JOptionPane.YES_OPTION){
                dispose(); // Fecha a janela
            }
        });

        return telaComprar;
    }
}
