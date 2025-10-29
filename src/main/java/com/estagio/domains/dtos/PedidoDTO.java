package com.estagio.domains.dtos;

import com.estagio.domains.Pedido;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<VendaResumoDTO> vendas;

    public PedidoDTO() {
    }

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.cliente = pedido.getCliente().getId();
        this.clienteNome = pedido.getCliente().getFirstName() + " " + pedido.getCliente().getLastName();
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotal();
        this.formaPagamento = pedido.getFormaPagamento().getPagamento();
        this.statusPedido = pedido.getStatusPedido().toString();

        // Monta a lista de vendas resumidas
        if (pedido.getVendas() != null) {
            this.vendas = pedido.getVendas().stream()
                    .map(VendaResumoDTO::new)
                    .collect(Collectors.toList());
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCliente() { return cliente; }
    public void setCliente(Long cliente) { this.cliente = cliente; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public String getClienteNome() { return clienteNome; }
    public void setClienteNome(String clienteNome) { this.clienteNome = clienteNome; }

    public String getStatusPedido() { return statusPedido; }
    public void setStatusPedido(String statusPedido) { this.statusPedido = statusPedido; }

    public List<VendaResumoDTO> getVendas() { return vendas; }
    public void setVendas(List<VendaResumoDTO> vendas) { this.vendas = vendas; }
}
