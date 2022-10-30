package br.com.alura.comex.model.dto.input;

import br.com.alura.comex.model.Categoria;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaInputDto {

    @NotNull @NotEmpty @Length(min = 2)
    private String nome;

    public Categoria converter() {
        Categoria categoria = new Categoria();
        categoria.setNome(this.nome);
        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
