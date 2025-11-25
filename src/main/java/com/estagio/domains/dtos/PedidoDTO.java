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


    private String enderecoEntrega;
    private String bairroEntrega;
    private String cidadeEntrega;
    private String cepEntrega;
    private String referenciaEntrega;
    private String nomeRecebedor;
    private String telefoneContato;

    public PedidoDTO() {
    }

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.cliente = pedido.getCliente() != null ? pedido.getCliente().getId() : null;
        this.clienteNome = pedido.getCliente() != null ? pedido.getCliente().getFirstName() + " " + pedido.getCliente().getLastName() : null;
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotal();
        this.formaPagamento = pedido.getFormaPagamento() != null ? pedido.getFormaPagamento().getPagamento() : null;
        this.statusPedido = pedido.getStatusPedido() != null ? pedido.getStatusPedido().toString() : null;


        if (pedido.getVendas() != null) {
            this.vendas = pedido.getVendas().stream()
                    .map(VendaResumoDTO::new)
                    .collect(Collectors.toList());
        }


        this.enderecoEntrega = pedido.getEnderecoEntrega();
        this.bairroEntrega = pedido.getBairroEntrega();
        this.cidadeEntrega = pedido.getCidadeEntrega();
        this.cepEntrega = pedido.getCepEntrega();
        this.referenciaEntrega = pedido.getReferenciaEntrega();
        this.nomeRecebedor = pedido.getNomeRecebedor();
        this.telefoneContato = pedido.getTelefoneContato();
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


    public String getEnderecoEntrega() { return enderecoEntrega; }
    public void setEnderecoEntrega(String enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }

    public String getBairroEntrega() { return bairroEntrega; }
    public void setBairroEntrega(String bairroEntrega) { this.bairroEntrega = bairroEntrega; }

    public String getCidadeEntrega() { return cidadeEntrega; }
    public void setCidadeEntrega(String cidadeEntrega) { this.cidadeEntrega = cidadeEntrega; }

    public String getCepEntrega() { return cepEntrega; }
    public void setCepEntrega(String cepEntrega) { this.cepEntrega = cepEntrega; }

    public String getReferenciaEntrega() { return referenciaEntrega; }
    public void setReferenciaEntrega(String referenciaEntrega) { this.referenciaEntrega = referenciaEntrega; }

    public String getNomeRecebedor() { return nomeRecebedor; }
    public void setNomeRecebedor(String nomeRecebedor) { this.nomeRecebedor = nomeRecebedor; }

    public String getTelefoneContato() { return telefoneContato; }
    public void setTelefoneContato(String telefoneContato) { this.telefoneContato = telefoneContato; }
}
