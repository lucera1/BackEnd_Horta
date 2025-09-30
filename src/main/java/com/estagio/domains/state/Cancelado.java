package com.estagio.domains.state;

import com.estagio.domains.Pedido;
import com.estagio.domains.enums.StatusPedido;

public class Cancelado implements EstadoPedido {

    private Pedido pedido;
    private StatusPedido statusPedido;

    public Cancelado(Pedido pedido){
        this.pedido = pedido;
    }

    public Cancelado() {

    }


    @Override
    public void preparar(Pedido pedido) {
        throw new IllegalStateException("PEDIDO CANCLADO! AÇÃO INVÁLIDA");
    }

    @Override
    public void entregar(Pedido pedido) {
        throw new IllegalStateException("PEDIDO CANCELADO! AÇÃO INVALIDA");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("PEDIDO JÁ ESTÁ CANCELADO!");
    }

    public String getStatusPedido(){
        return StatusPedido.CANCELADO.toString();
    }
}
