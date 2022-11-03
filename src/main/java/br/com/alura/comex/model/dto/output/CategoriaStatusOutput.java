package br.com.alura.comex.model.dto.output;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;

public class CategoriaStatusOutput {

    private StatusCategoria statusCategoria;

    public CategoriaStatusOutput(Categoria categoria) {
        this.statusCategoria = categoria.getStatus();
    }

    public StatusCategoria getStatusCategoria() {
        return statusCategoria;
    }
}
