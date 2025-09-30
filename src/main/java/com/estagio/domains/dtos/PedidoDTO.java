package com.estagio.domains.dtos;

import com.estagio.domains.Pedido;
import com.estagio.domains.enums.FormaPagamento;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;


public class PedidoDTO {

    private Long id;

    @NotNull(message = "O campo não pode ser nulo!")
    private Long cliente;

    @NotNull(message = "O campo não pode ser nulo!")
    private String formaPagamento;

    @NotNull(message = "O campo não pode ser nulo!")
    private LocalDate data;

    private BigDecimal valorTotal;

    private String clienteNome;

    private String statusPedido;


    public PedidoDTO() {
    }

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.cliente = pedido.getCliente().getId();
        this.clienteNome = pedido.getCliente().getFirstName()+" "+pedido.getCliente().getLastName();
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotal();
        this.formaPagamento = pedido.getFormaPagamento().getPagamento();
        this.statusPedido = pedido.getStatusPedido().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull( message = "O campo não pode ser nulo!" ) Long getCliente() {
        return cliente;
    }

    public void setCliente(@NotNull( message = "O campo não pode ser nulo!" ) Long cliente) {
        this.cliente = cliente;
    }

    public @NotNull( message = "O campo não pode ser nulo!" ) String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(@NotNull( message = "O campo não pode ser nulo!" ) String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public @NotNull( message = "O campo não pode ser nulo!" ) LocalDate getData() {
        return data;
    }

    public void setData(@NotNull( message = "O campo não pode ser nulo!" ) LocalDate data) {
        this.data = data;
    }

    public  String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome( String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public  String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido( String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}


