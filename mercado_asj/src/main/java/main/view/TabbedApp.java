package main.view;

import javax.swing.JTabbedPane;

import main.model.Login;
import main.view.funcionario.PainelFuncionarios;

public class TabbedApp extends JTabbedPane {
    //PainelCarros painelCarros;
    PainelFuncionarios painelFuncionarios = new PainelFuncionarios();
    //PainelVendas painelVendas;

    public TabbedApp(Login logado) {
        super();
        //painelCarros = new PainelCarros(logado);
        //painelVendas = new PainelVendas(logado);
        //this.add("Carros", painelCarros);
        this.add("Funcionarios", painelFuncionarios);
        //this.add("Venda", painelVendas);

        this.addChangeListener(e -> {
            //painelVendas.atualizarClientesComboBox();
            //painelVendas.atualizarCarrosComboBox();
            //painelCarros.atualizarPainelCarros();
        });
    }
}