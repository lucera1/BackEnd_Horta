package com.estagio.domains.state;

import com.estagio.domains.Pedido;
import com.estagio.domains.enums.StatusPedido;

public class Preparando implements EstadoPedido{
    private Pedido pedido;
    private StatusPedido statusPedido;

    public Preparando(Pedido pedido){
        this.pedido = pedido;
    }


    @Override
    public void preparar(Pedido pedido) {
        throw new IllegalStateException("O PEDIDO JÁ ESTÁ EM PREPARO");
    }

    @Override
    public void entregar(Pedido pedido) {
        this.pedido.setEstadoPedido(new Entregue(this.pedido));
        this.pedido.setStatusPedido(StatusPedido.ENTREGUE);
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("IMPOSSÍVEL CANCELAR UM PEDIDO EM PREPARO");
    }


    public String getStatusPedido(){
        return StatusPedido.PREPARANDO.getStatusPedido();
    }
}
