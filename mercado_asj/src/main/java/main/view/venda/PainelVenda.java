package main.view.venda;

import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.model.Estoque;

public class PainelVenda extends JPanel {
    //-----===| ATRIBUTOS |===-----//
    // Criando componentes
    // Escolha de cliente
    private JPanel jPanelClienteCadastrado;
    private JButton buttonClienteCadastrado = new JButton("Cliente Cadastrado");
    private JLabel labelClienteCadastradoInfo = new JLabel("CPF: | Nome:"); // Vai ser usado em outro painel também (painel do resumo pré venda)

    // Barra direita (resumo da venda)
    private JPanel jPanelResumoPreVenda;
    private List<String> produtosResumoPreVenda = new ArrayList<>();
    private JTable tableResumoPreVenda;
    private DefaultTableModel tableModelResumoPreVenda;
    private JScrollPane scrollPaneResumoPreVenda = new JScrollPane();
    private JButton buttonLimparResumoPreVenda = new JButton("Limpar Venda"); // Limpar TUDO
    private JButton buttonConcluirResumoPreVenda = new JButton("Concluir Venda");

    // Botões de controle de produto + info do selecionado
    private JPanel jPanelControleProdutos;
    private JButton buttonAdicionarProduto = new JButton("Adicionar Produto");
    private JButton buttonEditarProduto = new JButton("Editar Produto");
    private JButton buttonRemoverProduto = new JButton("Remover Produto");
    private JLabel labelInfoControleProdutos = new JLabel("COD: | Nome: | QTD: | Preço U. | Desconto VIP: ");

    // Lista "detalhada" da venda item a item
    private JPanel jPanelListaDetalhada;
    private List<Estoque> produtosListaDetalhada = new ArrayList<>();
    private JTable tableListaDetalhada;
    private DefaultTableModel tableModelListaDetalhada;
    private int linhaSelecionadaListaDetalhada = -1;

    // ArrayList dos componentes a serem exibidos ("Global")
    ArrayList<JComponent> componentesGlobal;
    // ArrayList dos componentes a serem exibidos (ClienteCadastrado)
    ArrayList<JComponent> componentesClienteCadastrado;
    // ArrayList dos componentes a serem exibidos (ResumoPreVenda)
    ArrayList<JComponent> componentesResumoPreVenda;
    // ArrayList dos componentes a serem exibidos (ControleProdutos)
    ArrayList<JComponent> componentesControleProdutos;
    // ArrayLista dos componentes a serem exibidos (Lista Detalhada)
    ArrayList<JComponent> componentesListaDetalhada;

    Insets insets; // Chamando insets para estilização do GridBaglayout


    //-----===| CONSTRUTOR |===-----//
    public PainelVenda() {
        super();

        // Criando a tela
        JPanel painelVenda = criarPainelVenda();

        // Adicionando tela ao JFrame
        this.add(painelVenda);
    }

    //-----===| MÉTODOS |===-----//
    // Gerar Painel Venda
    private JPanel criarPainelVenda() {
        JPanel telaPainelVenda = new JPanel();
        telaPainelVenda.setLayout(new GridBagLayout());
        GridBagConstraints elemento = new GridBagConstraints();

        jPanelClienteCadastrado = criarClienteCadastrado();
        jPanelResumoPreVenda = criarResumoPreVenda();
        jPanelControleProdutos = criarControleProdutos();
        jPanelListaDetalhada = criarListaDetalhada();

        componentesGlobal = new ArrayList<>(List.of(
                jPanelClienteCadastrado,
                jPanelResumoPreVenda,
                jPanelControleProdutos,
                jPanelListaDetalhada
        ));

        int[] posicaoNoArrayGlobal = {0, 1, 2, 3};
        int[][] posicaoComponentesGlobal = {
                {0, 0, 2, 1, 1, 1, 0, 0, 0, 0},
                {2, 0, 1, 3, 1, 1, 0, 0, 0, 0},
                {0, 1, 2, 1, 1, 1, 0, 0, 0, 0},
                {2, 1, 2, 2, 1, 1, 0, 0, 0, 0}
        };

        for (int i = 0; i < 4; i++) {
            elemento.gridx = posicaoComponentesGlobal[i][0];
            elemento.gridy = posicaoComponentesGlobal[i][1];
            elemento.gridwidth = posicaoComponentesGlobal[i][2];
            elemento.gridheight = posicaoComponentesGlobal[i][3];
            elemento.weightx = posicaoComponentesGlobal[i][4];
            elemento.weighty = posicaoComponentesGlobal[i][5];
            elemento.insets = new Insets(posicaoComponentesGlobal[i][6], posicaoComponentesGlobal[i][7],
                    posicaoComponentesGlobal[i][8], posicaoComponentesGlobal[i][9]);

            telaPainelVenda.add(componentesGlobal.get(posicaoNoArrayGlobal[i]), elemento);
        }

        return telaPainelVenda;
    }

    // Gerar painel Cliente Cadastrado
    private JPanel criarClienteCadastrado(){
        JPanel painelClienteCadastrado = new JPanel();
        // Setando layout
        painelClienteCadastrado.setLayout(new GridBagLayout());
        // Configurando Painel
        GridBagConstraints elemento = new GridBagConstraints(); // Variável de controle de exibição de cada elemento

        // ArrayList dos componentes a serem exibidos (ClienteCadastrado)
        componentesClienteCadastrado = new ArrayList<JComponent>(){
            {
                add(buttonClienteCadastrado); // Botão p/ abrir janela de escolha de cliente cadastrado
                add(labelClienteCadastradoInfo); // Label com info do cliente cadastrado selecionado
            }
        };
        // Declarando os itens a serem utilizados (sua posição no ArrayList)
        int[] posicaoNoArrayClienteCadastrado = {
            0, // Botão p/ abrir "menu" de escolha de Cliente Cadastrado
            1 // Label de informações do Cliente Cadastrado
        };
        // Declarando os valores de cada Item
        int[][] posicaoComponentesClienteCadastrado = {
            { 0, 0, 1, 1, 1, 1, 0, 0, 0, 0 }, // Botão Cliente Cadastrado
            { 0, 1, 3, 1, 1, 1, 0, 0, 0, 0 }, // Label Info Cliente Cadastrado
        };

        // Configurand modelo de exibição
        for (int i = 0; i < 2; i++) { // Menor que 2 pois 2 é a quantidade de item da janela
            elemento.gridx = posicaoComponentesClienteCadastrado[i][0];
            elemento.gridy = posicaoComponentesClienteCadastrado[i][1];
            elemento.gridwidth = posicaoComponentesClienteCadastrado[i][2];
            elemento.gridheight = posicaoComponentesClienteCadastrado[i][3];
            elemento.weightx = posicaoComponentesClienteCadastrado[i][4];
            elemento.weighty = posicaoComponentesClienteCadastrado[i][5];
            elemento.insets = new Insets(posicaoComponentesClienteCadastrado[i][6], posicaoComponentesClienteCadastrado[i][7], posicaoComponentesClienteCadastrado[i][8], posicaoComponentesClienteCadastrado[i][9]);

            painelClienteCadastrado.add(componentesClienteCadastrado.get(posicaoNoArrayClienteCadastrado[i]), elemento);
        }

        return painelClienteCadastrado;
    }

    // Gerar painel ResumoPreVenda
    private JPanel criarResumoPreVenda(){
        JPanel painelResumoPreVenda = new JPanel();
        // Setando layout
        painelResumoPreVenda.setLayout(new GridBagLayout());
        // Configurando Painel
        GridBagConstraints elemento = new GridBagConstraints(); // Variável de controle de exibição de cada elemento

        tableModelResumoPreVenda = new DefaultTableModel(new Object[][] {}, new String[] {"Produtos"});
        tableResumoPreVenda = new JTable(tableModelResumoPreVenda);

        // ArrayList dos componentes a serem exibidos (ResumoPreVenda)
        componentesResumoPreVenda = new ArrayList<JComponent>(){
            {
                add(labelClienteCadastradoInfo); // Pegando label do ClientesCadastrado pra reutilizar
                add(tableResumoPreVenda); // Tabela que exibirá os produtos (resumidamente)
                add(buttonLimparResumoPreVenda); // Botão que "cancela" venda
                add(buttonConcluirResumoPreVenda); // Botão p/ ir pra etapa final da venda
            }
        };
        // Declarando os itens a serem utilizados (sua posição no ArrayList)
        int[] posicaoNoArrayResumoPreVenda = {
            0, // Info do Cliente Cadastrado selecionado
            1, // Tabela que resume os produtos da compra
            2, 3 // Botões Limpar Venda | Concluir Venda
        };
        // Declarando os valores de cada Item
        int[][] posicaoComponentesResumoPreVenda = {
            { 0, 0, 2, 1, 1, 1, 0, 0, 0, 0 }, // Label info Cliente Cadastrado

            { 0, 1, 2, 4, 1, 1, 0, 0, 0, 0 }, // Tabela resumida

            { 0, 5, 1, 1, 1, 1, 0, 0, 0, 0 }, // Botão Limpar
            { 1, 5, 1, 1, 1, 1, 0, 0, 0, 0 } // Botão Concluir
        };

        // Configurand modelo de exibição
        for (int i = 0; i < 4; i++) { // Menor que 4 pois 4 é a quantidade de item da janela
            elemento.gridx = posicaoComponentesResumoPreVenda[i][0];
            elemento.gridy = posicaoComponentesResumoPreVenda[i][1];
            elemento.gridwidth = posicaoComponentesResumoPreVenda[i][2];
            elemento.gridheight = posicaoComponentesResumoPreVenda[i][3];
            elemento.weightx = posicaoComponentesResumoPreVenda[i][4];
            elemento.weighty = posicaoComponentesResumoPreVenda[i][5];
            elemento.insets = new Insets(posicaoComponentesResumoPreVenda[i][6], posicaoComponentesResumoPreVenda[i][7], posicaoComponentesResumoPreVenda[i][8], posicaoComponentesResumoPreVenda[i][9]);

            painelResumoPreVenda.add(componentesResumoPreVenda.get(posicaoNoArrayResumoPreVenda[i]), elemento);
        }

        return painelResumoPreVenda;
    }

    // Gerar painel Controle de Produto
    private JPanel criarControleProdutos() {
        JPanel painelControleProdutos = new JPanel();
        painelControleProdutos.setLayout(new GridBagLayout());
        GridBagConstraints elemento = new GridBagConstraints();

        componentesControleProdutos = new ArrayList<>(List.of(
                buttonAdicionarProduto,
                buttonEditarProduto,
                buttonRemoverProduto,
                labelInfoControleProdutos
        ));

        int[] posicaoNoArrayControleProdutos = {0, 1, 2, 3};
        int[][] posicaoComponentesControleProdutos = {
                {0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {2, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 1, 3, 1, 1, 1, 0, 0, 0, 0}
        };

        for (int i = 0; i < 4; i++) {
            elemento.gridx = posicaoComponentesControleProdutos[i][0];
            elemento.gridy = posicaoComponentesControleProdutos[i][1];
            elemento.gridwidth = posicaoComponentesControleProdutos[i][2];
            elemento.gridheight = posicaoComponentesControleProdutos[i][3];
            elemento.weightx = posicaoComponentesControleProdutos[i][4];
            elemento.weighty = posicaoComponentesControleProdutos[i][5];
            elemento.insets = new Insets(posicaoComponentesControleProdutos[i][6],
                    posicaoComponentesControleProdutos[i][7], posicaoComponentesControleProdutos[i][8],
                    posicaoComponentesControleProdutos[i][9]);

            painelControleProdutos.add(componentesControleProdutos.get(posicaoNoArrayControleProdutos[i]), elemento);
        }

        return painelControleProdutos;
    }

    // Gerar painel Lista Detalhada
    private JPanel criarListaDetalhada(){
        JPanel painelListaDetalhada = new JPanel();
        // Setando layout
        painelListaDetalhada.setLayout(new GridBagLayout());
        // Configurando Painel
        GridBagConstraints elemento = new GridBagConstraints(); // Variável de controle de exibição de cada elemento

        tableModelListaDetalhada = new DefaultTableModel(new Object[][] {}, new String[] {"COD", "Produto", "QTD", "Preço U", "Preço T", "Preco VIP"});
        tableListaDetalhada = new JTable(tableModelListaDetalhada);

        // ArrayLista dos componentes a serem exibidos (Lista Detalhada)
        componentesListaDetalhada = new ArrayList<JComponent>(){
            {
                add(tableListaDetalhada); // Tabela detalhada dos produtos
            }
        };
        // Declarando os itens a serem utilizados (sua posição no ArrayList)
        int[] posicaoNoArrayListaDetalhada = {
            0 // Tabela
        };
        // Declarando os valores de cada Item
        int[][] posicaoComponentesListaDetalhada = {
            { 0, 0, 1, 1, 1, 1, 0, 0, 0, 0 }, // Tabela
        };

        // Configurand modelo de exibição
        for (int i = 0; i < 1; i++) { // Menor que 1 pois 1 é a quantidade de item da janela
            elemento.gridx = posicaoComponentesListaDetalhada[i][0];
            elemento.gridy = posicaoComponentesListaDetalhada[i][1];
            elemento.gridwidth = posicaoComponentesListaDetalhada[i][2];
            elemento.gridheight = posicaoComponentesListaDetalhada[i][3];
            elemento.weightx = posicaoComponentesListaDetalhada[i][4];
            elemento.weighty = posicaoComponentesListaDetalhada[i][5];
            elemento.insets = new Insets(posicaoComponentesListaDetalhada[i][6], posicaoComponentesListaDetalhada[i][7], posicaoComponentesListaDetalhada[i][8], posicaoComponentesListaDetalhada[i][9]);

            painelListaDetalhada.add(componentesListaDetalhada.get(posicaoNoArrayListaDetalhada[i]), elemento);
        }

        return painelListaDetalhada;
    }
}
