package com.estagio.domains.dtos;

import com.estagio.domains.Venda;

import java.math.BigDecimal;

public class VendaDTO {

    private Long id;

    private Long produtoId;

    private Integer quantidade;

    private Long clienteId;

    private String nomeProduto;

    private Long pedidoId;

    public VendaDTO() {}

    public VendaDTO( Venda obj) {
        this.id = obj.getId();
        this.produtoId = obj.getProduto().getId();
        this.quantidade = obj.getQuantidade();
        this.clienteId = obj.getCliente().getId();
        this.nomeProduto = obj.getProduto().getNome();
        this.pedidoId = obj.getPedido().getId();
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

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }
}


