package com.estagio.domains.state;

import com.estagio.domains.Pedido;
import com.estagio.domains.enums.StatusPedido;

public class Entregue implements EstadoPedido{

    private StatusPedido statusPedido;

    private Pedido pedido;

    public Entregue(Pedido pedido){
        this.pedido = pedido;
    }


    @Override
    public void preparar(Pedido pedido) {
        throw new IllegalStateException("O PEDIDO JÁ FOI ENTREGUE! AÇÃO INVÁLIDA");
    }

    @Override
    public void entregar(Pedido pedido) {
        throw new IllegalStateException("O PEDIDO JÁ FOI ENTREGUE");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("O PEDIDO JÁ FOI ENTREGUE! AÇÃO INVÁLIDA");
    }

    public String getStatusPedido(){
        return StatusPedido.ENTREGUE.getStatusPedido();
    }
}
