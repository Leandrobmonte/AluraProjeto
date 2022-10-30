package br.com.alura.comex.validation.produto;

import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProdutoValidator implements ConstraintValidator<ValidateIdProduto, Long> {

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public boolean isValid(Long produtoId, ConstraintValidatorContext constraintValidatorContext) {
        return produtoRepository.findById(produtoId).isPresent();
    }
}
