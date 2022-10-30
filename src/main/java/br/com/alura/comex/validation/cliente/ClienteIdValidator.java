package br.com.alura.comex.validation.cliente;

import br.com.alura.comex.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteIdValidator implements ConstraintValidator<ValidateIdCliente, Long> {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public boolean isValid(Long clienteId, ConstraintValidatorContext constraintValidatorContext) {
        return clienteRepository.findById(clienteId).isPresent();
    }
}
