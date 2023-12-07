package main.model;

public class Estoque {
    //-----===| ATRIBUTOS |===-----//
    private String codigoProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private String nomeFornecedor;
    private Double precoProduto;
    private Integer quantidadeProduto;
    private Double descontoVip;
    private Boolean statusProduto;

    //-----===| CONSTRUTOR |===-----//
    public Estoque() {}

    public Estoque(String codigoProduto, String nomeProduto, String descricaoProduto, String nomeFornecedor, Double precoProduto, Integer quantidadeProduto, Double descontoVip, Boolean statusProduto) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.nomeFornecedor = nomeFornecedor;
        this.precoProduto = precoProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.descontoVip = descontoVip;
        this.statusProduto = statusProduto;
    }

    //-----===| GETTERS & SETTERS |===-----//
    public String getCodigoProduto() {
        return codigoProduto;
    }
    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }
    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }
    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }
    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public Integer getQuantidadeProduto() {
        return quantidadeProduto;
    }
    public void setQuantidadeProduto(Integer quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Double getDescontoVip() {
        return descontoVip;
    }
    public void setDescontoVip(Double descontoVip) {
        this.descontoVip = descontoVip;
    }

    public Boolean getStatusProduto() {
        return statusProduto;
    }
    public void setStatusProduto(Boolean statusProduto) {
        this.statusProduto = statusProduto;
    }
}
