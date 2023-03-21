package com.company.demodata.criteria;

import org.springframework.data.jpa.domain.Specification;

import java.text.MessageFormat;
import java.util.Locale;

public abstract class SpecificationBase {

    public <T> Specification<T> equals(String fieldName, String fieldValue) {
        return fieldValue == null ? null :
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get(fieldName), fieldValue);
    }

    public <T> Specification<T> like(String fieldName, String fieldValue) {
        if (fieldValue != null) {
            String uppercaseValue = MessageFormat.format("%{0}%", fieldValue.trim().toUpperCase(Locale.ROOT)).replaceAll(" ", "%");
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get(fieldName)), uppercaseValue);
        } else {
            return null;
        }
    }

    public <T> Specification<T> equals(String fieldName, Boolean fieldValue) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(fieldName), fieldValue);
    }

    public <T> Specification<T> equals(String fieldName, int fieldValue) {
        return fieldValue == 0 ? null :
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get(fieldName), fieldValue);
    }
}
