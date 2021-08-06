package com.ecommerce.rodolpho.shared;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class OptionalIdExistsValidator implements ConstraintValidator<OptionalIdExists, Long> {
    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void initialize(OptionalIdExists params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("select 1 from " + klass.getName()
                + " where " + domainAttribute + " = :value");
        query.setParameter("value", value);

        List<?> list = query.getResultList();
        Assert.state(list.size() <=1, "");

        return !list.isEmpty();
    }
}
