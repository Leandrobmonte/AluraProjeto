package br.com.alura.comex.controller;

import br.com.alura.comex.model.dto.input.PedidoInputDto;
import br.com.alura.comex.model.dto.output.PedidoNovoOutputDto;
import br.com.alura.comex.model.dto.output.PedidoOutputDto;
import br.com.alura.comex.service.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @GetMapping
    public Page<PedidoOutputDto> lista( @PageableDefault(sort="data",direction = Sort.Direction.DESC, page = 0, size = 5) Pageable paginacao) {
        return pedidoService.listaPedidos(paginacao);
    }
    @PostMapping
    public ResponseEntity<PedidoNovoOutputDto> inserir(@RequestBody @Valid PedidoInputDto pedidoInputDto, UriComponentsBuilder uriBuilder){
        PedidoNovoOutputDto pedidoNovoOutputDto = pedidoService.cadastrar(pedidoInputDto);
        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoNovoOutputDto.getId()).toUri();
        return ResponseEntity.created(uri).body(pedidoNovoOutputDto);
    }


}
