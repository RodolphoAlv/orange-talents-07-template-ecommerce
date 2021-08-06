package com.ecommerce.rodolpho.shared;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Null;
import java.lang.annotation.*;

@Documented
@Null(message = "{categoria.sem.mae}")
@Constraint(validatedBy = {OptionalIdExistsValidator.class})
@ConstraintComposition(CompositionType.OR)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionalIdExists {

    String message() default "{}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String fieldName();
    Class<?> domainClass();
}
