package br.com.alura.comex.controller;

import br.com.alura.comex.model.dto.input.ClienteInputDto;
import br.com.alura.comex.model.dto.output.ClienteListaOutputDto;
import br.com.alura.comex.model.dto.output.ClienteNovoOutputDto;
import br.com.alura.comex.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteListaOutputDto> listarTodos(@RequestParam(value = "page", required = false) Integer page){
        return clienteService.listar(page);
    }

    @PostMapping
    public ResponseEntity<ClienteNovoOutputDto> inserir(@RequestBody @Valid ClienteInputDto clienteInputDto, UriComponentsBuilder uriBuilder){
        ClienteNovoOutputDto clienteNovoOutputDto = clienteService.cadastrar(clienteInputDto);
        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteNovoOutputDto.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteNovoOutputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        clienteService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
