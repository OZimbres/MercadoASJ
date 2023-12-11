package main.view.estoque;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
<<<<<<< HEAD
=======

>>>>>>> ffc8ebf391960e402d0d7dc61066f63586d90489
import main.control.EstoquesControl;
import main.dao.EstoquesDAO;
import main.model.Estoque;

public class PainelEstoque extends JPanel {
    //-----===| ATRIBUTOS |===-----//
    private JButton buttonCadastrar, buttonInativar, buttonEditar;
    private JLabel produtoInfo = new JLabel("Índice | Código: | Nome: | Quantidade: | Preço: | Status: ");
    private JComboBox<String> modoExibicaoComboBox = new JComboBox<>();
    private List<Estoque> estoqueProdutos;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    //-----===| CONSTRUTOR |===-----//
    public PainelEstoque() {
        super();

        // Entrada de dados
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Estoques");
        this.add(titulo);
        
        // "Menu" dos botões
        JPanel botoes = new JPanel();
        
        // Modo de EXIBIÇÃO
        modoExibicaoComboBox.addItem("Ordenar por Estado");
        modoExibicaoComboBox.addItem("Ordenar por Atividade");
        modoExibicaoComboBox.setBackground(java.awt.Color.WHITE);
        botoes.add(modoExibicaoComboBox);
        // Botão CADASTRAR
        buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.setBackground(java.awt.Color.WHITE);
        botoes.add(buttonCadastrar);
        // Botão EDITAR
        buttonEditar = new JButton("Editar");
        buttonEditar.setBackground(java.awt.Color.WHITE);
        botoes.add(buttonEditar);
        // Botão INATIVAR
        buttonInativar = new JButton("Inativar");
        buttonInativar.setBackground(java.awt.Color.WHITE);
        botoes.add(buttonInativar);

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
        // Definindo um renderizador de célula personalizado para a tabela
        table.setDefaultRenderer(Object.class, new ColoredCellRenderer());

        scrollPane.setViewportView(table);

        new EstoquesDAO();

        atualizarTabela(String.valueOf(modoExibicaoComboBox.getSelectedItem()));

        //---=| Tratamento de Evento |=---//
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                linhaSelecionada = table.rowAtPoint(evt.getPoint()); // Pegando a linha selecionada (clicada)

                if(linhaSelecionada != -1){
                    // Variáveis temporárias
                    String codigo = String.valueOf(table.getValueAt(linhaSelecionada, 0));
                    String nome = String.valueOf(table.getValueAt(linhaSelecionada, 1));
                    String quantidade = String.valueOf(table.getValueAt(linhaSelecionada, 5));
                    String preco = String.valueOf(table.getValueAt(linhaSelecionada, 4));
                    String desconto = String.valueOf(table.getValueAt(linhaSelecionada, 6));
                    String status = String.valueOf(table.getValueAt(linhaSelecionada, 7));
                    
                    produtoInfo.setText("Índice "+ linhaSelecionada +" | Código: "+ codigo +" | Nome: "+ nome +" | Quantidade: "+ quantidade +" | Preço: "+ preco +" | Status: "+ status);
                }
            }
        });
        buttonCadastrar.addActionListener(e ->{
            JanelaCadastroEstoque janelaCadastrar = new JanelaCadastroEstoque(this, estoqueProdutos, tableModel, table);
            janelaCadastrar.setVisible(true);

            // "Resetando"
            produtoInfo.setText("Índice | Código: | Nome: | Quantidade: | Preço: | Status: ");
            atualizarTabela(String.valueOf(modoExibicaoComboBox.getSelectedItem()));
            linhaSelecionada = -1; 
        });
        buttonEditar.addActionListener(e ->{
            // Vou pegar a linha selecionada a partir do "ìndice" no Estoque Info (usando regex)
            // Padrão Regex para pedar a linha selecionada
            Pattern pattern = Pattern.compile("Índice (-?\\d+)\\s*\\|");

            // Criando um Matcher que corresponde ao padrão na entrada
            Matcher matcher = pattern.matcher(produtoInfo.getText());
            if(matcher.find()){
                linhaSelecionada = Integer.valueOf(matcher.group(1));
            }
            
            if(linhaSelecionada == -1){
                JOptionPane.showMessageDialog(this, "É necessário selecionar algum Estoque!");
            } else{
                // Variáveis temporárias
                String codigo = String.valueOf(table.getValueAt(linhaSelecionada, 0));
                String nomeProduto = String.valueOf(table.getValueAt(linhaSelecionada, 1));
                String descricao = String.valueOf(table.getValueAt(linhaSelecionada, 2));
                String nomeFornecedor = String.valueOf(table.getValueAt(linhaSelecionada, 3));
                String preco = String.valueOf(table.getValueAt(linhaSelecionada, 4));
                String quantidade = String.valueOf(table.getValueAt(linhaSelecionada, 5));
                String desconto = String.valueOf(table.getValueAt(linhaSelecionada, 6));
                String status = String.valueOf(table.getValueAt(linhaSelecionada, 7));

                JanelaEditaEstoque janelaEdita = new JanelaEditaEstoque(this, estoqueProdutos, tableModel, table, linhaSelecionada, codigo, nomeProduto, descricao, nomeFornecedor, preco, quantidade, desconto, status);
                janelaEdita.setVisible(true);
                // "Resetando"
                atualizarTabela(String.valueOf(modoExibicaoComboBox.getSelectedItem()));
                produtoInfo.setText("Índice | Código: | Nome: | Quantidade: | Preço: | Status: ");
                linhaSelecionada = -1; 
            }
        });
        buttonInativar.addActionListener(e ->{
            // Vou pegar a linha selecionada a partir do "ìndice" no Estoque Info (usando regex)
            // Padrão Regex para pedar a linha selecionada
            Pattern pattern = Pattern.compile("Índice (-?\\d+)\\s*\\|");

            // Criando um Matcher que corresponde ao padrão na entrada
            Matcher matcher = pattern.matcher(produtoInfo.getText());
            if(matcher.find()){
                linhaSelecionada = Integer.valueOf(matcher.group(1));
            }
            
            if(linhaSelecionada == -1){
                JOptionPane.showMessageDialog(this, "É necessário selecionar algum Estoque!");
            } else{
                // Variável temporária
                String codigo = String.valueOf(table.getValueAt(linhaSelecionada, 0));

                EstoquesControl estoqueControl = new EstoquesControl(estoqueProdutos, tableModel, table);

                if(estoqueControl.checkEstoqueCampos(linhaSelecionada, "apagar", codigo, "temporario", "temporario", "temporario", "0", "0", "0", false)){
                    // "Resetando"
                    produtoInfo.setText("Índice | Código: | Nome: | Quantidade: | Preço: | Status: ");
                    atualizarTabela(String.valueOf(modoExibicaoComboBox.getSelectedItem()));    
                    linhaSelecionada = -1;            
                }
            }
        });
        modoExibicaoComboBox.addActionListener(e -> {
            atualizarTabela(String.valueOf(modoExibicaoComboBox.getSelectedItem()));
        });
    }

    private void atualizarTabela(String modelo) {
        try {
            tableModel.setRowCount(0);
            estoqueProdutos = new EstoquesDAO().readAll();
            Object linha[] = new Object[8];

            if(modelo.equals("Ordenar por Estado")){

                // Ordenar tabela por Estoque bom, Estoque em Atenção, estoque em alerta e inativo
                // Estoque BOM
                for (int i = 0; i < estoqueProdutos.size(); i++) {
                    if(estoqueProdutos.get(i).getQuantidadeProduto() > 200 & estoqueProdutos.get(i).getStatusProduto()){
                        linha[0] = estoqueProdutos.get(i).getCodigoProduto();
                        linha[1] = estoqueProdutos.get(i).getNomeProduto();
                        linha[2] = estoqueProdutos.get(i).getDescricaoProduto();
                        linha[3] = estoqueProdutos.get(i).getNomeFornecedor();
                        linha[4] = estoqueProdutos.get(i).getPrecoProduto();
                        linha[5] = estoqueProdutos.get(i).getQuantidadeProduto();
                        linha[6] = estoqueProdutos.get(i).getDescontoVip();
                        linha[7] = "ativo";
                        tableModel.addRow(linha);
                    }
                }
    
                // Estoque ATENÇÃO
                for (int i = 0; i < estoqueProdutos.size(); i++) {
                    if(estoqueProdutos.get(i).getQuantidadeProduto() <= 200 & estoqueProdutos.get(i).getQuantidadeProduto() > 50 & estoqueProdutos.get(i).getStatusProduto()){
                        linha[0] = estoqueProdutos.get(i).getCodigoProduto();
                        linha[1] = estoqueProdutos.get(i).getNomeProduto();
                        linha[2] = estoqueProdutos.get(i).getDescricaoProduto();
                        linha[3] = estoqueProdutos.get(i).getNomeFornecedor();
                        linha[4] = estoqueProdutos.get(i).getPrecoProduto();
                        linha[5] = estoqueProdutos.get(i).getQuantidadeProduto();
                        linha[6] = estoqueProdutos.get(i).getDescontoVip();
                        linha[7] = "ativo";
                        tableModel.addRow(linha);
                    }
                }
    
                // Estoque ALERTA!!
                for (int i = 0; i < estoqueProdutos.size(); i++) {
                    if(estoqueProdutos.get(i).getQuantidadeProduto() <= 50 & estoqueProdutos.get(i).getQuantidadeProduto() >= 0 & estoqueProdutos.get(i).getStatusProduto()){
                        linha[0] = estoqueProdutos.get(i).getCodigoProduto();
                        linha[1] = estoqueProdutos.get(i).getNomeProduto();
                        linha[2] = estoqueProdutos.get(i).getDescricaoProduto();
                        linha[3] = estoqueProdutos.get(i).getNomeFornecedor();
                        linha[4] = estoqueProdutos.get(i).getPrecoProduto();
                        linha[5] = estoqueProdutos.get(i).getQuantidadeProduto();
                        linha[6] = estoqueProdutos.get(i).getDescontoVip();
                        linha[7] = "ativo";
                        tableModel.addRow(linha);
                    }
                }
    
                // Estoque INATIVO
                for (int i = 0; i < estoqueProdutos.size(); i++) {
                    if(!estoqueProdutos.get(i).getStatusProduto()){
                        linha[0] = estoqueProdutos.get(i).getCodigoProduto();
                        linha[1] = estoqueProdutos.get(i).getNomeProduto();
                        linha[2] = estoqueProdutos.get(i).getDescricaoProduto();
                        linha[3] = estoqueProdutos.get(i).getNomeFornecedor();
                        linha[4] = estoqueProdutos.get(i).getPrecoProduto();
                        linha[5] = estoqueProdutos.get(i).getQuantidadeProduto();
                        linha[6] = estoqueProdutos.get(i).getDescontoVip();
                        linha[7] = "inativo";
                        tableModel.addRow(linha);
                    }
                }
            } else {
                for (int i = 0; i < estoqueProdutos.size(); i++) {
                    linha[0] = estoqueProdutos.get(i).getCodigoProduto();
                    linha[1] = estoqueProdutos.get(i).getNomeProduto();
                    linha[2] = estoqueProdutos.get(i).getDescricaoProduto();
                    linha[3] = estoqueProdutos.get(i).getNomeFornecedor();
                    linha[4] = estoqueProdutos.get(i).getPrecoProduto();
                    linha[5] = estoqueProdutos.get(i).getQuantidadeProduto();
                    linha[6] = estoqueProdutos.get(i).getDescontoVip();
                    linha[7] = (estoqueProdutos.get(i).getStatusProduto()) ? "ativo" : "inativo";
                    tableModel.insertRow(0, linha);
                }
            }

            // Configurando o renderizador de célula personalizado após a tabela ser atualizada
            table.setDefaultRenderer(Object.class, new ColoredCellRenderer());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Renderizador de célula personalizado
    static class ColoredCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Obtendo o valor da célula de quantidade
            int quantidade = Integer.parseInt(table.getValueAt(row, 5).toString());
            String status = String.valueOf(table.getValueAt(row, 7));

            // Alterando a cor de fundo da linha com base no valor da quantidade
            if(status.equals("inativo")){
                cellComponent.setBackground(java.awt.Color.DARK_GRAY);
                cellComponent.setForeground(java.awt.Color.GRAY);
            } else if (quantidade <= 50) {
                cellComponent.setBackground(java.awt.Color.RED);
                cellComponent.setForeground(java.awt.Color.BLACK);
            } else if (quantidade <= 200) {
                cellComponent.setBackground(java.awt.Color.YELLOW);
                cellComponent.setForeground(java.awt.Color.BLACK);
            } else {
                // Cor padrão se a quantidade for maior ou igual a 200
                cellComponent.setBackground(java.awt.Color.CYAN);
                cellComponent.setForeground(java.awt.Color.BLACK);
            }

            return cellComponent;
        }
    }
}
