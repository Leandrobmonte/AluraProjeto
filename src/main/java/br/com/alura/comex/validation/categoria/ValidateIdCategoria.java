package br.com.alura.comex.validation.categoria;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CategoriaIdValidator.class)
public @interface ValidateIdCategoria {

    public String message() default "Invalid CategoriaId: Not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
