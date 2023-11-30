package main.control.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.connection.ConnectionFactory;
import main.model.Cliente;

public class ClientesDAO {
    //-----===| ATRIBUTOS |===-----//
    private Connection connection; // Conexão
    private List<Cliente> clientes; // Lista de Funcionários

    //-----===| CONSTRUTOR |===-----//
    public ClientesDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    //-----===| MÉTODOS CRUD |===-----//
    //---=| READ ALL |=---//
    public List<Cliente> readAll() throws SQLException{
        ResultSet resultSet = null; // Objeto que armazena
        clientes = new ArrayList<>();

        String query = "SELECT * FROM clientes;"; // SQL Query
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try{
            resultSet = preparedStatement.executeQuery();

            // Loop para armazenar as informações do resultSet para a List<Cliente>
            while(resultSet.next()){
                Cliente cliente = new Cliente(resultSet.getLong("cpf_cliente"), resultSet.getString("nome_cliente"), resultSet.getLong("telefone_cliente"), resultSet.getString("rua_cliente"), resultSet.getString("numero_rua_cliente"), resultSet.getInt("cep_cliente")); // Instanciando Cliente com as informações obtidas pela query

                clientes.add(cliente); // Adicionando objetos instanciados à lista
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar: "+ ex);
        } finally { // Independente se deu certo ou não, tem que fechar a conexão
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        }

        // Retornando lista (list<Cliente>)
        return clientes;
    }

    //---=| READ |=---//
    public Cliente read(Long cpfCliente) throws SQLException {
        ResultSet resultSet = null; // Objeto que armazena
        Cliente cliente;

        String query = "SELECT * FROM clientes WHERE cpf_cliente = ?;"; // SQL Query
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setLong(1, cpfCliente);

            resultSet = preparedStatement.executeQuery();
            // Loop para armazenar as informações do resultSet para a List<Cliente>

            if(resultSet.next()){
                cliente = new Cliente(resultSet.getLong("cpf_cliente"), resultSet.getString("nome_cliente"), resultSet.getLong("telefone_cliente"), resultSet.getString("rua_cliente"), resultSet.getString("numero_rua_cliente"), resultSet.getInt("cep_cliente")); // Instanciando Cliente com as informações obtidas pela query

                return cliente;
            }
            else{
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar: " + ex);
            return null;
        }
        finally { // Independente se deu certo ou não, tem que fechar a conexão
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        }
    }

    //---=| CREATE |=---//
    public void create(Long cpfCliente, String nomeCliente, Long telefoneCliente, String ruaCliente, String numeroCliente, Integer cepCliente) throws SQLException {
        String query = "INSERT INTO clientes (cpf_cliente, nome_cliente, telefone_cliente, rua_cliente, numero_rua_cliente, cep_cliente) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setLong(1, cpfCliente);
            preparedStatement.setString(2, nomeCliente);
            preparedStatement.setLong(3, telefoneCliente);
            preparedStatement.setString(4, ruaCliente);
            preparedStatement.setString(5, numeroCliente);
            preparedStatement.setDouble(6, cepCliente);
            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Cliente cadastrada com sucesso!");

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
    public void update(Long cpfCliente, String nomeCliente, Long telefoneCliente, String ruaCliente, String numeroCliente, Integer cepCliente) throws SQLException {
        String query = "UPDATE clientes SET nome_cliente = ?, telefone_cliente = ?, rua_cliente = ?, numero_rua_cliente = ?, cep_cliente = ? WHERE cpf_cliente = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setString(1, nomeCliente);
            preparedStatement.setLong(2, telefoneCliente);
            preparedStatement.setString(3, ruaCliente);
            preparedStatement.setString(4, numeroCliente);
            preparedStatement.setInt(5, cepCliente);
            preparedStatement.setLong(6, cpfCliente);
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

    //---=| DELETE |=---//
    public void delete(Long cpfCliente) throws SQLException {
        String query = "DELETE FROM clientes WHERE cpf_cliente = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setLong(1, cpfCliente);
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
}
