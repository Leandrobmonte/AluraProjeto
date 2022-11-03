package br.com.alura.comex.controller;

import br.com.alura.comex.model.dto.input.ProdutoInputDto;
import br.com.alura.comex.model.dto.output.ProdutoOutputDto;
import br.com.alura.comex.service.ProdutoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping()
    public List<ProdutoOutputDto> listar( @RequestParam(value = "page", required = false) Integer page){
        return produtoService.listar(page);
    }

    @PostMapping
    @CacheEvict(value = "listaCategoriasPedidos", allEntries = true)
    public ResponseEntity<ProdutoOutputDto> inserir(@RequestBody @Valid ProdutoInputDto produtoInputDto, UriComponentsBuilder uriBuilder){
        ProdutoOutputDto produtoOutputDto =produtoService.cadastrar(produtoInputDto);
        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoOutputDto.getId()).toUri();
        return ResponseEntity.created(uri).body(produtoOutputDto);
    }

}
