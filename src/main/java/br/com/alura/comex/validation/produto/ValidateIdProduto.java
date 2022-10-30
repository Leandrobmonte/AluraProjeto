package br.com.alura.comex.validation.produto;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ProdutoValidator.class)
public @interface ValidateIdProduto {

    public String message() default "Invalid ProdutoId: Not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
