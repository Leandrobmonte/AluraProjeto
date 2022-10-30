package br.com.alura.comex.controller;

import br.com.alura.comex.model.dto.input.PedidoInputDto;
import br.com.alura.comex.model.dto.output.PedidoNovoOutputDto;
import br.com.alura.comex.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoNovoOutputDto> inserir(@RequestBody @Valid PedidoInputDto pedidoInputDto, UriComponentsBuilder uriBuilder){
        PedidoNovoOutputDto pedidoNovoOutputDto = pedidoService.cadastrar(pedidoInputDto);
        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoNovoOutputDto.getId()).toUri();
        return ResponseEntity.created(uri).body(pedidoNovoOutputDto);
    }


}
