package com.estagio.domains;

import com.estagio.domains.dtos.PedidoDTO;
import com.estagio.domains.enums.FormaPagamento;
import com.estagio.domains.enums.StatusPedido;
import com.estagio.domains.state.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pedido")
    @SequenceGenerator(name = "seq_pedido", sequenceName = "seq_pedido", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;


    @JsonIgnore
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venda> vendas = new ArrayList<>();

    @JsonIgnore
    @Transient
    private EstadoPedido estadoPedido;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "statusPedido")
    private StatusPedido statusPedido;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "formaPagamento")
    private FormaPagamento formaPagamento;

    private BigDecimal valorTotal;

    public Pedido() {
        this.estadoPedido = new Aguardando(this);
        this.statusPedido = StatusPedido.AGUARDANDO_PAGAMENTO;
    }

    public Pedido(Long id, Cliente cliente, LocalDate data,
                  FormaPagamento formaPagamento) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.formaPagamento = formaPagamento;
        this.valorTotal = getValorTotal();
        this.estadoPedido = new Aguardando(this);
        this.statusPedido = StatusPedido.AGUARDANDO_PAGAMENTO;
    }

    public Pedido( PedidoDTO dto ){
        this.id = dto.getId();
        this.cliente = new Cliente();
        this.cliente.setId(dto.getCliente());
        this.data = dto.getData();
        this.estadoPedido = new Aguardando(this);
        this.statusPedido = StatusPedido.AGUARDANDO_PAGAMENTO;
        this.formaPagamento = FormaPagamento.valueOf(dto.getFormaPagamento());
        this.valorTotal = getValorTotal();
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente( Cliente cliente ) {
        this.cliente = cliente;
    }

    @JsonProperty("valorTotal")
    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(BigDecimal valorTotal ) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData( LocalDate data ) {
        this.data = data;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas( List<Venda> vendas ) {
        this.vendas = vendas;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido( EstadoPedido estadoPedido ) {
        this.estadoPedido = estadoPedido;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido( StatusPedido statusPedido ) {
        this.statusPedido = statusPedido;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento( FormaPagamento formaPagamento ) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal calcularValorTotal() {
        if (vendas == null || vendas.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return vendas.stream()
                .map(Venda::calcularSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    public void atualizarValorTotal() {
        this.valorTotal = calcularValorTotal();
    }

    public void adicionarVenda(Venda venda) {
        venda.setPedido(this);
        this.vendas.add(venda);
    }

    public void atualizarEstadoPedido() {
        switch (this.statusPedido) {
            case AGUARDANDO_PAGAMENTO:
                this.estadoPedido = new Aguardando(this);
                break;
            case PREPARANDO:
                this.estadoPedido = new Preparando(this);
                break;
            case ENTREGUE:
                this.estadoPedido = new Entregue(this);
                break;
            case CANCELADO:
                this.estadoPedido = new Cancelado(this);
                break;
            default:
                throw new IllegalArgumentException("Estado desconhecido");
        }
    }


    public void preparar() {
        this.estadoPedido.preparar(this); // delega ao estado atual o método preparar
        this.statusPedido = StatusPedido.PREPARANDO; // ou equivalente para persistir
    }

    public void entregar() {
        estadoPedido.entregar(this);
        this.statusPedido = StatusPedido.ENTREGUE;
    }

    public void cancelar() {
        estadoPedido.cancelar(this);
        this.statusPedido = StatusPedido.CANCELADO;
    }


}