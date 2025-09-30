package com.estagio.domains.enums;

public enum StatusPedido {
    AGUARDANDO_PAGAMENTO(1,"AGUARDANDO_PAGAMENTO"), PREPARANDO(2,"PREPARANDO"),

    ENTREGUE(3,"ENTREGUE"), CANCELADO(4,"CANCELADO");

    private Integer id;
    private String statusPedido;

    StatusPedido(Integer id, String statusPedido) {
        this.id = id;
        this.statusPedido = statusPedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    @Override
    public String toString() {
        return statusPedido;
    }

    public static StatusPedido toEnum(Integer id){
        if(id == null) return null;
        for(StatusPedido x : StatusPedido.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Estado Invalido");
    }
}
