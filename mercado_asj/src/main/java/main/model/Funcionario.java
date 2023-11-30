package main.model;

public class Funcionario {
    //-----===| ATRIBUTOS |===-----//
    private Long cpfFuncionario;
    private String nomeFuncionario;
    private Long telefoneFuncionario;
    private String ruaFuncionario;
    private String numeroFuncionario;
    private Integer cepFuncionario;
    private String senhaFuncionario;
    private String nivelAcessoFuncionario; // 'operador' | 'gerente' //

    //-----===| CONSTRUTOR |===-----//
    public Funcionario() {}

    public Funcionario(Long cpfFuncionario, String nomeFuncionario, Long telefoneFuncionario, String ruaFuncionario, String numeroFuncionario, Integer cepFuncionario, String senhaFuncionario, String nivelAcessoFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.telefoneFuncionario = telefoneFuncionario;
        this.ruaFuncionario = ruaFuncionario;
        this.numeroFuncionario = numeroFuncionario;
        this.cepFuncionario = cepFuncionario;
        this.senhaFuncionario = senhaFuncionario;
        this.nivelAcessoFuncionario = nivelAcessoFuncionario; // 'operador' | 'gerente' //
    }

    
    //-----===| GETTERS & SETTERS |===-----//
    public Long getCpfFuncionario() {
        return cpfFuncionario;
    }
    public void setCpfFuncionario(Long cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }
    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public Long getTelefoneFuncionario() {
        return telefoneFuncionario;
    }
    public void setTelefoneFuncionario(Long telefoneFuncionario) {
        this.telefoneFuncionario = telefoneFuncionario;
    }

    public String getRuaFuncionario() {
        return ruaFuncionario;
    }
    public void setRuaFuncionario(String ruaFuncionario) {
        this.ruaFuncionario = ruaFuncionario;
    }

    public String getNumeroFuncionario() {
        return numeroFuncionario;
    }
    public void setNumeroFuncionario(String numeroFuncionario) {
        this.numeroFuncionario = numeroFuncionario;
    }

    public Integer getCepFuncionario() {
        return cepFuncionario;
    }
    public void setCepFuncionario(Integer cepFuncionario) {
        this.cepFuncionario = cepFuncionario;
    }

    public String getSenhaFuncionario() {
        return senhaFuncionario;
    }
    public void setSenhaFuncionario(String senhaFuncionario) {
        this.senhaFuncionario = senhaFuncionario;
    }

    // 'operador' | 'gerente' //
    public String getNivelAcessoFuncionario() {
        return nivelAcessoFuncionario;
    }
    public void setNivelAcessoFuncionario(String nivelAcessoFuncionario) {
        this.nivelAcessoFuncionario = nivelAcessoFuncionario;
    }
}
