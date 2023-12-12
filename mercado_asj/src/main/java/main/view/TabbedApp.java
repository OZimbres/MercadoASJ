package main.view;

import javax.swing.JTabbedPane;

import main.model.Login;
import main.view.cliente.PainelClientes;
import main.view.estoque.PainelEstoque;
import main.view.funcionario.PainelFuncionarios;
import main.view.venda.PainelVenda;

public class TabbedApp extends JTabbedPane {
    //PainelCarros painelCarros;
    PainelClientes painelClientes = new PainelClientes();
    PainelFuncionarios painelFuncionarios = new PainelFuncionarios();
    PainelEstoque painelEstoque = new PainelEstoque();
    PainelVenda painelVenda = new PainelVenda();

    public TabbedApp(Login logado) {
        super();
        //painelCarros = new PainelCarros(logado);
        //painelVenda = new PainelVenda();
        //this.add("Carros", painelCarros);
        switch (logado.getNivelAcesso()) {
            case "gerente":
                this.add("Estoque", painelEstoque);
                this.add("Funcionários", painelFuncionarios);
            case "operador":
                this.add("Venda", painelVenda);
                this.add("Clientes", painelClientes);
            default:
                break;
        }
        //this.add("Venda", painelVendas);

        this.addChangeListener(e -> {
            //painelVenda.atualizarEstoque();
            //painelVendas.atualizarCarrosComboBox();
            //painelCarros.atualizarPainelCarros();
        });
    }
}