package br.com.alura.comex.model.dto.input;

import br.com.alura.comex.model.Cliente;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClienteInputDto {

    @NotNull @NotEmpty @Length(min = 2)
    private String nome;
    @NotNull @NotEmpty @CPF
    private String cpf;
    @NotNull @NotEmpty
    private String telefone;
    @NotNull @NotEmpty
    private String rua;
    @NotNull @NotEmpty
    private String numero;
    private String complemento;
    @NotNull @NotEmpty
    private String bairro;
    @NotNull @NotEmpty
    private String cidade;
    @NotNull @NotEmpty
    private String estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente converter() {
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setBairro(this.bairro);
        cliente.setComplemento(this.complemento);
        cliente.setCpf(this.cpf);
        cliente.setEstado(this.estado);
        cliente.setCidade(this.cidade);
        cliente.setNumero(this.numero);
        cliente.setRua(this.rua);
        cliente.setTelefone(this.telefone);
        return cliente;
    }

}
