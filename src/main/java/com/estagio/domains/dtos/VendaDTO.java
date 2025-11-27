package com.estagio.domains.dtos;

import com.estagio.domains.Venda;

import java.math.BigDecimal;

public class VendaDTO {

    private Long id;
    private Long produtoId;
    private Integer quantidade;
    private Long clienteId;
    private String nomeProduto;
    private String nomeCliente;
    private Long pedidoId;
    private BigDecimal subTotal;

    public VendaDTO() {}

    public VendaDTO(Venda obj) {
        this.id = obj.getId();
        this.produtoId = obj.getProduto().getId();
        this.quantidade = obj.getQuantidade();
        this.clienteId = obj.getCliente().getId();
        this.nomeCliente = obj.getCliente().getFirstName() + " " + obj.getCliente().getLastName();
        this.nomeProduto = obj.getProduto().getNome();
        this.pedidoId = obj.getPedido().getId();
        this.subTotal = obj.getSubTotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
