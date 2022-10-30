package br.com.alura.comex.service;

import br.com.alura.comex.config.ExceptionEntidadeNaoEncontrada;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.dto.input.CategoriaInputDto;
import br.com.alura.comex.model.dto.input.CategoriaUpdateInputDto;
import br.com.alura.comex.model.dto.output.CategoriaOutputDto;
import br.com.alura.comex.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    private static final String MSG_CATEGORIA_NAO_ENCONTRADA
            = "Não existe um cadastro de categoria com o código %d";

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaOutputDto> listar(){
        Integer page = 0;
        Pageable pageable = PageRequest.of(page,5, Sort.unsorted());
        List<Categoria> categorias = categoriaRepository.findAll();
        return CategoriaOutputDto.converter(categorias);
    }

    @Transactional
    public CategoriaOutputDto cadastrar(CategoriaInputDto categoriaInputDto){
        Categoria categoria = categoriaInputDto.converter();
        categoriaRepository.save(categoria);
        return new CategoriaOutputDto(categoria);
    }

    @Transactional
    public CategoriaOutputDto atualizar(@PathVariable Long id, @RequestBody @Valid CategoriaUpdateInputDto form){
        Categoria categoriaAtual = this.buscarOuFalhar(id);
        BeanUtils.copyProperties(form.atualizar(), categoriaAtual, "id");
        categoriaRepository.save(categoriaAtual);
        return new CategoriaOutputDto(categoriaAtual);
    }

    public Categoria buscarOuFalhar(Long categoriaId){
        return categoriaRepository
                .findById(categoriaId)
                .orElseThrow(() ->
                        new ExceptionEntidadeNaoEncontrada(
                                String.format(MSG_CATEGORIA_NAO_ENCONTRADA, categoriaId)
                        )
                );
    }

    public CategoriaOutputDto buscaPorId(Long id) {
        return  new CategoriaOutputDto(this.buscarOuFalhar(id));
    }

    @Transactional
    public void remover(Long id) {
        this.buscaPorId(id);
        categoriaRepository.deleteById(id);
    }
}
