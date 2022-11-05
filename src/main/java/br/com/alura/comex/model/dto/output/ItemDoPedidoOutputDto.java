package br.com.alura.comex.model.dto.output;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ItemDoPedidoOutputDto {

    private Long id;
    private Long produtoId;
    private String nomeProduto;
    private String nomeCategoria;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal valorPago;
    private BigDecimal desconto;

    public ItemDoPedidoOutputDto(ItemDePedido itemDePedido) {
        this.id = itemDePedido.getId();
        this.produtoId = itemDePedido.getProdutoId().getId();
        this.nomeProduto = itemDePedido.getProdutoId().getNome();
        this.nomeCategoria = itemDePedido.getProdutoId().getCategoria().getNome();
        this.quantidade = itemDePedido.getQuantidade();
        this.precoUnitario = itemDePedido.getPrecoUnitario();
        this.valorPago = somaPagamentoComDesconto(itemDePedido);
        this.desconto = itemDePedido.getDesconto();
    }

    private BigDecimal somaPagamentoComDesconto(ItemDePedido item){
        return item.getPrecoUnitario().subtract(item.getDesconto());
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

    public static List<ItemDoPedidoOutputDto> converter(List<ItemDePedido> itens){
        return itens.stream().map(ItemDoPedidoOutputDto::new).collect(Collectors.toList());
    }

}
