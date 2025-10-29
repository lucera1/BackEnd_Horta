package com.estagio.domains.dtos;

import com.estagio.domains.Venda;
import java.math.BigDecimal;

public class VendaResumoDTO {

    private Long id;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal subTotal;

    public VendaResumoDTO() {
    }

    public VendaResumoDTO(Venda venda) {
        this.id = venda.getId();
        this.nomeProduto = venda.getProduto() != null ? venda.getProduto().getNome() : null;
        this.quantidade = venda.getQuantidade();
        this.subTotal = venda.getSubTotal();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public BigDecimal getSubTotal() { return subTotal; }
    public void setSubTotal(BigDecimal subTotal) { this.subTotal = subTotal; }
}
