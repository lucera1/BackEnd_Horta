package com.estagio.domains.produtos;

import com.estagio.domains.Venda;
import com.estagio.domains.dtos.GrupoProdutoDTO;
import com.estagio.domains.enums.TipoProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="grupoproduto")
public class GrupoProduto {

    @Id
    private Long id;

    private TipoProduto tipoProduto;

    @Transient
    private Venda venda;

    @NotNull
    @NotBlank
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "grupoProduto")
    private List<Produto> produtos = new ArrayList<>();

    public GrupoProduto() {

    }

    public GrupoProduto(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public GrupoProduto( GrupoProdutoDTO dto) {
        this.id = dto.getId();
        this.descricao = dto.getDescricao();

    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }


    public @NotNull @NotBlank String getDescricao() {
        return descricao;
    }

    public void setDescricao( @NotNull @NotBlank String descricao ) {
        this.descricao = descricao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos( List<Produto> produtos ) {
        this.produtos = produtos;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoProduto that = (GrupoProduto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
