package com.estagio.domains.dtos;

import com.estagio.domains.produtos.GrupoProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GrupoProdutoDTO {

    private Long id;

    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo descrição não pode estar vazio")
    private String descricao;

    public GrupoProdutoDTO() {
    }

    public GrupoProdutoDTO( GrupoProduto grupoProduto) {
        this.id = grupoProduto.getId();
        this.descricao = grupoProduto.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public @NotNull( message = "O campo não pode ser nulo" ) @NotBlank( message = "O campo descrição não pode estar vazio" ) String getDescricao() {
        return descricao;
    }

    public void setDescricao( @NotNull( message = "O campo não pode ser nulo" ) @NotBlank( message = "O campo descrição não pode estar vazio" ) String descricao ) {
        this.descricao = descricao;
    }
}
