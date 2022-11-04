package br.com.alura.comex.service;

import br.com.alura.comex.config.exception.ExceptionEntidadeNaoEncontrada;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.dto.input.ClienteInputDto;
import br.com.alura.comex.model.dto.output.ClienteListaOutputDto;
import br.com.alura.comex.model.dto.output.ClienteNovoOutputDto;
import br.com.alura.comex.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private static final String MSG_CLIENTE_NAO_ENCONTRADO
            = "Não existe um cliente com o código %d";


    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> clientePorId(Long clienteId){
        return clienteRepository.findById(clienteId);
    }

    @Transactional
    public ClienteNovoOutputDto cadastrar(ClienteInputDto clienteInputDto) {
        Cliente cliente = clienteInputDto.converter();
        clienteRepository.save(cliente);
        return new ClienteNovoOutputDto(cliente);
    }

    public List<ClienteListaOutputDto> listar(Integer page) {

        Pageable pageable = PageRequest.of(page != null ? page: 0, 5, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Cliente> clientes = clienteRepository.findAll(pageable);
        return ClienteListaOutputDto.converter(clientes.getContent());

    }

    public Cliente buscarOuFalhar(Long clienteId){
        return clienteRepository
                .findById(clienteId)
                .orElseThrow(() ->
                        new ExceptionEntidadeNaoEncontrada(
                                String.format(MSG_CLIENTE_NAO_ENCONTRADO, clienteId)
                        )
                );
    }

    public ClienteNovoOutputDto buscaPorId(Long id) {
        return new ClienteNovoOutputDto(this.buscarOuFalhar(id));
    }

    @Transactional
    public void remover(Long id) {
        this.buscaPorId(id);
        clienteRepository.deleteById(id);
    }
}
