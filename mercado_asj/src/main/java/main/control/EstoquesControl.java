package main.control;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.dao.EstoquesDAO;
import main.model.Estoque;
import main.view.venda.PainelVenda;

public class EstoquesControl {
    //-----===| ATRIBUTOS |===-----//
    private EstoquesDAO estoquesDAO = new EstoquesDAO();
    private List<Estoque> estoques;
    private DefaultTableModel tableModel;
    private JTable table;
    
    //-----===| CONSTRUTOR |===-----//
    public EstoquesControl(List<Estoque> estoques, DefaultTableModel tableModel, JTable table) {
        this.estoques = estoques;
        this.tableModel = tableModel;
        this.table = table;
    }

    //-----===| MÉTODOS CRUD |===-----//
    // ---=| CREATE |=---//
    public void createEstoque(String codigoProduto, String nomeProduto, String descricaoProduto, String nomeFornecedor, Double precoProduto, Integer quantidadeProduto, Double descontoVip, Boolean statusProduto) {
        try {
            estoquesDAO.create(codigoProduto, nomeProduto, descricaoProduto, nomeFornecedor, precoProduto, quantidadeProduto, descontoVip, statusProduto);

            Estoque estoque = new Estoque(codigoProduto, nomeProduto, descricaoProduto, nomeFornecedor, precoProduto, quantidadeProduto, descontoVip, statusProduto);
            estoques.add(estoque);

            atualizarTabela();

            // Atualizar o "estoque temporario"
            PainelVenda painelVenda = new PainelVenda();
            painelVenda.atualizarEstoque();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //---=| READ |=---//
    public Estoque readEstoque(String codigoProduto) throws SQLException{
        try {
            return estoquesDAO.read(codigoProduto);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //---=| UPDATE |=---//
    public void updateEstoque(int linhaSelecionada, String codigoProduto, String nomeProduto, String descricaoProduto, String nomeFornecedor, Double precoProduto, Integer quantidadeProduto, Double descontoVip, Boolean statusProduto) {
        if (linhaSelecionada != -1) {
            try {
                estoquesDAO.update(codigoProduto, nomeProduto, descricaoProduto, nomeFornecedor, precoProduto, quantidadeProduto, descontoVip, statusProduto);
                
                Estoque estoque = new Estoque(codigoProduto, nomeProduto, descricaoProduto, nomeFornecedor, precoProduto, quantidadeProduto, descontoVip, statusProduto);
                estoques.set(linhaSelecionada, estoque);
    
                atualizarTabela();

                // Atualizar o "estoque temporario"
                PainelVenda painelVenda = new PainelVenda();
                painelVenda.atualizarEstoque();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void updateEstoque(String codigoProduto, Integer quantidadeProduto){
        try {
            estoquesDAO.updateEmVenda(codigoProduto, quantidadeProduto);

            atualizarTabela();

            // Atualizar o "estoque temporario"
            PainelVenda painelVenda = new PainelVenda();
            painelVenda.atualizarEstoque();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //---=| DELETE |=---//
    public void inativarEstoque(int linhaSelecionada, String codigoProduto) {
        try {
            if(linhaSelecionada != 1){
                estoquesDAO.inativar(codigoProduto);
                estoques.remove(linhaSelecionada);
                
                atualizarTabela();

                // Atualizar o "estoque temporario"
                PainelVenda painelVenda = new PainelVenda();
                painelVenda.atualizarEstoque();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //---=| Atualizar Tabela |=---//
    private void atualizarTabela() {
        tableModel.setRowCount(0);
        Object linha[] = new Object[8];
        for (int i = 0; i < estoques.size(); i++) {
            linha[0] = estoques.get(i).getCodigoProduto();
            linha[1] = estoques.get(i).getNomeProduto();
            linha[2] = estoques.get(i).getDescricaoProduto();
            linha[3] = estoques.get(i).getNomeFornecedor();
            linha[4] = estoques.get(i).getPrecoProduto();
            linha[5] = estoques.get(i).getQuantidadeProduto();
            linha[6] = estoques.get(i).getDescontoVip();
            linha[7] = estoques.get(i).getStatusProduto();
        }
    }

    //---=| CHECAGEM DE CAMPO |=---//
    public boolean checkEstoqueCampos(int linhaSelecionada, String operacao, String codigo, String nomeProduto, String descricao, String nomeFornecedor, String preco, String quantidade, String descontoVip, Boolean status){
        // Verifica se os campos estão preenchidos
        if (codigo.isEmpty() || nomeProduto.isEmpty() || descricao.isEmpty() || nomeFornecedor.isEmpty() || preco.isEmpty() || quantidade.isEmpty() || descontoVip.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ATENÇÃO!\nCampos obrigatórios estão em branco");
            return false;
        }
        if(!validarFormatoCodigo(codigo)){
            JOptionPane.showMessageDialog(null, "Código inválido!\nO Código do Produto deve conter apenas números e ter 3 dígitos!");
            return false;
        }
        if(!validarFormatoPreco(preco)){
            JOptionPane.showMessageDialog(null, "Preço inválido!\nO Preço deve conter até no máximo 2 casas decimais e deve ser positivo!");
            return false;
        }
        if(!validarFormatoQuantidade(quantidade)){
            JOptionPane.showMessageDialog(null, "Quantidade inválidoa\nA quantidade deve apenas números positivos!");
            return false;
        }
        if(!validarFormatoDescontoVip(descontoVip)){
            JOptionPane.showMessageDialog(null, "Desconto VIP inválido!\nO Desconto VIP deve conter apenas números positivos e ter até 3 casas decimais!");
            return false;
        }

        if(operacao.equals("cadastrar")){
            int resposta = JOptionPane.showConfirmDialog(null,"Realizar cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                // Executa a operação de cadastrar
                createEstoque(String.valueOf(codigo.trim()), nomeProduto.trim(), descricao.trim(), nomeFornecedor.trim(), Double.valueOf(preco.trim()), Integer.valueOf(quantidade.trim()), Double.valueOf(descontoVip.trim()), status);
            }
        }
        else if(operacao.equals("atualizar")){
            int resposta = JOptionPane.showConfirmDialog(null,"Realizar edição?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                // Executa a operação de editar
                updateEstoque(linhaSelecionada, String.valueOf(codigo.trim()), nomeProduto.trim(), descricao.trim(), nomeFornecedor.trim(), Double.valueOf(preco.trim()), Integer.valueOf(quantidade.trim()), Double.valueOf(descontoVip.trim()), status);
            }
        }
        else if(operacao.equals("venda")){
            // Operação de remover uma X quantia do estoque
            updateEstoque(codigo.trim(), Integer.valueOf(quantidade));
        }
        else {
            int resposta = JOptionPane.showConfirmDialog(null,"Realizar inativação?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                // Executa a opção de deletar
                inativarEstoque(linhaSelecionada, String.valueOf(codigo.trim()));
            }
        }
        return true;
    }

    // Método para validar o formato do CPF
    private boolean validarFormatoCodigo(String codigo) {
        // Verificar se a string tem exatamente 3 dígitos e é um número inteiro
        return codigo.matches("\\d{3}");
    }
    // Método para validar o formato do preço
    private boolean validarFormatoPreco(String preco) {
        // Expressão regular para validar o formato do preço
        String regex = "^[0-9]+(\\.[0-9]+)?$";
        // Remove espaços em branco antes e depois do preço
        preco = preco.trim();

        // Verifica se o preço corresponde à expressão regular
        return preco.matches(regex);
    }
    // Método para validar o formato da entrada (máximo 10 caracteres, permitindo números e até 1 letra)
    private boolean validarFormatoQuantidade(String quantidade) {
        // Verificar se a string tem exatamente 3 dígitos e é um número inteiro
    return quantidade.matches("\\d+");
    }
    // Método para validar a quantidade de caracteres
    private boolean validarFormatoDescontoVip(String valor) {
        // Remover caracteres que não são dígitos ou ponto
        valor = valor.replaceAll("[^0-9.]", "");
        // Remove espaços em branco antes e depois
        valor.trim();
    
        try {
            // Converter a string para um número de ponto flutuante
            double numero = Double.parseDouble(valor);
    
            // Verificar se é um número positivo e tem no máximo três casas decimais
            return numero >= 0 && String.valueOf(numero).matches("\\d+(\\.\\d{1,3})?");
        } catch (NumberFormatException e) {
            // Se a conversão falhar, não é um número válido
            return false;
        }
    }
}
