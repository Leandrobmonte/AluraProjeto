package br.com.alura.comex.model.dto.input;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.validation.categoria.ValidateIdCategoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProdutoInputDto {

    @JsonIgnore
    private Long id;
    @NotNull @NotEmpty @Length(min = 2)
    private String nome;
    @NotNull @Positive
    private Double precoUnitario;
    private String descricao;
    @NotNull
    private Integer quantidadeEstoque;
    @NotNull @ValidateIdCategoria
    private Long categoriaId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public Produto converter(CategoriaRepository categoriaRepository) {
        Produto produto = new Produto();
        if(this.id != null){
            produto.setId(this.id);
        }
        Categoria categoria = categoriaRepository.findById(this.categoriaId).get();
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPrecoUnitario(new BigDecimal(this.precoUnitario));
        produto.setQuantidadeEstoque(this.quantidadeEstoque);
        produto.setCategoria(categoria);
        return produto;
    }

}
