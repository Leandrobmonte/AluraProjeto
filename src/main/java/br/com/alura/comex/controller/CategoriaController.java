package br.com.alura.comex.controller;

import br.com.alura.comex.model.dto.input.CategoriaInputDto;
import br.com.alura.comex.model.dto.input.CategoriaUpdateInputDto;
import br.com.alura.comex.model.dto.output.CategoriaOutputDto;
import br.com.alura.comex.model.dto.projecao.PedidoProjecao;
import br.com.alura.comex.service.CategoriaService;
import br.com.alura.comex.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<CategoriaOutputDto> listarTodos(){
        return categoriaService.listar();
    }

    @GetMapping("/{id}")
    public CategoriaOutputDto buscarPorId(@PathVariable Long id){
        return categoriaService.buscaPorId(id);
    }

    @PostMapping
    @CacheEvict(value = "listaCategoriasPedidos", allEntries = true)
    public ResponseEntity<CategoriaOutputDto> inserir(@RequestBody @Valid CategoriaInputDto categoriaInputDto, UriComponentsBuilder uriBuilder){
        CategoriaOutputDto categoriaOutputDto = categoriaService.cadastrar(categoriaInputDto);
        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoriaOutputDto.getId()).toUri();
        return ResponseEntity.created(uri).body(categoriaOutputDto);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "listaCategoriasPedidos", allEntries = true)
    public ResponseEntity<CategoriaOutputDto> atualizar(@PathVariable Long id, @RequestBody @Valid CategoriaUpdateInputDto categoriaUpdateInputDto){
        return ResponseEntity.ok(categoriaService.atualizar(id,categoriaUpdateInputDto));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaCategoriasPedidos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id){
        categoriaService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pedidos")
    @Cacheable(value = "listaCategoriasPedidos")
    public List<PedidoProjecao> relatorioPedidosPorCategoria(){
        return  pedidoService.pedidosPorCategoria();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> ativarDesativarCategoria(@PathVariable Long id){
        return ResponseEntity.ok().body(categoriaService.ativaDesativaCategoria(id));
    }


}
