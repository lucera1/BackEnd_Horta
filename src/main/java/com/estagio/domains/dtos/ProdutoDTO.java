package com.estagio.domains.dtos;

import com.estagio.domains.produtos.Produto;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProdutoDTO {

    private Long id;

    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode estar vazio.")
    private String nome;

    @NotNull(message = "O campo não pode ser nulo")
    @Digits(integer = 15, fraction = 2)
    private BigDecimal preco;

    @NotNull(message = "O campo não pode ser nulo")
    private int qtdEstoque;

    @NotNull(message = "O campo não pode ser nulo")
    private Long grupoProduto;

    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode estar vazio.")
    private String descricao;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.qtdEstoque = produto.getQtdEstoque();
        this.grupoProduto = produto.getGrupoProduto().getId();
        this.descricao = produto.getGrupoProduto().getDescricao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull( message = "O campo não pode ser nulo" ) @NotBlank( message = "O campo não pode estar vazio." ) String getNome() {
        return nome;
    }

    public void setNome(@NotNull( message = "O campo não pode ser nulo" ) @NotBlank( message = "O campo não pode estar vazio." ) String nome) {
        this.nome = nome;
    }

    public @NotNull( message = "O campo não pode ser nulo" ) @Digits( integer = 15, fraction = 2 ) BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(@NotNull( message = "O campo não pode ser nulo" ) @Digits( integer = 15, fraction = 2 ) BigDecimal preco) {
        this.preco = preco;
    }

    @NotNull( message = "O campo não pode ser nulo" )
    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(@NotNull( message = "O campo não pode ser nulo" ) int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public @NotNull( message = "O campo não pode ser nulo" ) Long getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(@NotNull( message = "O campo não pode ser nulo" ) Long grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

    public @NotNull( message = "O campo não pode ser nulo" ) @NotBlank( message = "O campo não pode estar vazio." ) String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull( message = "O campo não pode ser nulo" ) @NotBlank( message = "O campo não pode estar vazio." ) String descricao) {
        this.descricao = descricao;
    }
}
