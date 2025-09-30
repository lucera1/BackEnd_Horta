package com.estagio.domains.enums;

public enum TipoProduto {
    VERDURA(1L, "VERDURA"), LEGUME(2L, "LEGUME");

    private Long id;
    private String tipoProduto;

    TipoProduto( Long id, String tipoProduto ) {
        this.id = id;
        this.tipoProduto = tipoProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto( String tipoProduto ) {
        this.tipoProduto = tipoProduto;
    }

    @Override
    public String toString() {
        return "tipoProduto='" + tipoProduto;
    }

    public static TipoProduto toEnum (Long id) {
        if (id == null) return null;
        for (TipoProduto x : TipoProduto.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Produto Inválido");
    }
}
