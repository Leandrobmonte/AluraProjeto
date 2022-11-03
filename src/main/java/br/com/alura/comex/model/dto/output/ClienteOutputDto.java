package br.com.alura.comex.model.dto.output;

import br.com.alura.comex.model.Cliente;

public class ClienteOutputDto {
    private Long id;
    private String nome;

    public ClienteOutputDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
