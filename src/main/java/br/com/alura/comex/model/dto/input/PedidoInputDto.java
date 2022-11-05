package br.com.alura.comex.model.dto.input;

import br.com.alura.comex.validation.cliente.ValidateIdCliente;

import javax.validation.Valid;
import java.util.List;

public class PedidoInputDto {

    @ValidateIdCliente
    private Long clienteId;
    @Valid
    private List<ItemPedidoDto> itemPedidos;


    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemPedidoDto> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(List<ItemPedidoDto> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }
}
