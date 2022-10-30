package br.com.alura.comex.model.dto.input;

import br.com.alura.comex.validation.produto.ValidateIdProduto;

public class ItemPedidoDto {

    @ValidateIdProduto
    private Long produtoId;
    private Integer quantidadeVendida;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(Integer quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }
}
