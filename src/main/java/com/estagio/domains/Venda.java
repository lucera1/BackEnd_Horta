package com.estagio.domains;

import com.estagio.domains.dtos.VendaDTO;
import com.estagio.domains.produtos.GrupoProduto;
import com.estagio.domains.produtos.Produto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_venda")
    @SequenceGenerator(name = "seq_venda", sequenceName = "seq_venda", allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    private Integer quantidade;

    private BigDecimal subTotal;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    @JsonBackReference
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Transient
    private GrupoProduto grupoProduto;

    public Venda() {


    }

    public Venda(Long id, Cliente cliente, Produto produto, Integer quantidade) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.subTotal = calcularSubTotal();
        this.cliente = cliente;
        this.produto = produto;

    }

    public Venda ( VendaDTO dto ){
        this.id = dto.getId();
        this.quantidade = dto.getQuantidade();
        this.cliente = new Cliente();
        this.cliente.setId(dto.getClienteId());
        this.produto = new Produto();
        this.produto.setId(dto.getProdutoId());
        this.subTotal = calcularSubTotal();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



    public BigDecimal calcularSubTotal() {
        if (produto == null || quantidade == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal valorUnitario = produto.getPreco(); // deve ser BigDecimal
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade)).setScale(2, RoundingMode.HALF_EVEN);
    }

}


