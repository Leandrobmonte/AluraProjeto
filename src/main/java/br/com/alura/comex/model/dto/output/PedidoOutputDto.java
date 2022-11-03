package br.com.alura.comex.model.dto.output;

import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.repository.ProdutoRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PedidoOutputDto {

    @JsonIgnore
    private Long id;
    private LocalDate dataPedido;
    private BigDecimal valorPedido;
    private BigDecimal desconto;
    private Integer qtdProdutosVendidos;
    private ClienteOutputDto cliente;

    public PedidoOutputDto(Pedido pedido) {
        this.id = pedido.getId();
        this.dataPedido = pedido.getData();
        this.cliente = new ClienteOutputDto(pedido.getCliente());
        this.qtdProdutosVendidos = somaDeQtdDoItemPedidos(pedido.getItens());
        this.desconto = somaDescontosItensPedido(pedido.getItens());
        this.valorPedido = somaTotalPedido(pedido.getItens());
    }

    public static Page<PedidoOutputDto> converter(Page<Pedido> pedidos) {
        return pedidos.map(PedidoOutputDto::new);
    }

    private BigDecimal somaDescontosItensPedido(List<ItemDePedido> itens) {
        BigDecimal valorDescontoTotal= itens.stream().map(item->item.getDesconto())
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        return  valorDescontoTotal;
    }

    private BigDecimal somaTotalPedido(List<ItemDePedido> itens) {
        BigDecimal valorEfetivo= itens.stream().map(item->item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        return  valorEfetivo;
    }

    private Integer somaDeQtdDoItemPedidos(List<ItemDePedido> itens) {
        Integer somaDeItens = itens.stream().mapToInt(i -> i.getQuantidade()).sum();
        return somaDeItens;
    }

    private List<BigDecimal> somaDeQtdDePedidos(List<ItemDePedido> itens) {
        List<BigDecimal> produtosVendidos = new ArrayList<>();
        for (ItemDePedido itemDePedido : itens){
            BigDecimal soma = itemDePedido.getPrecoUnitario().multiply(new BigDecimal(itemDePedido.getQuantidade()));
            produtosVendidos.add(soma);
        }
        return produtosVendidos;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public BigDecimal getValorPedido() {
        return valorPedido;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public Integer getQtdProdutosVendidos() {
        return qtdProdutosVendidos;
    }

    public ClienteOutputDto getCliente() {
        return cliente;
    }
}
