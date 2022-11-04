package br.com.alura.comex.model.dto.output;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;

import java.math.BigDecimal;

public class ItemDoPedidoOutputDto {

    private Long id;
    private Long produtoId;
    private String nomeProduto;
    private String nomeCategoria;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal valorPago;
    private BigDecimal desconto;

    public ItemDoPedidoOutputDto(Categoria categoria, Produto produto) {
        this.id = id;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.nomeCategoria = nomeCategoria;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.valorPago = valorPago;
        this.desconto = desconto;
    }

    public Long getId() {
        return id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }
}
