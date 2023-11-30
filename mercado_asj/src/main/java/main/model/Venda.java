package main.model;

import java.sql.Date;

public class Venda {
    //-----===| ATRIBUTOS |===-----//
    private Integer idVenda;
    private Long cpfClienteVenda;
    private Short codigoProdutoVenda;
    private Integer quantidadeProdutoVenda;
    private Double precoVenda;
    private String metodoPagamento;
    private Date dataVenda;

    //-----===| CONSTRUTOR |===-----//
    public Venda() {}

    public Venda(Integer idVenda, Long cpfClienteVenda, Short codigoProdutoVenda, Integer quantidadeProdutoVenda,
            Double precoVenda, String metodoPagamento, Date dataVenda) {
        this.idVenda = idVenda;
        this.cpfClienteVenda = cpfClienteVenda;
        this.codigoProdutoVenda = codigoProdutoVenda;
        this.quantidadeProdutoVenda = quantidadeProdutoVenda;
        this.precoVenda = precoVenda;
        this.metodoPagamento = metodoPagamento;
        this.dataVenda = dataVenda;
    }

    //-----===| GETTERS & SETTERS |===-----//
    public Integer getIdVenda() {
        return idVenda;
    }
    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Long getCpfClienteVenda() {
        return cpfClienteVenda;
    }
    public void setCpfClienteVenda(Long cpfClienteVenda) {
        this.cpfClienteVenda = cpfClienteVenda;
    }

    public Short getCodigoProdutoVenda() {
        return codigoProdutoVenda;
    }
    public void setCodigoProdutoVenda(Short codigoProdutoVenda) {
        this.codigoProdutoVenda = codigoProdutoVenda;
    }

    public Integer getQuantidadeProdutoVenda() {
        return quantidadeProdutoVenda;
    }
    public void setQuantidadeProdutoVenda(Integer quantidadeProdutoVenda) {
        this.quantidadeProdutoVenda = quantidadeProdutoVenda;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }
    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }
    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Date getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
}
