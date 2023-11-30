package main.control.funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.connection.ConnectionFactory;
import main.model.Funcionario;

public class FuncionariosDAO {
    //-----===| ATRIBUTOS |===-----//
    private Connection connection; // Conexão
    private List<Funcionario> funcionarios; // Lista de Funcionários

    //-----===| CONSTRUTOR |===-----//
    public FuncionariosDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    //-----===| MÉTODOS CRUD |===-----//
    //---=| READ ALL |=---//
    public List<Funcionario> readAll() throws SQLException{
        ResultSet resultSet = null; // Objeto que armazena
        funcionarios = new ArrayList<>();

        String query = "SELECT * FROM funcionarios;"; // SQL Query
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try{
            resultSet = preparedStatement.executeQuery();

            // Loop para armazenar as informações do resultSet para a List<Funcionario>
            while(resultSet.next()){
                Funcionario funcionario = new Funcionario(resultSet.getLong("cpf_funcionario"), resultSet.getString("nome_funcionario"), resultSet.getLong("telefone_funcionario"), resultSet.getString("rua_funcionario"), resultSet.getString("numero_rua_funcionario"), resultSet.getInt("cep_funcionario"), resultSet.getString("senha"), resultSet.getString("nivel_acesso_funcionario")); // Instanciando Funcionario com as informações obtidas pela query

                funcionarios.add(funcionario); // Adicionando objetos instanciados à lista
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar: "+ ex);
        } finally { // Independente se deu certo ou não, tem que fechar a conexão
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        }

        // Retornando lista (list<Funcionario>)
        return funcionarios;
    }

    //---=| READ |=---//
    public Funcionario read(Long cpfFuncionario) throws SQLException {
        ResultSet resultSet = null; // Objeto que armazena
        Funcionario funcionario;

        String query = "SELECT * FROM funcionarios WHERE cpf_funcionario = ?;"; // SQL Query
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setLong(1, cpfFuncionario);

            resultSet = preparedStatement.executeQuery();
            // Loop para armazenar as informações do resultSet para a List<Funcionario>

            if(resultSet.next()){
                funcionario = new Funcionario(resultSet.getLong("cpf_funcionario"), resultSet.getString("nome_funcionario"), resultSet.getLong("telefone_funcionario"), resultSet.getString("rua_funcionario"), resultSet.getString("numero_rua_funcionario"), resultSet.getInt("cep_funcionario"), resultSet.getString("senha"), resultSet.getString("nivel_acesso_funcionario")); // Instanciando Funcionario com as informações obtidas pela query

                return funcionario;
            }
            else{
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
            return null;
        }
        finally { // Independente se deu certo ou não, tem que fechar a conexão
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        }
    }

    //---=| CREATE |=---//
    public void create(Long cpfFuncionario, String nomeFuncionario, Long telefoneFuncionario, String ruaFuncionario, String numeroFuncionario, Integer cepFuncionario, String senhaFuncionario, String nivelAcessoFuncionario) throws SQLException {
        String query = "INSERT INTO funcionarios (cpf_funcionario, nome_funcionario, telefone_funcionario, rua_funcionario, numero_rua_funcionario, cep_funcionario, senha, nivel_acesso_funcionario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setLong(1, cpfFuncionario);
            preparedStatement.setString(2, nomeFuncionario);
            preparedStatement.setLong(3, telefoneFuncionario);
            preparedStatement.setString(4, ruaFuncionario);
            preparedStatement.setString(5, numeroFuncionario);
            preparedStatement.setDouble(6, cepFuncionario);
            preparedStatement.setString(7, senhaFuncionario);
            preparedStatement.setString(8, nivelAcessoFuncionario);
            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!");

            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(connection);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    //---=| UPDATE |=---//
    public void update(Long cpfFuncionario, String nomeFuncionario, Long telefoneFuncionario, String ruaFuncionario, String numeroFuncionario, Integer cepFuncionario, String senhaFuncionario, String nivelAcessoFuncionario) throws SQLException {
        String query = "UPDATE funcionarios SET nome_funcionario = ?, telefone_funcionario = ?, rua_funcionario = ?, numero_rua_funcionario = ?, cep_funcionario = ?, senha = ?, nivel_acesso_funcionario = ? WHERE cpf_funcionario = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setString(1, nomeFuncionario);
            preparedStatement.setLong(2, telefoneFuncionario);
            preparedStatement.setString(3, ruaFuncionario);
            preparedStatement.setString(4, numeroFuncionario);
            preparedStatement.setInt(5, cepFuncionario);
            preparedStatement.setString(6, senhaFuncionario);
            preparedStatement.setString(7, nivelAcessoFuncionario);
            preparedStatement.setLong(8, cpfFuncionario);
            preparedStatement.execute();

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
    public void delete(Long cpfFuncionario) throws SQLException {
        String query = "DELETE FROM funcionarios WHERE cpf_funcionario = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setLong(1, cpfFuncionario);
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
