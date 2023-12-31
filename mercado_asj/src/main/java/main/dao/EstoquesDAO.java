package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.connection.ConnectionFactory;
import main.model.Estoque;

public class EstoquesDAO {
    //-----===| ATRIBUTOS |===-----//
    private Connection connection; // Conexão
    private List<Estoque> estoques; // Lista de Funcionários

    //-----===| CONSTRUTOR |===-----//
    public EstoquesDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    //-----===| MÉTODOS CRUD |===-----//
    //---=| READ ALL |=---//
    public List<Estoque> readAll() throws SQLException{
        ResultSet resultSet = null; // Objeto que armazena
        estoques = new ArrayList<>();

        String query = "SELECT * FROM estoque;"; // SQL Query
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try{
            resultSet = preparedStatement.executeQuery();

            // Loop para armazenar as informações do resultSet para a List<Estoque>
            while(resultSet.next()){
                Estoque estoque = new Estoque(resultSet.getString("codigo_produto"), resultSet.getString("nome_produto"), resultSet.getString("descricao_produto"), resultSet.getString("nome_fornecedor"), resultSet.getDouble("preco_produto"), resultSet.getInt("quantidade_produto"), resultSet.getDouble("desconto_vip_produto"), resultSet.getBoolean("status_produto")); // Instanciando Estoque com as informações obtidas pela query

                estoques.add(estoque); // Adicionando objetos instanciados à lista
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar: "+ ex);
        } finally { // Independente se deu certo ou não, tem que fechar a conexão
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        }

        // Retornando lista (list<Estoque>)
        return estoques;
    }

    //---=| READ |=---//
    public Estoque read(String codigoProduto) throws SQLException {
        ResultSet resultSet = null; // Objeto que armazena
        Estoque estoque;

        // Por ser uma consulta realizada frequentemente, vai abrir toda vez
        connection = ConnectionFactory.getConnection();

        String query = "SELECT * FROM estoque WHERE codigo_produto = ?;"; // SQL Query
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setString(1, codigoProduto);

            resultSet = preparedStatement.executeQuery();
            // Loop para armazenar as informações do resultSet para a List<Estoque>

            if(resultSet.next()){
                estoque = new Estoque(resultSet.getString("codigo_produto"), resultSet.getString("nome_produto"), resultSet.getString("descricao_produto"), resultSet.getString("nome_fornecedor"), resultSet.getDouble("preco_produto"), resultSet.getInt("quantidade_produto"), resultSet.getDouble("desconto_vip_produto"), resultSet.getBoolean("status_produto")); // Instanciando Estoque com as informações obtidas pela query

                return estoque;
            }
            else{
                JOptionPane.showMessageDialog(null, "Produto não encontrado");
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro consultar: " + ex);
            return null;
        }
        finally { // Independente se deu certo ou não, tem que fechar a conexão
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        }
    }

    //---=| CREATE |=---//
    public void create(String codigoProduto, String nomeProduto, String descricaoProduto, String nomeFornecedor, Double precoProduto, Integer quantidadeProduto, Double descontoVip, Boolean statusProduto) throws SQLException {
        String query = "INSERT INTO estoque (codigo_produto, nome_produto, descricao_produto, nome_fornecedor, preco_produto, quantidade_produto, desconto_vip_produto, status_produto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setString(1, codigoProduto);
            preparedStatement.setString(2, nomeProduto);
            preparedStatement.setString(3, descricaoProduto);
            preparedStatement.setString(4, nomeFornecedor);
            preparedStatement.setDouble(5, precoProduto);
            preparedStatement.setDouble(6, quantidadeProduto);
            preparedStatement.setDouble(7, descontoVip);
            preparedStatement.setBoolean(8, statusProduto);
            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Produto incluído ao estoque com sucesso!");

            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + ex);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    //---=| UPDATE |=---//
    public void update(String codigoProduto, String nomeProduto, String descricaoProduto, String nomeFornecedor, Double precoProduto, Integer quantidadeProduto, Double descontoVip, Boolean statusProduto) throws SQLException {
        String query = "UPDATE estoque SET nome_produto = ?, descricao_produto = ?, nome_fornecedor = ?, preco_produto = ?, quantidade_produto = ?, desconto_vip_produto = ?, status_produto = ? WHERE codigo_produto = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setString(1, nomeProduto);
            preparedStatement.setString(2, descricaoProduto);
            preparedStatement.setString(3, nomeFornecedor);
            preparedStatement.setDouble(4, precoProduto);
            preparedStatement.setInt(5, quantidadeProduto);
            preparedStatement.setDouble(6, descontoVip);
            preparedStatement.setBoolean(7, statusProduto);
            preparedStatement.setString(8, codigoProduto);
            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public void updateEmVenda(String codigoProduto, Integer quantidadeProduto) throws SQLException{
        String query = "UPDATE estoque SET quantidade_produto = ? WHERE codigo_produto = ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try{
            preparedStatement.setInt(1, quantidadeProduto);
            preparedStatement.setString(2, codigoProduto);
            preparedStatement.execute();

            System.out.println("Produto debitado do estoque com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao debitar do banco: " + ex);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    //---=| DELETE |=---//
    public void delete(String codigoProduto) throws SQLException {
        String query = "DELETE FROM estoque WHERE codigo_produto = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setString(1, codigoProduto);
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");

            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        }
    }
    //---=| UPDATE ("DELETE") |=---//
    public void inativar(String codigoProduto) throws SQLException {
        String query = "UPDATE estoque SET status_produto = FALSE WHERE codigo_produto = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setString(1, codigoProduto);
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Inativado com sucesso!");

            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inativar: " + ex);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        }
    }
}
