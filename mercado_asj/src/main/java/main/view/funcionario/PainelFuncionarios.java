package main.view.funcionario;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import main.control.funcionario.FuncionariosControl;
import main.control.funcionario.FuncionariosDAO;
import main.model.Funcionario;

public class PainelFuncionarios extends JPanel {
    //-----===| ATRIBUTOS |===-----//
    private JButton buttonCadastrar, buttonApagar, buttonEditar;
    private JLabel clienteInfo = new JLabel("Índice | CPF: | Nome: | Tipo: ");
    private JComboBox<String>modoExibicaoComboBox = new JComboBox<>();
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
        this.add(new JLabel("Funcionários"));
        
        // "Menu" dos botões
        JPanel botoes = new JPanel();
        
        // Modo de EXIBIÇÃO
        modoExibicaoComboBox.addItem("Ordenar por Nível de Acesso");
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
        buttonApagar = new JButton("Apagar");
        buttonApagar.setBackground(java.awt.Color.WHITE);
        botoes.add(buttonApagar);


        // Informação do cliente selecionado sendo exibida
        JPanel inputPanel = new JPanel();
        inputPanel.add(clienteInfo);

        // Adicionando paineis ao JPanel da janela
        this.add(botoes);
        this.add(inputPanel);

        // Tabela de Clientes
        JScrollPane scrollPane = new JScrollPane();
        this.add(scrollPane); // Adicionando scrollPane ao JPanel da janela
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] {"CPF", "Nome", "Telefone", "Rua", "Número", "CEP", "Senha", "Tipo"}); // "Desenhando" organização da tabela com o tableModel
        table = new JTable(tableModel); // Declarando a tabela com o estilo definido no tableModel
        // Definindo um renderizador de célula personalizado para a tabela
        table.setDefaultRenderer(Object.class, new ColoredCellRenderer());
        
        scrollPane.setViewportView(table);

        new FuncionariosDAO();

        atualizarTabela(String.valueOf(modoExibicaoComboBox.getSelectedItem()));

        //---=| Tratamento de Evento |=---//
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                linhaSelecionada = table.rowAtPoint(evt.getPoint()); // Pegando a linha selecionada (clicada)

                if(linhaSelecionada != -1){
                    // Variáveis temporárias
                    String cpf = String.valueOf(table.getValueAt(linhaSelecionada, 0));
                    String nome = String.valueOf(table.getValueAt(linhaSelecionada, 1));
                    String nivel = String.valueOf(table.getValueAt(linhaSelecionada, 7));
                    clienteInfo.setText("Índice "+ linhaSelecionada +" | CPF: "+ cpf +" | Nome: "+ nome +" | Tipo: "+ nivel);
                }
            }
        });
        buttonCadastrar.addActionListener(e ->{
            JanelaCadastroFuncionario janelaCadastrar = new JanelaCadastroFuncionario(this, funcionarios, tableModel, table);
            janelaCadastrar.setVisible(true);
            clienteInfo.setText("Índice | CPF: | Nome: | Tipo: ");
            atualizarTabela(String.valueOf(modoExibicaoComboBox.getSelectedItem()));
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
                String nivelAcesso = String.valueOf(table.getValueAt(linhaSelecionada, 7)).toLowerCase();

                // Evitar problemas
                if(telefone.equals("Não registrado")){
                    telefone = "";
                }
                if(rua.equals("Não registrado")){
                    rua = "";
                }
                if(numero.equals("Não registrado")){
                    numero = "";
                }
                if(cep.equals("Não registrado")){
                    cep = "";
                }

                JanelaEditaFuncionario janelaEdita = new JanelaEditaFuncionario(this, funcionarios, tableModel, table, linhaSelecionada, cpf, nome, telefone, rua, numero, cep, senha, nivelAcesso);
                janelaEdita.setVisible(true);
                // "Resetando"
                clienteInfo.setText("Índice | CPF: | Nome: | Tipo: ");
                atualizarTabela(String.valueOf(modoExibicaoComboBox.getSelectedItem()));
                linhaSelecionada = -1;
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
                JOptionPane.showMessageDialog(this, "É necessário selecionar algum funcionário!");
            } else{
                // Variável temporária
                String cpf = String.valueOf(table.getValueAt(linhaSelecionada, 0));

                FuncionariosControl funcionarioControl = new FuncionariosControl(funcionarios, tableModel, table);

                if(funcionarioControl.checkFuncionarioCampos(linhaSelecionada, "deletar", cpf, "temporario", "", "", "", "","temporario","temporario")){
                    // "Resetando"
                    clienteInfo.setText("Índice | CPF: | Nome: | Tipo: ");
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
            funcionarios = new FuncionariosDAO().readAll();
            Object linha[] = new Object[8];

            if(modelo.equals("Ordenar por Nível de Acesso")){
                for (int i = 0; i < funcionarios.size(); i++) {
                    if(funcionarios.get(i).getNivelAcessoFuncionario().equals("gerente")){

                        linha[0] = funcionarios.get(i).getCpfFuncionario();
                        linha[1] = funcionarios.get(i).getNomeFuncionario();
        
                        linha[2] = (funcionarios.get(i).getTelefoneFuncionario() == 0) ? "Não registrado" : funcionarios.get(i).getTelefoneFuncionario();
        
                        linha[3] = (funcionarios.get(i).getRuaFuncionario().equals("")) ? "Não registrado" : funcionarios.get(i).getRuaFuncionario();
        
                        linha[4] = (funcionarios.get(i).getNumeroFuncionario().equals("")) ? "Não registrado" : funcionarios.get(i).getNumeroFuncionario();
        
                        linha[5] = (funcionarios.get(i).getCepFuncionario() == 0) ? "Não registrado" : funcionarios.get(i).getCepFuncionario();
        
                        linha[6] = (funcionarios.get(i).getSenhaFuncionario());
                        linha[7] = "Gerente";
                        tableModel.addRow(linha);
                    }
                }

                for (int i = 0; i < funcionarios.size(); i++) {
                    if(funcionarios.get(i).getNivelAcessoFuncionario().equals("operador")){

                        linha[0] = funcionarios.get(i).getCpfFuncionario();
                        linha[1] = funcionarios.get(i).getNomeFuncionario();
        
                        linha[2] = (funcionarios.get(i).getTelefoneFuncionario() == 0) ? "Não registrado" : funcionarios.get(i).getTelefoneFuncionario();
        
                        linha[3] = (funcionarios.get(i).getRuaFuncionario().equals("")) ? "Não registrado" : funcionarios.get(i).getRuaFuncionario();
        
                        linha[4] = (funcionarios.get(i).getNumeroFuncionario().equals("")) ? "Não registrado" : funcionarios.get(i).getNumeroFuncionario();
        
                        linha[5] = (funcionarios.get(i).getCepFuncionario() == 0) ? "Não registrado" : funcionarios.get(i).getCepFuncionario();
        
                        linha[6] = (funcionarios.get(i).getSenhaFuncionario());
                        linha[7] = "Operador";
                        tableModel.addRow(linha);
                    }
                }
            } else {
                for (int i = 0; i < funcionarios.size(); i++) {
                    linha[0] = funcionarios.get(i).getCpfFuncionario();
                    linha[1] = funcionarios.get(i).getNomeFuncionario();
    
                    linha[2] = (funcionarios.get(i).getTelefoneFuncionario() == 0) ? "Não registrado" : funcionarios.get(i).getTelefoneFuncionario();
    
                    linha[3] = (funcionarios.get(i).getRuaFuncionario().equals("")) ? "Não registrado" : funcionarios.get(i).getRuaFuncionario();
    
                    linha[4] = (funcionarios.get(i).getNumeroFuncionario().equals("")) ? "Não registrado" : funcionarios.get(i).getNumeroFuncionario();
    
                    linha[5] = (funcionarios.get(i).getCepFuncionario() == 0) ? "Não registrado" : funcionarios.get(i).getCepFuncionario();
    
                    linha[6] = (funcionarios.get(i).getSenhaFuncionario());
                    linha[7] = (funcionarios.get(i).getNivelAcessoFuncionario().equals("operador")) ? "Operador" : "Gerente";
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

            // Obtendo o valor da célula de nivel de acesso
            String nivelAcesso = String.valueOf(table.getValueAt(row, 7));

            // Alterando a cor de fundo da linha com base no nivel de acesso
            if(nivelAcesso.equals("Operador")){
                cellComponent.setBackground(java.awt.Color.CYAN);
            } else {
                cellComponent.setBackground(java.awt.Color.GREEN);
            }

            return cellComponent;
        }
    }
}
