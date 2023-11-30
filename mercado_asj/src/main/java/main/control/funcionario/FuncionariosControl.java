package main.control.funcionario;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.model.Funcionario;

public class FuncionariosControl {
    //-----===| ATRIBUTOS |===-----//
    private FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
    private List<Funcionario> funcionarios;
    private DefaultTableModel tableModel;
    private JTable table;
    
    //-----===| CONSTRUTOR |===-----//
    public FuncionariosControl(FuncionariosDAO funcionariosDAO, List<Funcionario> funcionarios, DefaultTableModel tableModel, JTable table) {
        this.funcionariosDAO = funcionariosDAO;
        this.funcionarios = funcionarios;
        this.tableModel = tableModel;
        this.table = table;
    }

    //-----===| MÉTODOS CRUD |===-----//
    // ---=| CREATE |=---//
    public void createFuncionario(Long cpfFuncionario, String nomeFuncionario, Long telefoneFuncionario, String ruaFuncionario, String numeroFuncionario, Integer cepFuncionario, String senhaFuncionario, String nivelAcessoFuncionario) {
        try {
            funcionariosDAO.create(cpfFuncionario, nomeFuncionario, telefoneFuncionario, ruaFuncionario, numeroFuncionario, cepFuncionario, senhaFuncionario, nivelAcessoFuncionario);

            Funcionario funcionario = new Funcionario(cpfFuncionario, nomeFuncionario, telefoneFuncionario, ruaFuncionario, numeroFuncionario, cepFuncionario, senhaFuncionario, nivelAcessoFuncionario);
            funcionarios.add(funcionario);

            atualizarTabela();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //---=| UPDATE |=---//
    public void updatePessoa(int linhaSelecionada, Long cpfFuncionario, String nomeFuncionario, Long telefoneFuncionario, String ruaFuncionario, String numeroFuncionario, Integer cepFuncionario, String senhaFuncionario, String nivelAcessoFuncionario) {
        if (linhaSelecionada != -1) {
            try {
                funcionariosDAO.update(cpfFuncionario, nomeFuncionario, telefoneFuncionario, ruaFuncionario, numeroFuncionario, cepFuncionario, senhaFuncionario, nivelAcessoFuncionario);
                
                Funcionario funcionario = new Funcionario(cpfFuncionario, nomeFuncionario, telefoneFuncionario, ruaFuncionario, numeroFuncionario, cepFuncionario, senhaFuncionario, nivelAcessoFuncionario);
                funcionarios.set(linhaSelecionada, funcionario);
    
                atualizarTabela();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //---=| DELETE |=---//
    public void deleteFuncionario(int linhaSelecionada, Long cpfFuncionario) {
        try {
            if(linhaSelecionada != 1){
                funcionariosDAO.delete(cpfFuncionario);
                funcionarios.remove(linhaSelecionada);
                
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
        for (int i = 0; i < funcionarios.size(); i++) {
            linha[0] = funcionarios.get(i).getCpfFuncionario();
            linha[1] = funcionarios.get(i).getNomeFuncionario();
            linha[2] = funcionarios.get(i).getTelefoneFuncionario();
            linha[3] = funcionarios.get(i).getRuaFuncionario();
            linha[4] = funcionarios.get(i).getNumeroFuncionario();
            linha[5] = funcionarios.get(i).getCepFuncionario();
            linha[6] = funcionarios.get(i).getSenhaFuncionario();
            linha[7] = funcionarios.get(i).getNivelAcessoFuncionario();
        }
    }
}
