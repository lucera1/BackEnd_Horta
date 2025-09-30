package com.estagio.domains.produtos;

import com.estagio.domains.dtos.ProdutoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import java.util.Objects;


@Entity
    @Table(name = "produto")
    @Inheritance(strategy = InheritanceType.JOINED)
    public class Produto {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_produto" )
    @SequenceGenerator( name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1 )
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Digits( integer = 15, fraction = 2 )
    private BigDecimal preco;

    private Integer qtdEstoque;

    @ManyToOne
    @JoinColumn( name = "idGrupoProduto" )
    private GrupoProduto grupoProduto;


    public Produto() {
        this.preco = BigDecimal.ZERO;
    }

    public Produto(Long id, String nome, BigDecimal preco, Integer qtdEstoque, GrupoProduto grupoProduto) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.grupoProduto = grupoProduto;

    }

    public Produto(ProdutoDTO dto){
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.preco = dto.getPreco();
        this.qtdEstoque = dto.getQtdEstoque();
        this.grupoProduto = new GrupoProduto();
        this.grupoProduto.setId(dto.getGrupoProduto());
    }


    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public @NotNull String getNome() {
        return nome;
    }

    public void setNome( @NotNull String nome ) {
        this.nome = nome;
    }

    public @NotNull @Digits( integer = 15, fraction = 2 ) BigDecimal getPreco() {
        return preco;
    }

    public void setPreco( @NotNull @Digits( integer = 15, fraction = 2 ) BigDecimal preco ) {
        this.preco = preco;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque ) {
        this.qtdEstoque = qtdEstoque;
    }

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto( GrupoProduto grupoProduto ) {
        this.grupoProduto = grupoProduto;
    }


    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}