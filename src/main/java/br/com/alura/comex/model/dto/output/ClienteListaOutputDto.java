package br.com.alura.comex.model.dto.output;

import br.com.alura.comex.model.Cliente;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteListaOutputDto {
    private String nome;
    private String cpf;
    private String telefone;
    private String local;

    public ClienteListaOutputDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.local = StringUtils.capitalize(cliente.getCidade())+ "/" + cliente.getEstado().toUpperCase();
    }

    public static List<ClienteListaOutputDto> converter(List<Cliente> clientes) {
        return clientes.stream().map(ClienteListaOutputDto::new).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getLocal() {
        return local;
    }
}
