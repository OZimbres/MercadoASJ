package main.view.estoque;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.control.estoque.EstoquesControl;
import main.control.estoque.EstoquesDAO;
import main.model.Estoque;

public class PainelEstoque extends JPanel {
    //-----===| ATRIBUTOS |===-----//
    private JButton buttonCadastrar, buttonApagar, buttonEditar;
    private JLabel produtoInfo = new JLabel("Índice | Código Produto: | Nome Produto: | Quantidade Produto: | Preço Produto: | Desconto VIP: | Status: ");
    private List<Estoque> estoqueProdutos;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    private EstoquesControl EstoquesControl;

    //-----===| CONSTRUTOR |===-----//
    public PainelEstoque() {
        super();

        // Entrada de dados
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Estoques"));
        
        // "Menu" dos botões
        JPanel botoes = new JPanel();
        botoes.add(buttonCadastrar = new JButton("Cadastrar"));
        botoes.add(buttonEditar = new JButton("Editar"));
        botoes.add(buttonApagar = new JButton("Apagar"));

        // Informação do Estoque selecionado sendo exibida
        JPanel inputPanel = new JPanel();
        inputPanel.add(produtoInfo);

        // Adicionando paineis ao JPanel da janela
        this.add(botoes);
        this.add(inputPanel);

        // Tabela de Estoques
        JScrollPane scrollPane = new JScrollPane();
        this.add(scrollPane); // Adicionando scrollPane ao JPanel da janela
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] {"Código", "Nome", "Descrição", "Fornecedor", "Preço Item", "Quantidade", "Desconto VIP", "Status"}); // "Desenhando" organização da tabela com o tableModel
        table = new JTable(tableModel); // Declarando a tabela com o estilo definido no tableModel
        scrollPane.setViewportView(table);

        new EstoquesDAO();

        atualizarTabela();

        //---=| Tratamento de Evento |=---//
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                linhaSelecionada = table.rowAtPoint(evt.getPoint()); // Pegando a linha selecionada (clicada)

                if(linhaSelecionada != -1){
                    // Variáveis temporárias
                    String codigo = String.valueOf(table.getValueAt(linhaSelecionada, 0));
                    String nome = String.valueOf(table.getValueAt(linhaSelecionada, 1));
                    String quantidade = String.valueOf(table.getValueAt(linhaSelecionada, 2));
                    String preco = String.valueOf(table.getValueAt(linhaSelecionada, 3));
                    String desconto = String.valueOf(table.getValueAt(linhaSelecionada, 4));
                    String status = String.valueOf(table.getValueAt(linhaSelecionada, 5));
                    
                    produtoInfo.setText("Índice "+ linhaSelecionada +" | Código Produto: "+ codigo +" | Nome Produto: "+ nome +" | Quantidade Produto: "+ quantidade +" | Preço Produto: "+ preco +" | Desconto VIP: "+ desconto +" | Status: "+ status);
                }
            }
        });
        buttonCadastrar.addActionListener(e ->{
            JanelaCadastroEstoque janelaCadastrar = new JanelaCadastroEstoque(this, estoqueProdutos, tableModel, table);
            janelaCadastrar.setVisible(true);
            produtoInfo.setText("Índice | Código Produto: | Nome Produto: | Quantidade Produto: | Preço Produto: | Desconto VIP: | Status: ");
            atualizarTabela();
        });
        buttonEditar.addActionListener(e ->{
            // Vou pegar a linha selecionada a partir do "ìndice" no Estoque Info (usando regex)
            // Padrão Regex para pedar a linha selecionada
            Pattern pattern = Pattern.compile("Índice (-?\\d+)\\s*\\|");

            // Criando um Matcher que corresponde ao padrão na entrada
            Matcher matcher = pattern.matcher(EstoqueInfo.getText());
            if(matcher.find()){
                linhaSelecionada = Integer.valueOf(matcher.group(1));
            }
            
            if(linhaSelecionada == -1){
                JOptionPane.showMessageDialog(this, "É necessário selecionar algum Estoque!");
            } else{
                // Variáveis temporárias
                String cpf = String.valueOf(table.getValueAt(linhaSelecionada, 0));
                String nome = String.valueOf(table.getValueAt(linhaSelecionada, 1));
                String telefone = String.valueOf(table.getValueAt(linhaSelecionada, 2));
                String rua = String.valueOf(table.getValueAt(linhaSelecionada, 3));
                String numero = String.valueOf(table.getValueAt(linhaSelecionada, 4));
                String cep = String.valueOf(table.getValueAt(linhaSelecionada, 5));

                JanelaEditaEstoque janelaEdita = new JanelaEditaEstoque(this, Estoques, tableModel, table, linhaSelecionada, cpf, nome, telefone, rua, numero, cep);
                janelaEdita.setVisible(true);
                EstoqueInfo.setText("Índice | CPF: | Nome: ");
                atualizarTabela();
            }
        });
        buttonApagar.addActionListener(e ->{
            // Vou pegar a linha selecionada a partir do "ìndice" no Estoque Info (usando regex)
            // Padrão Regex para pedar a linha selecionada
            Pattern pattern = Pattern.compile("Índice (-?\\d+)\\s*\\|");

            // Criando um Matcher que corresponde ao padrão na entrada
            Matcher matcher = pattern.matcher(EstoqueInfo.getText());
            if(matcher.find()){
                linhaSelecionada = Integer.valueOf(matcher.group(1));
            }
            
            if(linhaSelecionada == -1){
                JOptionPane.showMessageDialog(this, "É necessário selecionar algum Estoque!");
            } else{
                // Variável temporária
                String cpf = String.valueOf(table.getValueAt(linhaSelecionada, 0));

                EstoquesControl EstoquesControl = new EstoquesControl(Estoques, tableModel, table);

                if(EstoquesControl.checkEstoqueCampos(linhaSelecionada, "deletar", cpf, "temporario", "", "", "", "")){
                    EstoqueInfo.setText("Índice | CPF: | Nome: ");
                    atualizarTabela();
                }
            }
        });
    }

    private void atualizarTabela() {
        try {
            tableModel.setRowCount(0);
            Estoques = new EstoquesDAO().readAll();
            Object linha[] = new Object[8];

            for (int i = 0; i < Estoques.size(); i++) {
                linha[0] = Estoques.get(i).getCpfEstoque();
                linha[1] = Estoques.get(i).getNomeEstoque();
                linha[2] = (Estoques.get(i).getTelefoneEstoque() == 0) ? "" : Estoques.get(i).getTelefoneEstoque();
                linha[3] = Estoques.get(i).getRuaEstoque();
                linha[4] = Estoques.get(i).getNumeroEstoque();
                linha[5] = (Estoques.get(i).getCepEstoque() == 0) ? "" : Estoques.get(i).getCepEstoque();
                tableModel.addRow(linha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
