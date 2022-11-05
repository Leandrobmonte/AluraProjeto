package br.com.alura.comex.model.dto.output;

import br.com.alura.comex.model.Produto;
import br.com.alura.comex.validation.categoria.ValidateIdCategoria;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoOutputDto {

    @JsonIgnore
    private Long id;
    private String nome;
    private BigDecimal precoUnitario;
    private String descricao;
    private Integer quantidadeEstoque;
    @ValidateIdCategoria
    private Long categoriaId;
    private String categoriaNome;
    public ProdutoOutputDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.precoUnitario = produto.getPrecoUnitario().setScale(2, RoundingMode.DOWN);
        this.descricao = produto.getDescricao();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.categoriaId = produto.getCategoria().getId();
        this.categoriaNome = produto.getCategoria().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public static List<ProdutoOutputDto> converter(List<Produto> produtos) {
        return produtos.stream().map(ProdutoOutputDto::new).collect(Collectors.toList());
    }
}
