package main.model;

public class Cliente {
    //-----===| ATRIBUTOS |===-----//
    private Long cpfCliente;
    private String nomeCliente;
    private Long telefoneCliente;
    private String ruaCliente;
    private String numeroCliente;
    private Integer cepCliente;

    //-----===| CONSTRUTOR |===-----//
    public Cliente() {}

    public Cliente(Long cpfCliente, String nomeCliente, Long telefoneCliente, String ruaCliente, String numeroCliente, Integer cepCliente) {
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.telefoneCliente = telefoneCliente;
        this.ruaCliente = ruaCliente;
        this.numeroCliente = numeroCliente;
        this.cepCliente = cepCliente;
    }

    //-----===| GETTERS & SETTERS |===-----//
    public Long getCpfCliente() {
        return cpfCliente;
    }
    public void setCpfCliente(Long cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Long getTelefoneCliente() {
        return telefoneCliente;
    }
    public void setTelefoneCliente(Long telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getRuaCliente() {
        return ruaCliente;
    }
    public void setRuaCliente(String ruaCliente) {
        this.ruaCliente = ruaCliente;
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }
    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public Integer getCepCliente() {
        return cepCliente;
    }
    public void setCepCliente(Integer cepCliente) {
        this.cepCliente = cepCliente;
    }
}
