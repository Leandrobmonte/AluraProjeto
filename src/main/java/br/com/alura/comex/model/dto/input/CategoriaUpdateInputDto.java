package br.com.alura.comex.model.dto.input;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaUpdateInputDto {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String nome;

    @NotNull
    @NotEmpty
    private String status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Categoria atualizar() {
        Categoria categoria = new Categoria();
        categoria.setNome(this.nome);
        categoria.setStatus(this.status.toUpperCase().equals(StatusCategoria.ATIVA.toString()) ?
                StatusCategoria.ATIVA:
                this.status.toUpperCase().equals(StatusCategoria.INATIVA.toString()) ?
                        StatusCategoria.INATIVA: null);
        return categoria;
    }
}
