package main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.connection.ConnectionFactory;
import main.model.Venda;
import main.model.Venda;

public class VendasDAO {
    //-----===| ATRIBUTOS |===-----//
    private Connection connection; // Conexão
    private List<Venda> vendas; // Lista de Funcionários

    //-----===| CONSTRUTOR |===-----//
    public VendasDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    //-----===| MÉTODOS CRUD |===-----//
    //---=| READ ALL |=---//
    public List<Venda> readAll() throws SQLException{
        ResultSet resultSet = null; // Objeto que armazena
        vendas = new ArrayList<>();

        String query = "SELECT * FROM venda;"; // SQL Query
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try{
            resultSet = preparedStatement.executeQuery();

            // Loop para armazenar as informações do resultSet para a List<Venda>
            while(resultSet.next()){
                Venda venda = new Venda(resultSet.getInt("id_venda"), resultSet.getLong("cpf_cliente_venda"), resultSet.getString("codigo_produto_venda"), resultSet.getInt("quantidade_produto_venda"), resultSet.getDouble("preco_venda"), resultSet.getString("metodo_pagamento_venda"), resultSet.getDate("data_venda")); // Instanciando Venda com as informações obtidas pela query

                vendas.add(venda); // Adicionando objetos instanciados à lista
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar: "+ ex);
        } finally { // Independente se deu certo ou não, tem que fechar a conexão
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        }

        // Retornando lista (list<Venda>)
        return vendas;
    }

    //---=| CREATE |=---//
    public void create(Long cpfClienteVenda, String codigoProdutoVenda, Integer quantidadeProdutoVenda, Double precoVenda, String metodoPagamento, Date dataVenda) throws SQLException {
        String query = "INSERT INTO venda (cpf_cliente_venda, codigo_produto_venda, quantidade_produto_venda, preco_venda, metodo_pagamento_venda, data_venda) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setString(1, codigoProdutoVenda);
            preparedStatement.setString(2, codigoProdutoVenda);
            preparedStatement.setInt(3, quantidadeProdutoVenda);
            preparedStatement.setDouble(4, precoVenda);
            preparedStatement.setString(5, metodoPagamento);
            preparedStatement.setDate(6, dataVenda);
            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!");

            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar venda: " + ex);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(this.connection);
        }
    }
}