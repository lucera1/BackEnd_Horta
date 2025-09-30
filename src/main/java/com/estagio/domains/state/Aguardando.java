package com.estagio.domains.state;

import com.estagio.domains.Pedido;
import com.estagio.domains.enums.StatusPedido;

public class Aguardando implements EstadoPedido{
    private StatusPedido statusPedido;
    private Pedido pedido;

    public Aguardando(Pedido pedido){
        this.pedido = pedido;
    }

    @Override
    public void preparar(Pedido pedido) {
        this.pedido.setEstadoPedido(new Preparando(this.pedido));
        this.pedido.setStatusPedido(StatusPedido.PREPARANDO);
    }

    @Override
    public void entregar(Pedido pedido) {
        throw new IllegalStateException("NÃO PODE ENTREGAR SEM PREPARAR");
    }

    @Override
    public void cancelar(Pedido pedido) {
        this.pedido.setEstadoPedido(new Cancelado(this.pedido));
        this.pedido.setStatusPedido(StatusPedido.CANCELADO);
    }


    public String getStatusPedido(){
        return StatusPedido.AGUARDANDO_PAGAMENTO.getStatusPedido();
    }
}
