package com.estagio.domains.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FormaPagamento {

    DINHEIRO(1, "DINHEIRO"),
    PIX(2, "PIX");

    private final Integer id;
    private final String pagamento;

    FormaPagamento(Integer id, String pagamento) {
        this.id = id;
        this.pagamento = pagamento;
    }

    public Integer getId() {
        return id;
    }

    public String getPagamento() {
        return pagamento;
    }

    // 🔹 Usado na serialização (resposta JSON)
    @JsonValue
    public String getJsonValue() {
        return pagamento; // Retorna o nome legível ("PIX", "DINHEIRO")
    }

    // 🔹 Usado na desserialização (quando o front envia o número ou string)
    @JsonCreator
    public static FormaPagamento fromId(Object value) {
        if (value == null) return null;

        if (value instanceof Integer) {
            Integer id = (Integer) value;
            for (FormaPagamento x : FormaPagamento.values()) {
                if (x.getId().equals(id)) {
                    return x;
                }
            }
        } else if (value instanceof String) {
            String val = ((String) value).toUpperCase();
            for (FormaPagamento x : FormaPagamento.values()) {
                if (x.getPagamento().equalsIgnoreCase(val)) {
                    return x;
                }
            }
        }

        throw new IllegalArgumentException("Forma de Pagamento inválida: " + value);
    }

    // 🔹 Método antigo, para compatibilidade com código existente
    public static FormaPagamento toEnum(Integer id) {
        if (id == null) return null;
        for (FormaPagamento x : FormaPagamento.values()) {
            if (x.getId().equals(id)) {
                return x;
            }
        }
        throw new IllegalArgumentException("Forma de Pagamento inválida: " + id);
    }

    @Override
    public String toString() {
        return pagamento;
    }
}
