package com.estagio.domains.state;

import com.estagio.domains.Pedido;

public interface EstadoPedido {


     void preparar(Pedido pedido);
     void entregar(Pedido pedido);
     void cancelar(Pedido pedido);
}
