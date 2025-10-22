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

    // ❌ Removemos a validação do front
    private String descricao;

    public ProdutoDTO() {
    }

    // Construtor para retornar produto para o front (read-only)
    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.qtdEstoque = produto.getQtdEstoque();
        this.grupoProduto = produto.getGrupoProduto().getId();
        this.descricao = produto.getGrupoProduto().getDescricao(); // só para leitura
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Long getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(Long grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
