package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import main.connection.ConnectionFactory;

public class LoginDAO {
    //-----===| ATRIBUTOS |===-----//
    private Connection connection; // Conexão

    //-----===| CONSTRUTOR |===-----//
    public LoginDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    //-----===| MÉTODOS |===-----//
    public String consulta(Long cpf, String senha) throws SQLException{
        ResultSet resultSet = null;

        String query = "SELECT * FROM funcionarios WHERE cpf_funcionario = ? AND senha_funcionario = ?;";
        PreparedStatement preparedStatement = connection.prepareCall(query);
        try {
            preparedStatement.setLong(1, cpf);
            preparedStatement.setString(2, senha);

            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
                if(resultSet.getString("nivel_acesso_funcionario").equals("operador")){
                    return "operador";
                }
                else{
                    return "gerente";
                }
            }
            else{
                return "";
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar logar: " + ex);
            // Vai retornar False por "default" em caso de erro
            return "";
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeConnection(this.connection);
        }

    }
}