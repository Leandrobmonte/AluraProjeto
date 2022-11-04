package br.com.alura.comex.model.dto.output;

import br.com.alura.comex.model.Pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoDetalheOutputDto {

    private LocalDate dataDoPedido;
    private BigDecimal valor;
    private BigDecimal descontos;
    private List<ItemDoPedidoOutputDto> itensDePedido;
    private ClienteOutputDto cliente;

    public PedidoDetalheOutputDto(Pedido pedido) {
        this.dataDoPedido = pedido.getData();
        this.valor = valor;
        this.descontos = descontos;
        this.itensDePedido = itensDePedido;
        this.cliente = cliente;
    }

    public LocalDate getDataDoPedido() {
        return dataDoPedido;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public BigDecimal getDescontos() {
        return descontos;
    }

    public List<ItemDoPedidoOutputDto> getItensDePedido() {
        return itensDePedido;
    }

    public ClienteOutputDto getCliente() {
        return cliente;
    }
}
