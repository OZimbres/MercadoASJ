package main.view;

import javax.swing.JTabbedPane;

import main.model.Login;
import main.view.cliente.PainelClientes;
import main.view.estoque.PainelEstoque;
import main.view.funcionario.PainelFuncionarios;

public class TabbedApp extends JTabbedPane {
    //PainelCarros painelCarros;
    PainelClientes painelClientes = new PainelClientes();
    PainelFuncionarios painelFuncionarios = new PainelFuncionarios();
    PainelEstoque painelEstoque = new PainelEstoque();
    //PainelVendas painelVendas;

    public TabbedApp(Login logado) {
        super();
        //painelCarros = new PainelCarros(logado);
        //painelVendas = new PainelVendas(logado);
        //this.add("Carros", painelCarros);
        switch (logado.getNivelAcesso()) {
            case "gerente":
                this.add("Estoque", painelEstoque);
                this.add("Funcionários", painelFuncionarios);
            case "operador":
                this.add("Clientes", painelClientes);
            default:
                break;
        }
        //this.add("Venda", painelVendas);

        this.addChangeListener(e -> {
            //painelVendas.atualizarClientesComboBox();
            //painelVendas.atualizarCarrosComboBox();
            //painelCarros.atualizarPainelCarros();
        });
    }
}