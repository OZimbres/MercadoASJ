package main.view.venda;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.sql.SQLException;
import java.util.List;

import main.control.EstoquesControl;
import main.dao.EstoquesDAO;
import main.model.Estoque;

public class PainelVenda extends JPanel {
    /**
     * Creates new form Global
     */
    public PainelVenda() {
        atualizarEstoque();
        initComponents();
    }

     /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        jPanelCompra = new javax.swing.JPanel();
        jPanelCliente = new javax.swing.JPanel();
        labelClienteTitulo = new javax.swing.JLabel();
        labelCpfCliente = new javax.swing.JLabel();
        inputCpfCliente = new javax.swing.JTextField();
        labelClienteInfo = new javax.swing.JLabel();
        btnSelecionarCliente = new javax.swing.JButton();
        btnBuscarCliente = new javax.swing.JButton();
        jPanelProduto = new javax.swing.JPanel();
        labelProdutoTitulo = new javax.swing.JLabel();
        labelProdutoCod = new javax.swing.JLabel();
        inputProdutoCod = new javax.swing.JTextField();
        labelProdutoInfo = new javax.swing.JLabel();
        labelProdutoQtd = new javax.swing.JLabel();
        btnMenosProdutoQtd = new javax.swing.JButton();
        inputProdutoQtd = new javax.swing.JTextField();
        btnMaisProdutoQtd = new javax.swing.JButton();
        btnAdicionarProduto = new javax.swing.JButton();
        btnBuscarProduto = new javax.swing.JButton();
        jScrollPaneCompraFull = new javax.swing.JScrollPane();
        jPanelCompraResumida = new javax.swing.JPanel();
        jPanelCompraResumidaClienteVip = new javax.swing.JPanel();
        labelCVipCompraResumida = new javax.swing.JLabel();
        btnRemoverCVipCompraResumida = new javax.swing.JButton();
        jScrollPaneCompraResumida = new javax.swing.JScrollPane();
        jPanelInfoCompraResumida = new javax.swing.JPanel();
        btnConcluirCompraResumida = new javax.swing.JButton();
        btnCancelarCompraResumida = new javax.swing.JButton();
        labelTotalCompraResumida = new javax.swing.JLabel();
        jLabelPrecoCompraResumida = new javax.swing.JLabel();

        jPanelCompra.setPreferredSize(new java.awt.Dimension(500, 400));

        jPanelCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelClienteTitulo.setText("Cliente VIP");

        labelCpfCliente.setText("CPF:");

        inputCpfCliente.setMinimumSize(new java.awt.Dimension(120, 24));
        inputCpfCliente.setPreferredSize(new java.awt.Dimension(120, 24));

        labelClienteInfo.setText("Nome:");

        btnSelecionarCliente.setBackground(new java.awt.Color(0, 255, 0));
        btnSelecionarCliente.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        btnSelecionarCliente.setText("Selecionar");
        btnSelecionarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanelClienteLayout = new javax.swing.GroupLayout(jPanelCliente);
        jPanelCliente.setLayout(jPanelClienteLayout);
        jPanelClienteLayout.setHorizontalGroup(
            jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelClienteLayout.createSequentialGroup()
                        .addComponent(labelClienteInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(btnSelecionarCliente))
                    .addGroup(jPanelClienteLayout.createSequentialGroup()
                        .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelClienteTitulo)
                            .addGroup(jPanelClienteLayout.createSequentialGroup()
                                .addComponent(labelCpfCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputCpfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarCliente)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelClienteLayout.setVerticalGroup(
            jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelClienteTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCpfCliente)
                    .addComponent(inputCpfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelClienteInfo)
                    .addComponent(btnSelecionarCliente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelClienteTitulo.getAccessibleContext().setAccessibleName("Cliente VIP");

        jPanelProduto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelProduto.setMinimumSize(new java.awt.Dimension(100, 116));
        jPanelProduto.setPreferredSize(new java.awt.Dimension(500, 100));

        labelProdutoTitulo.setText("Produto");

        labelProdutoCod.setText("CÓD:");

        inputProdutoCod.setMinimumSize(new java.awt.Dimension(50, 24));
        inputProdutoCod.setPreferredSize(new java.awt.Dimension(50, 24));
        inputProdutoCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputProdutoCodActionPerformed(evt);
            }
        });

        labelProdutoInfo.setText("CÓD: | Produto: | P.U.: | D.VIP:");

        labelProdutoQtd.setText("QTD:");

        btnMenosProdutoQtd.setText("-");
        btnMenosProdutoQtd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        inputProdutoQtd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputProdutoQtd.setText("0");
        inputProdutoQtd.setMinimumSize(new java.awt.Dimension(70, 24));
        inputProdutoQtd.setPreferredSize(new java.awt.Dimension(70, 24));
        inputProdutoQtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputProdutoQtdActionPerformed(evt);
            }
        });

        btnMaisProdutoQtd.setText("+");
        btnMaisProdutoQtd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMaisProdutoQtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaisProdutoQtdActionPerformed(evt);
            }
        });

        btnAdicionarProduto.setBackground(new java.awt.Color(0, 255, 0));
        btnAdicionarProduto.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        btnAdicionarProduto.setText("Adicionar");
        btnAdicionarProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnBuscarProduto.setText("Buscar");
        btnBuscarProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Events
        btnBuscarProduto.addActionListener(e -> {
            estoqueControl = new EstoquesControl(estoqueProdutos, tableModelFull, tableFull);
            Estoque produtoTemp = estoqueControl.readEstoque(inputProdutoCod.getText());
            labelProdutoInfo.setText("CÓD: "+produtoTemp.getCodigoProduto()+" | Produto: "+produtoTemp.getNomeProduto()+" | P.U.: "+produtoTemp.getPrecoProduto()+" | D.VIP:"+produtoTemp.getDescontoVip());
        });

        javax.swing.GroupLayout jPanelProdutoLayout = new javax.swing.GroupLayout(jPanelProduto);
        jPanelProduto.setLayout(jPanelProdutoLayout);
        jPanelProdutoLayout.setHorizontalGroup(
            jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addComponent(labelProdutoQtd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMenosProdutoQtd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputProdutoQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMaisProdutoQtd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdicionarProduto))
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelProdutoTitulo)
                            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                .addComponent(labelProdutoCod)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputProdutoCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarProduto))
                            .addComponent(labelProdutoInfo))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelProdutoLayout.setVerticalGroup(
            jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelProdutoTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProdutoCod)
                    .addComponent(inputProdutoCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelProdutoInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProdutoQtd)
                    .addComponent(btnMenosProdutoQtd)
                    .addComponent(inputProdutoQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMaisProdutoQtd)
                    .addComponent(btnAdicionarProduto))
                .addContainerGap())
        );

        jScrollPaneCompraFull.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPaneCompraFull.setAutoscrolls(true);
        jScrollPaneCompraFull.setPreferredSize(new java.awt.Dimension(500, 100));

        javax.swing.GroupLayout jPanelCompraLayout = new javax.swing.GroupLayout(jPanelCompra);
        jPanelCompra.setLayout(jPanelCompraLayout);
        jPanelCompraLayout.setHorizontalGroup(
            jPanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCompraLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addComponent(jScrollPaneCompraFull, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanelCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelCompraLayout.setVerticalGroup(
            jPanelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanelProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneCompraFull, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addContainerGap())
        );

        // Adicionando Tabel para visualizar itens no COMPRA FULL
        tableModelFull = new DefaultTableModel(new Object[][] {}, new String[] {"COD", "Produto", "Descrição", "P.U.", "Desconto VIP", "Quantidade"}); // "Desenhando" organização da tabela com o tableModel
        tableFull = new JTable(tableModelFull); // Declarando a tabela com o estilo definido no tableModel
        jScrollPaneCompraFull.setViewportView(tableFull);

        jPanelCompraResumida.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCompraResumida.setPreferredSize(new java.awt.Dimension(203, 100));

        jPanelCompraResumidaClienteVip.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelCompraResumidaClienteVip.setOpaque(false);
        jPanelCompraResumidaClienteVip.setPreferredSize(new java.awt.Dimension(203, 62));

        labelCVipCompraResumida.setText("Cliente VIP");

        btnRemoverCVipCompraResumida.setBackground(new java.awt.Color(255, 0, 0));
        btnRemoverCVipCompraResumida.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        btnRemoverCVipCompraResumida.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoverCVipCompraResumida.setText("Remover");
        btnRemoverCVipCompraResumida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemoverCVipCompraResumida.setPreferredSize(new java.awt.Dimension(150, 24));

        javax.swing.GroupLayout jPanelCompraResumidaClienteVipLayout = new javax.swing.GroupLayout(jPanelCompraResumidaClienteVip);
        jPanelCompraResumidaClienteVip.setLayout(jPanelCompraResumidaClienteVipLayout);
        jPanelCompraResumidaClienteVipLayout.setHorizontalGroup(
            jPanelCompraResumidaClienteVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCompraResumidaClienteVipLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCompraResumidaClienteVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemoverCVipCompraResumida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelCompraResumidaClienteVipLayout.createSequentialGroup()
                        .addComponent(labelCVipCompraResumida)
                        .addGap(0, 120, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelCompraResumidaClienteVipLayout.setVerticalGroup(
            jPanelCompraResumidaClienteVipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCompraResumidaClienteVipLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCVipCompraResumida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemoverCVipCompraResumida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPaneCompraResumida.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPaneCompraResumida.setAutoscrolls(true);
        jScrollPaneCompraResumida.setMinimumSize(new java.awt.Dimension(203, 16));
        jScrollPaneCompraResumida.setPreferredSize(new java.awt.Dimension(100, 200));

        jPanelInfoCompraResumida.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnConcluirCompraResumida.setBackground(new java.awt.Color(0, 255, 0));
        btnConcluirCompraResumida.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        btnConcluirCompraResumida.setText("Concluir");
        btnConcluirCompraResumida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnCancelarCompraResumida.setBackground(new java.awt.Color(255, 0, 0));
        btnCancelarCompraResumida.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        btnCancelarCompraResumida.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarCompraResumida.setText("Cancelar");
        btnCancelarCompraResumida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        labelTotalCompraResumida.setText("Total:");

        jLabelPrecoCompraResumida.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPrecoCompraResumida.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabelPrecoCompraResumida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPrecoCompraResumida.setText("0.00");
        jLabelPrecoCompraResumida.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2));

        javax.swing.GroupLayout jPanelInfoCompraResumidaLayout = new javax.swing.GroupLayout(jPanelInfoCompraResumida);
        jPanelInfoCompraResumida.setLayout(jPanelInfoCompraResumidaLayout);
        jPanelInfoCompraResumidaLayout.setHorizontalGroup(
            jPanelInfoCompraResumidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoCompraResumidaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoCompraResumidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPrecoCompraResumida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInfoCompraResumidaLayout.createSequentialGroup()
                        .addComponent(btnCancelarCompraResumida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConcluirCompraResumida))
                    .addGroup(jPanelInfoCompraResumidaLayout.createSequentialGroup()
                        .addComponent(labelTotalCompraResumida)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelInfoCompraResumidaLayout.setVerticalGroup(
            jPanelInfoCompraResumidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInfoCompraResumidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTotalCompraResumida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPrecoCompraResumida, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoCompraResumidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConcluirCompraResumida)
                    .addComponent(btnCancelarCompraResumida))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelCompraResumidaLayout = new javax.swing.GroupLayout(jPanelCompraResumida);
        jPanelCompraResumida.setLayout(jPanelCompraResumidaLayout);
        jPanelCompraResumidaLayout.setHorizontalGroup(
            jPanelCompraResumidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCompraResumidaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCompraResumidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCompraResumidaClienteVip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(jPanelInfoCompraResumida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPaneCompraResumida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelCompraResumidaLayout.setVerticalGroup(
            jPanelCompraResumidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCompraResumidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCompraResumidaClienteVip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneCompraResumida, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelInfoCompraResumida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        // Adicionando Tabel para visualizar itens no COMPRA RESUMIDA
        tableModelResumida = new DefaultTableModel(new Object[][] {}, new String[] {"QTD", "Produto"}); // "Desenhando" organização da tabela com o tableModel
        tableResumida = new JTable(tableModelResumida); // Declarando a tabela com o estilo definido no tableModel
        jScrollPaneCompraResumida.setViewportView(tableResumida);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelCompraResumida, 217, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCompraResumida, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
        );

        
    }// </editor-fold>                        

    private void inputProdutoCodActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void inputProdutoQtdActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void btnMaisProdutoQtdActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAdicionarProduto;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarProduto;
    private javax.swing.JButton btnCancelarCompraResumida;
    private javax.swing.JButton btnConcluirCompraResumida;
    private javax.swing.JButton btnMaisProdutoQtd;
    private javax.swing.JButton btnMenosProdutoQtd;
    private javax.swing.JButton btnRemoverCVipCompraResumida;
    private javax.swing.JButton btnSelecionarCliente;
    private javax.swing.JTextField inputCpfCliente;
    private javax.swing.JTextField inputProdutoCod;
    private javax.swing.JTextField inputProdutoQtd;
    private javax.swing.JLabel jLabelPrecoCompraResumida;
    private javax.swing.JPanel jPanelCliente;
    private javax.swing.JPanel jPanelCompra;
    private javax.swing.JPanel jPanelCompraResumida;
    private javax.swing.JPanel jPanelCompraResumidaClienteVip;
    private javax.swing.JPanel jPanelInfoCompraResumida;
    private javax.swing.JPanel jPanelProduto;
    private javax.swing.JScrollPane jScrollPaneCompraFull;
    private javax.swing.JTable tableFull;
    private javax.swing.table.DefaultTableModel tableModelFull;
    private javax.swing.JTable tableResumida;
    private javax.swing.table.DefaultTableModel tableModelResumida;
    private javax.swing.JScrollPane jScrollPaneCompraResumida;
    private javax.swing.JLabel labelCVipCompraResumida;
    private javax.swing.JLabel labelClienteInfo;
    private javax.swing.JLabel labelClienteTitulo;
    private javax.swing.JLabel labelCpfCliente;
    private javax.swing.JLabel labelProdutoCod;
    private javax.swing.JLabel labelProdutoInfo;
    private javax.swing.JLabel labelProdutoQtd;
    private javax.swing.JLabel labelProdutoTitulo;
    private javax.swing.JLabel labelTotalCompraResumida;
    // Control
    private EstoquesControl estoqueControl;
    // Listas
    private List<Estoque> estoqueProdutos; // Lista TODOS Produtos
    private List<Estoque> produtosCompra; // Lista Produtos COMPRA
    // End of variables declaration

    //-----===| MÉTODOS |===-----//
    private void atualizarEstoque() {
        try {
            estoqueProdutos = new EstoquesDAO().readAll();
            Object linha[] = new Object[8];

            for (int i = 0; i < estoqueProdutos.size(); i++) {
                linha[0] = estoqueProdutos.get(i).getCodigoProduto();
                linha[1] = estoqueProdutos.get(i).getNomeProduto();
                linha[2] = estoqueProdutos.get(i).getDescricaoProduto();
                linha[3] = estoqueProdutos.get(i).getNomeFornecedor();
                linha[4] = estoqueProdutos.get(i).getPrecoProduto();
                linha[5] = estoqueProdutos.get(i).getQuantidadeProduto();
                linha[6] = estoqueProdutos.get(i).getDescontoVip();
                linha[7] = (estoqueProdutos.get(i).getStatusProduto()) ? "ativo" : "inativo";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void atualizarCompra(){
        tableModelFull.setRowCount(0);
        Object linha[] = new Object[8];

        for(int i = 0; i < produtosCompra.size(); i++){
            linha[0] = produtosCompra.get(i).getCodigoProduto();
            linha[1] = produtosCompra.get(i).getNomeProduto();
            linha[2] = produtosCompra.get(i).getDescricaoProduto();
            linha[3] = produtosCompra.get(i).getNomeFornecedor();
            linha[4] = produtosCompra.get(i).getPrecoProduto();
            linha[5] = produtosCompra.get(i).getQuantidadeProduto();
            linha[6] = produtosCompra.get(i).getDescontoVip();
            linha[7] = (produtosCompra.get(i).getStatusProduto()) ? "ativo" : "inativo";
            tableModelFull.addRow(linha);
        }
    }

}
