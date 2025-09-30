package com.estagio.domains.enums;

public enum TipoUsuario {
    ADMIN(1, "ADMIN"),
    CLIENTE(2, "CLIENTE");


    private Integer id;
    private String tipoUsuario;

    TipoUsuario(Integer id, String tipoUsuario) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return tipoUsuario;
    }

    public static TipoUsuario toEnum (Integer id) {
        if (id == null) return null;
        for (TipoUsuario x : TipoUsuario.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil Inválido");
    }

    public static TipoUsuario fromDescricao(String descricao) {
        for (TipoUsuario tipo : TipoUsuario.values()) {
            if (tipo.getTipoUsuario().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("TipoUsuario inválido: " + descricao);
    }

}
