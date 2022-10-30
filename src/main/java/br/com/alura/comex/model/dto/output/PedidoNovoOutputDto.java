package br.com.alura.comex.model.dto.output;

import br.com.alura.comex.model.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoNovoOutputDto {

    @JsonIgnore
    private Long id;
    private LocalDate data;
    private String clienteNome;
    private BigDecimal desconto;

    public PedidoNovoOutputDto(Pedido pedido) {
        this.id = pedido.getId();
        this.data = pedido.getData();
        this.clienteNome = pedido.getCliente().getNome();
        this.desconto = pedido.getDesconto();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

}
