package main.control.estoque;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.model.Estoque;

public class EstoquesControl {
    //-----===| ATRIBUTOS |===-----//
    private EstoquesDAO estoquesDAO = new EstoquesDAO();
    private List<Estoque> estoques;
    private DefaultTableModel tableModel;
    private JTable table;
    
    //-----===| CONSTRUTOR |===-----//
    public EstoquesControl(EstoquesDAO estoquesDAO, List<Estoque> estoques, DefaultTableModel tableModel, JTable table) {
        this.estoquesDAO = estoquesDAO;
        this.estoques = estoques;
        this.tableModel = tableModel;
        this.table = table;
    }

    //-----===| MÉTODOS CRUD |===-----//
    // ---=| CREATE |=---//
    public void createEstoque(Short codigoProduto, String nomeProduto, String descricaoProduto, String nomeFornecedor, Double precoProduto, Integer quantidadeProduto, Double descontoVip, Boolean statusProduto) {
        try {
            estoquesDAO.create(codigoProduto, nomeProduto, descricaoProduto, nomeFornecedor, precoProduto, quantidadeProduto, descontoVip, statusProduto);

            Estoque estoque = new Estoque(codigoProduto, nomeProduto, descricaoProduto, nomeFornecedor, precoProduto, quantidadeProduto, descontoVip, statusProduto);
            estoques.add(estoque);

            atualizarTabela();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //---=| UPDATE |=---//
    public void updatePessoa(int linhaSelecionada, Short codigoProduto, String nomeProduto, String descricaoProduto, String nomeFornecedor, Double precoProduto, Integer quantidadeProduto, Double descontoVip, Boolean statusProduto) {
        if (linhaSelecionada != -1) {
            try {
                estoquesDAO.update(codigoProduto, nomeProduto, descricaoProduto, nomeFornecedor, precoProduto, quantidadeProduto, descontoVip, statusProduto);
                
                Estoque estoque = new Estoque(codigoProduto, nomeProduto, descricaoProduto, nomeFornecedor, precoProduto, quantidadeProduto, descontoVip, statusProduto);
                estoques.set(linhaSelecionada, estoque);
    
                atualizarTabela();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //---=| DELETE |=---//
    public void deleteEstoque(int linhaSelecionada, Short codigoProduto) {
        try {
            if(linhaSelecionada != 1){
                estoquesDAO.delete(codigoProduto);
                estoques.remove(linhaSelecionada);
                
                atualizarTabela();
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
}
