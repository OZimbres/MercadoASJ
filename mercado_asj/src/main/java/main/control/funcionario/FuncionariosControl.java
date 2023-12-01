package main.control.funcionario;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.model.Funcionario;

public class FuncionariosControl {
    // -----===| ATRIBUTOS |===-----//
    private FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
    private List<Funcionario> funcionarios;
    private DefaultTableModel tableModel;
    private JTable table;

    // -----===| CONSTRUTOR |===-----//
    public FuncionariosControl(List<main.model.Funcionario> funcionarios, DefaultTableModel tableModel, JTable table) {
        this.funcionarios = funcionarios;
        this.tableModel = tableModel;
        this.table = table;
    }

    // -----===| MÉTODOS CRUD |===-----//
    // ---=| CREATE |=---//
    public void createFuncionario(Long cpfFuncionario, String nomeFuncionario, Long telefoneFuncionario,
            String ruaFuncionario, String numeroFuncionario, Integer cepFuncionario, String senhaFuncionario,
            String nivelAcessoFuncionario) {
        try {
            funcionariosDAO.create(cpfFuncionario, nomeFuncionario, telefoneFuncionario, ruaFuncionario,
                    numeroFuncionario, cepFuncionario, senhaFuncionario, nivelAcessoFuncionario);

            Funcionario funcionario = new Funcionario(cpfFuncionario, nomeFuncionario, telefoneFuncionario,
                    ruaFuncionario, numeroFuncionario, cepFuncionario, senhaFuncionario, nivelAcessoFuncionario);
            funcionarios.add(funcionario);

            atualizarTabela();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---=| UPDATE |=---//
    public void updateFuncionario(int linhaSelecionada, Long cpfFuncionario, String nomeFuncionario,
            Long telefoneFuncionario, String ruaFuncionario, String numeroFuncionario, Integer cepFuncionario,
            String senhaFuncionario, String nivelAcessoFuncionario) {
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

    // ---=| DELETE |=---//
    public void deleteFuncionario(int linhaSelecionada, Long cpfFuncionario) {
        try {
            if (linhaSelecionada != 1) {
                funcionariosDAO.delete(cpfFuncionario);
                funcionarios.remove(linhaSelecionada);

                atualizarTabela();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---=| Atualizar Tabela |=---//
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

    public boolean checkFuncionarioCampos(int linhaSelecionada, String operacao, String cpf, String nome, String telefone, String rua, String numero, String cep, String senha, String nivelAcesso) {
        // Verifica se os campos estão preenchidos
        if (cpf.isEmpty() || nome.isEmpty() || senha.isEmpty() || nivelAcesso.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ATENÇÃO!\nCampos obrigatórios estão em branco");
            return false;
        }
        if (!validarFormatoCPF(cpf)) {
            JOptionPane.showMessageDialog(null, "CPF inválido!\nO CPF deve conter apenas números e ter 11 dígitos!");
            return false;
        }
        if (!validarFormatoTelefone(telefone)) {
            JOptionPane.showMessageDialog(null,
                    "Telefone inválido!\nO Telefone deve conter apenas números e ter entre 9 e 11 dígitos!");
            return false;
        }
        if (!validarFormatoNumero(numero)) {
            JOptionPane.showMessageDialog(null,
                    "Número inválido!\nO Número deve conter até 10 dígitos e no máximo 1 letra!");
            return false;
        }
        if (!validarFormatoCEP(cep)) {
            JOptionPane.showMessageDialog(null, "CEP inválido!\nO CEP deve conter apenas números e ter 8 dígitos!");
            return false;
        }
        if (!validarFormatoSenha(senha)) {
            JOptionPane.showMessageDialog(null,"Senha Invalida!\nA senha deve ter menos de 24 digitos e não pode ser nula!");
            return false;
        }

        if (operacao.equals("cadastrar")) {
            int resposta = JOptionPane.showConfirmDialog(null, "Realizar cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                // Evitar problemas de conversão (Long/Integer para String)
                telefone = (telefone.equals("")) ? "0" : telefone;
                cep = (cep.equals("")) ? "0" : cep;

                // Executa a operação de cadastrar
                createFuncionario(Long.valueOf(cpf.trim()), nome.trim(), Long.valueOf(telefone.trim()), rua.trim(), numero.trim(), Integer.valueOf(cep.trim()), senha.trim(), nivelAcesso.trim());
            }
        } else if (operacao.equals("atualizar")) {
            int resposta = JOptionPane.showConfirmDialog(null, "Realizar edição?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                // Evitar problemas de conversão (Long/Integer para String)
                telefone = (telefone.equals("")) ? "0" : telefone;
                cep = (cep.equals("")) ? "0" : cep;

                // Executa a operação de editar
                updateFuncionario(linhaSelecionada, Long.valueOf(cpf.trim()), nome.trim(), Long.valueOf(telefone.trim()), rua.trim(), numero.trim(), Integer.valueOf(cep.trim()), senha.trim(), nivelAcesso.trim());
            }
        } else {
            int resposta = JOptionPane.showConfirmDialog(null, "Realizar exclusão?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                // Executa a opção de deletar
                deleteFuncionario(linhaSelecionada, Long.valueOf(cpf.trim()));
            }
        }
        return true;
    }

    private boolean validarFormatoCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf.length() == 11 || cpf.length() == 0;
    }

    private static boolean validarFormatoTelefone(String telefone) {
        // Remove qualquer caractere não numérico
        String numeroLimpo = telefone.replaceAll("[^0-9]", "");
        // Verifica se o número de caracteres está entre 9 e 11
        return (numeroLimpo.length() >= 9 && numeroLimpo.length() <= 11) || numeroLimpo.length() == 0;
    }

    // Método para validar o formato da entrada (máximo 10 caracteres, permitindo
    // números e até 1 letra)
    private boolean validarFormatoNumero(String numero) {
        // Expressão regular para validar o formato da entrada
        String regex = "^[0-9]*[a-zA-Z]?[0-9]*$";
        // Remove todos os espaços em branco
        numero = numero.replaceAll("\\s", "");
        // Verifica se a entrada corresponde à expressão regular
        return numero.matches(regex) || numero.length() == 0;
    }

    // Método para validar a quantidade de caracteres
    private boolean validarFormatoCEP(String cep) {
        cep = cep.replaceAll("[^0-9]", "");
        return cep.length() == 8 || cep.length() == 0;
    }
    private boolean validarFormatoSenha(String senha) {
        return senha.length() <= 24 || senha.length() > 0;
    }
}
