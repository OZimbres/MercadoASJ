package main.view.funcionario;

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
import main.control.funcionario.FuncionariosControl;
import main.control.funcionario.FuncionariosDAO;
import main.model.Funcionario;

public class PainelFuncionarios extends JPanel {
    //-----===| ATRIBUTOS |===-----//
    private JButton buttonCadastrar, buttonApagar, buttonEditar;
    private JLabel clienteInfo = new JLabel("Índice | CPF: | Nome: ");
    private List<Funcionario> funcionarios;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    private FuncionariosControl funcionarioControl;

    //-----===| CONSTRUTOR |===-----//
    public PainelFuncionarios() {
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

        new FuncionariosDAO();

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
            JanelaCadastroFuncionario janelaCadastrar = new JanelaCadastroFuncionario(this, funcionarios, tableModel, table);
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
                String senha = String.valueOf(table.getValueAt(linhaSelecionada, 6));
                String nivelAcesso = String.valueOf(table.getValueAt(linhaSelecionada, 7));

                JanelaEditaFuncionario janelaEdita = new JanelaEditaFuncionario(this, funcionarios, tableModel, table, linhaSelecionada, cpf, nome, telefone, rua, numero, cep, senha, nivelAcesso);
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

                FuncionariosControl funcionarioControl = new FuncionariosControl(funcionarios, tableModel, table);

                if(funcionarioControl.checkFuncionarioCampos(linhaSelecionada, "deletar", cpf, "temporario", "", "", "", "","","")){
                    clienteInfo.setText("Índice | CPF: | Nome: ");
                    atualizarTabela();
                }
            }
        });
    }

    private void atualizarTabela() {
        try {
            tableModel.setRowCount(0);
            funcionarios = new FuncionariosDAO().readAll();
            Object linha[] = new Object[8];

            for (int i = 0; i < funcionarios.size(); i++) {
                linha[0] = funcionarios.get(i).getCpfFuncionario();
                linha[1] = funcionarios.get(i).getNomeFuncionario();
                linha[2] = (funcionarios.get(i).getTelefoneFuncionario() == 0) ? "" : funcionarios.get(i).getTelefoneFuncionario();
                linha[3] = funcionarios.get(i).getRuaFuncionario();
                linha[4] = funcionarios.get(i).getNumeroFuncionario();
                linha[5] = (funcionarios.get(i).getCepFuncionario() == 0) ? "" : funcionarios.get(i).getCepFuncionario();
                linha[6] = (funcionarios.get(i).getSenhaFuncionario());
                linha[7] = (funcionarios.get(i).getNivelAcessoFuncionario());
                tableModel.addRow(linha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
