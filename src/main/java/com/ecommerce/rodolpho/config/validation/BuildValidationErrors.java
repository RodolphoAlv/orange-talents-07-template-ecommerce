package com.ecommerce.rodolpho.config.validation;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public class BuildValidationErrors {
    private List<ObjectError> globalErrors;
    private List<ErroForm> fieldErrors;
    private Integer numberOfErrors;

    public BuildValidationErrors(List<ObjectError> globalErrors, List<ErroForm> fieldErrors) {
        this.globalErrors = globalErrors;
        this.fieldErrors = fieldErrors;
        this.numberOfErrors = fieldErrors == null? 0 : fieldErrors.size();
    }

    public static BuildValidationErrors builder(List<ObjectError> globalErrors, List<FieldError> fieldErrors) {
        List<ErroForm> errors = fieldErrors.stream()
                .map(e -> new ErroForm(e.getField(), e.getDefaultMessage()))
                .collect(Collectors.toList());

        return new BuildValidationErrors(globalErrors, errors);
    }

    public List<ObjectError> getGlobalErrors() {
        return globalErrors;
    }

    public List<ErroForm> getFieldErrors() {
        return fieldErrors;
    }

    public Integer getNumberOfErrors() {
        return numberOfErrors;
    }
}
