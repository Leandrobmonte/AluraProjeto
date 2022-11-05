package br.com.alura.comex.service;

import br.com.alura.comex.config.exception.ExceptionEntidadeNaoEncontrada;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.dto.input.ProdutoInputDto;
import br.com.alura.comex.model.dto.output.ProdutoOutputDto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {


    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    private static final String MSG_PRODUTO_NAO_ENCONTRADO
            = "Não existe um cliente com o código %d";

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public ProdutoOutputDto cadastrar(ProdutoInputDto produtoInputDto) {
        Produto produto = produtoInputDto.converter(categoriaRepository);
        produtoRepository.save(produto);
        return new ProdutoOutputDto(produto);
    }

    public List<ProdutoOutputDto> listar(Integer page) {
        Pageable pageable = PageRequest.of(page != null ? page: 0, 5, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Produto> produtos = produtoRepository.findAll(pageable);
        return ProdutoOutputDto.converter(produtos.getContent());
    }

    @Transactional
    public ProdutoOutputDto alterarProduto(Long id, ProdutoInputDto produtoInputDto) {
        this.buscarOuFalhar(id);
        produtoInputDto.setId(id);
        Produto produto = produtoInputDto.converter(categoriaRepository);
        produtoRepository.save(produto);
        return new ProdutoOutputDto(produto);
    }

    public Produto buscarOuFalhar(Long produtoId){
        return produtoRepository
                .findById(produtoId)
                .orElseThrow(() ->
                        new ExceptionEntidadeNaoEncontrada(
                                String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId)
                        )
                );
    }
}
