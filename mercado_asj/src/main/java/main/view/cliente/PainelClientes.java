package main.view.cliente;

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

import main.control.cliente.ClientesControl;
import main.control.cliente.ClientesDAO;
import main.model.Cliente;

public class PainelClientes extends JPanel {
    //-----===| ATRIBUTOS |===-----//
    private JButton buttonCadastrar, buttonApagar, buttonEditar;
    private JLabel clienteInfo = new JLabel("Índice | CPF: | Nome: ");
    private List<Cliente> clientes;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    private ClientesControl ClientesControl;

    //-----===| CONSTRUTOR |===-----//
    public PainelClientes() {
        super();

        // Entrada de dados
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Clientes"));
        
        // "Menu" dos botões
        JPanel botoes = new JPanel();
        botoes.add(buttonCadastrar = new JButton("Cadastrar"));
        botoes.add(buttonEditar = new JButton("Editar"));
        botoes.add(buttonApagar = new JButton("Apagar"));

        // Informação do cliente selecionado sendo exibida
        JPanel inputPanel = new JPanel();
        inputPanel.add(clienteInfo);

        // Adicionando paineis ao JPanel da janela
        this.add(botoes);
        this.add(inputPanel);

        // Tabela de Clientes
        JScrollPane scrollPane = new JScrollPane();
        this.add(scrollPane); // Adicionando scrollPane ao JPanel da janela
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] {"CPF", "Nome", "Telefone", "Rua", "Número", "CEP"}); // "Desenhando" organização da tabela com o tableModel
        table = new JTable(tableModel); // Declarando a tabela com o estilo definido no tableModel
        scrollPane.setViewportView(table);

        new ClientesDAO();

        atualizarTabela();

        //---=| Tratamento de Evento |=---//
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                linhaSelecionada = table.rowAtPoint(evt.getPoint()); // Pegando a linha selecionada (clicada)

                if(linhaSelecionada != -1){
                    // Variáveis temporárias
                    String cpf = String.valueOf(table.getValueAt(linhaSelecionada, 0));
                    String nome = String.valueOf(table.getValueAt(linhaSelecionada, 1));
                    clienteInfo.setText("Índice "+ linhaSelecionada +" | CPF: "+ cpf +" | Nome: "+ nome);
                }
            }
        });
        buttonCadastrar.addActionListener(e ->{
            JanelaCadastroCliente janelaCadastrar = new JanelaCadastroCliente(this, clientes, tableModel, table);
            janelaCadastrar.setVisible(true);
            clienteInfo.setText("Índice | CPF: | Nome: ");
            atualizarTabela();
        });
        buttonEditar.addActionListener(e ->{
            // Vou pegar a linha selecionada a partir do "ìndice" no cliente Info (usando regex)
            // Padrão Regex para pedar a linha selecionada
            Pattern pattern = Pattern.compile("Índice (-?\\d+)\\s*\\|");

            // Criando um Matcher que corresponde ao padrão na entrada
            Matcher matcher = pattern.matcher(clienteInfo.getText());
            if(matcher.find()){
                linhaSelecionada = Integer.valueOf(matcher.group(1));
            }
            
            if(linhaSelecionada == -1){
                JOptionPane.showMessageDialog(this, "É necessário selecionar algum cliente!");
            } else{
                // Variáveis temporárias
                String cpf = String.valueOf(table.getValueAt(linhaSelecionada, 0));
                String nome = String.valueOf(table.getValueAt(linhaSelecionada, 1));
                String telefone = String.valueOf(table.getValueAt(linhaSelecionada, 2));
                String rua = String.valueOf(table.getValueAt(linhaSelecionada, 3));
                String numero = String.valueOf(table.getValueAt(linhaSelecionada, 4));
                String cep = String.valueOf(table.getValueAt(linhaSelecionada, 5));

                JanelaEditaCliente janelaEdita = new JanelaEditaCliente(this, clientes, tableModel, table, linhaSelecionada, cpf, nome, telefone, rua, numero, cep);
                janelaEdita.setVisible(true);
                clienteInfo.setText("Índice | CPF: | Nome: ");
                atualizarTabela();
            }
        });
        buttonApagar.addActionListener(e ->{
            // Vou pegar a linha selecionada a partir do "ìndice" no cliente Info (usando regex)
            // Padrão Regex para pedar a linha selecionada
            Pattern pattern = Pattern.compile("Índice (-?\\d+)\\s*\\|");

            // Criando um Matcher que corresponde ao padrão na entrada
            Matcher matcher = pattern.matcher(clienteInfo.getText());
            if(matcher.find()){
                linhaSelecionada = Integer.valueOf(matcher.group(1));
            }
            
            if(linhaSelecionada == -1){
                JOptionPane.showMessageDialog(this, "É necessário selecionar algum cliente!");
            } else{
                // Variável temporária
                String cpf = String.valueOf(table.getValueAt(linhaSelecionada, 0));

                ClientesControl clientesControl = new ClientesControl(clientes, tableModel, table);

                if(clientesControl.checkClienteCampos(linhaSelecionada, "deletar", cpf, "", "", "", "", "")){
                    clienteInfo.setText("Índice | CPF: | Nome: ");
                    atualizarTabela();
                }
            }
        });
    }

    private void atualizarTabela() {
        try {
            tableModel.setRowCount(0);
            clientes = new ClientesDAO().readAll();
            Object linha[] = new Object[8];

            for (int i = 0; i < clientes.size(); i++) {
                linha[0] = clientes.get(i).getCpfCliente();
                linha[1] = clientes.get(i).getNomeCliente();
                linha[2] = (clientes.get(i).getTelefoneCliente() == 0) ? "" : clientes.get(i).getTelefoneCliente();
                linha[3] = clientes.get(i).getRuaCliente();
                linha[4] = clientes.get(i).getNumeroCliente();
                linha[5] = (clientes.get(i).getCepCliente() == 0) ? "" : clientes.get(i).getCepCliente();
                tableModel.addRow(linha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
