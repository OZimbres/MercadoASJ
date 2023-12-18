package main.view.venda;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class JanelaConfirmarCompra extends JFrame {
    /**
     * Creates new form ConcluirCompra
     */
    public JanelaConfirmarCompra( List<main.model.Estoque> estoques, DefaultTableModel tableModel, JTable table, String clienteInfo, String precoTotalSemDesconto, List<main.model.Estoque> produtosCompra) {
        this.setSize(350, 550);
        this.setLocationRelativeTo(null);
        initComponents(estoques, tableModel, table, clienteInfo, precoTotalSemDesconto, produtosCompra);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents(List<main.model.Estoque> estoques, DefaultTableModel tableModel, JTable table, String clienteInfo, String precoTotalSemDesconto, List<main.model.Estoque> produtosCompra) {

        mainPanel = new javax.swing.JPanel();

        jLabelTitulo = new javax.swing.JLabel();
        jPanelClienteVip = new javax.swing.JPanel();
        jLabelClienteVipTitulo = new javax.swing.JLabel();
        jLabelClienteVipInfo = new javax.swing.JLabel(clienteInfo);
        jScrollPane = new javax.swing.JScrollPane();
        jPanelMetodoDePagamento = new javax.swing.JPanel();
        jLabelMetodoDePagamento = new javax.swing.JLabel();
        jComboBoxMetodoDePagamento = new javax.swing.JComboBox<>();
        btnCancelar = new javax.swing.JButton();
        btnConcluir = new javax.swing.JButton();
        jPanelTotal = new javax.swing.JPanel();
        jLabelTotalAntes = new javax.swing.JLabel();
        jLabelTotalDepois = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(250, 435));

        jLabelTitulo.setFont(new java.awt.Font("Liberation Mono", 1, 18)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Concluir Compra");
        jLabelTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanelClienteVip.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelClienteVipTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelClienteVipTitulo.setText("Cliente VIP");

        jLabelClienteVipInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelClienteVipInfo.setMaximumSize(new java.awt.Dimension(0, 18));
        jLabelClienteVipInfo.setMinimumSize(new java.awt.Dimension(0, 18));
        jLabelClienteVipInfo.setPreferredSize(new java.awt.Dimension(0, 18));
        // Checar para passar Info

        javax.swing.GroupLayout jPanelClienteVipLayout = new javax.swing.GroupLayout(jPanelClienteVip);
        jPanelClienteVip.setLayout(jPanelClienteVipLayout);
        jPanelClienteVipLayout.setHorizontalGroup(
            jPanelClienteVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClienteVipLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelClienteVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelClienteVipTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelClienteVipInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelClienteVipLayout.setVerticalGroup(
            jPanelClienteVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClienteVipLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelClienteVipTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelClienteVipInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] {"COD","Produto"});
        table = new JTable(tableModel);
        jScrollPane.setViewportView(table);
        atualizarCompra(tableModel, produtosCompra);


        jPanelMetodoDePagamento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelMetodoDePagamento.setText("Método de Pagamento");

        jComboBoxMetodoDePagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Crédito", "Débito", "Dinheiro", "Cheque", "Pix" }));
        jComboBoxMetodoDePagamento.setBorder(new javax.swing.border.MatteBorder(null));
        jComboBoxMetodoDePagamento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanelMetodoDePagamentoLayout = new javax.swing.GroupLayout(jPanelMetodoDePagamento);
        jPanelMetodoDePagamento.setLayout(jPanelMetodoDePagamentoLayout);
        jPanelMetodoDePagamentoLayout.setHorizontalGroup(
            jPanelMetodoDePagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMetodoDePagamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMetodoDePagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMetodoDePagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxMetodoDePagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelMetodoDePagamentoLayout.setVerticalGroup(
            jPanelMetodoDePagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMetodoDePagamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMetodoDePagamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxMetodoDePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setBackground(new java.awt.Color(255, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Liberation Mono", 1, 15)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnConcluir.setBackground(new java.awt.Color(0, 255, 0));
        btnConcluir.setFont(new java.awt.Font("Liberation Mono", 1, 15)); // NOI18N
        btnConcluir.setText("Concluir");
        btnConcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanelTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTotalAntes.setText("Total:");

        jLabelTotalDepois.setFont(new java.awt.Font("Liberation Mono", 1, 24)); // NOI18N
        jLabelTotalDepois.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        // Display do preço vai depender se o Cliente comprando é VIP ou não
        // Padrão Regex para pedar o cpf do VIP selecionado
        Pattern pattern = Pattern.compile("CPF: (-?\\d+)\\s*\\|");
        // Criando um Matcher que corresponde ao padrão na entrada
        Matcher matcher = pattern.matcher(jLabelClienteVipInfo.getText());
        if(matcher.find()){
            jLabelTotalAntes.setText("Total de "+precoTotalSemDesconto+" por:");
            jLabelTotalDepois.setText(String.format("%,.2f", compraComDesconto(produtosCompra)));
        } else {
            jLabelTotalDepois.setText(precoTotalSemDesconto);
        }

        javax.swing.GroupLayout jPanelTotalLayout = new javax.swing.GroupLayout(jPanelTotal);
        jPanelTotal.setLayout(jPanelTotalLayout);
        jPanelTotalLayout.setHorizontalGroup(
            jPanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTotalDepois, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTotalAntes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelTotalLayout.setVerticalGroup(
            jPanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTotalAntes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTotalDepois, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelClienteVip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelMetodoDePagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(btnConcluir))
                    .addComponent(jPanelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelClienteVip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMetodoDePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConcluir)
                    .addComponent(btnCancelar)))
        );

        this.add(mainPanel);
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConcluir;
    private javax.swing.JComboBox<String> jComboBoxMetodoDePagamento;
    private javax.swing.JLabel jLabelClienteVipInfo;
    private javax.swing.JLabel jLabelClienteVipTitulo;
    private javax.swing.JLabel jLabelMetodoDePagamento;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTotalAntes;
    private javax.swing.JLabel jLabelTotalDepois;
    private javax.swing.JPanel jPanelClienteVip;
    private javax.swing.JPanel jPanelMetodoDePagamento;
    private javax.swing.JPanel jPanelTotal;
    private javax.swing.JScrollPane jScrollPane;

    private javax.swing.JPanel mainPanel;
    // End of variables declaration

    private void atualizarCompra(DefaultTableModel tableModel, List<main.model.Estoque> produtosCompra){
        tableModel.setRowCount(0);
        Object linha[] = new Object[6];

        if(produtosCompra != null){
            for(int i = 0; i < produtosCompra.size(); i++){
                linha[0] = produtosCompra.get(i).getCodigoProduto();
                linha[1] = produtosCompra.get(i).getNomeProduto();
                linha[2] = produtosCompra.get(i).getDescricaoProduto();
                linha[3] = String.format("%,.2f", produtosCompra.get(i).getPrecoProduto()).replaceAll("[.]", "").replace(",", ".");
                linha[4] = produtosCompra.get(i).getDescontoVip();
                linha[5] = produtosCompra.get(i).getQuantidadeProduto();
                tableModel.addRow(linha);
            }
        }
    }
    private double compraComDesconto(List<main.model.Estoque> produtosCompra){
        Double precoTotalComDesconto = 0.0;
        if(produtosCompra != null){
            for(int i = 0; i < produtosCompra.size(); i++){
                precoTotalComDesconto += produtosCompra.get(i).getPrecoProduto() * (1 - (produtosCompra.get(i).getDescontoVip() / 100));
            }
        }
        return precoTotalComDesconto;
    }
}
