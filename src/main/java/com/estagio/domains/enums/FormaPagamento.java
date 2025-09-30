package com.estagio.domains.enums;

public enum FormaPagamento {

    DINHEIRO(1,"DINHEIRO"), PIX(2, "PIX");

    private Integer id;
    private String pagamento;

    FormaPagamento(Integer id, String pagamento) {
        this.id = id;
        this.pagamento = pagamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        return "FormaPagamento{" +
                "id=" + id +
                ", pagamento='" + pagamento + '\'' +
                '}';
    }

    public static FormaPagamento toEnum(Integer id){
        if(id == null) return null;
        for(FormaPagamento x : FormaPagamento.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Forma de Pagamento Invalida");
    }
}
