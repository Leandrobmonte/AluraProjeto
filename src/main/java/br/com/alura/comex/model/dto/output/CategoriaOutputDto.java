package br.com.alura.comex.model.dto.output;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaOutputDto {

    private Long id;
    private String nome;
    private StatusCategoria status;

    public CategoriaOutputDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public StatusCategoria getStatus() {
        return status;
    }

    public static List<CategoriaOutputDto> converter(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaOutputDto::new).collect(Collectors.toList());
    }
}
