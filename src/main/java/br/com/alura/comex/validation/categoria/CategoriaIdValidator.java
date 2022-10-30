package br.com.alura.comex.validation.categoria;

import br.com.alura.comex.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoriaIdValidator implements ConstraintValidator<ValidateIdCategoria, Long> {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public boolean isValid(Long categoriaId, ConstraintValidatorContext constraintValidatorContext) {
        return categoriaRepository.findById(categoriaId).isPresent();
    }
}
