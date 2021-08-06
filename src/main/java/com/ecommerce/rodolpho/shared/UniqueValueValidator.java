package com.ecommerce.rodolpho.shared;

import org.springframework.util.Assert;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private static final String NOT_VALID_FIELD = "Foi encontrado mais de um %s com o atributo %s = %s";

    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue params) {
        this.domainAttribute = params.fieldName();
        this.klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager
                .createQuery("select 1 from " + klass.getName() +
                            " where " + domainAttribute + " = :value");

        query.setParameter("value", value);

        List<?> list = query.getResultList();
        if(list.isEmpty()) return true;

        Assert.state(
                list.size() <=1,
                String.format(NOT_VALID_FIELD, klass, domainAttribute, value)
        );

        return list.isEmpty();
    }
}
