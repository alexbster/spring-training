package com.company.demodata.criteria;

import com.company.demodata.dto.ClienteDto;
import com.company.demodata.dto.CuentaDto;
import com.company.demodata.model.Cliente;
import com.company.demodata.model.Cuenta;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.javapoet.TypeName;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component
public class CuentaSpecification {

    public <T> Specification<T> equals(String fieldName, String fieldValue) {
        return fieldValue == null ? null :
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get(fieldName), fieldValue);
    }

    public <T> Specification<T> equals(String fieldName, Boolean fieldValue) {
        return fieldValue == null ? null :
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get(fieldName), fieldValue);
    }

    public <T> Specification<T> equals(String fieldName, int fieldValue) {
        return fieldValue == 0 ? null :
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get(fieldName), fieldValue);
    }

    public static <T> Specification<T> like(String fieldName, String fieldValue) {
        if (fieldValue != null) {
            String uppercaseValue = MessageFormat.format("%{0}%", fieldValue.trim().toUpperCase(Locale.ROOT)).replaceAll(" ", "%");
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get(fieldName)), uppercaseValue);
        } else {
            return null;
        }
    }

    private Specification<Cuenta> numeroCriteria(CuentaDto cuentaDto)
    {
        return equals("numero", cuentaDto.getNumero());
    }

    private Specification<Cuenta> tipoCriteria(CuentaDto cuentaDto)
    {
        return equals("tipo", cuentaDto.getTipo());
    }

    private Specification<Cuenta> estadoCriteria(CuentaDto cuentaDto)
    {
        return equals("estado", cuentaDto.isEstado());
    }

    private Specification<Cuenta> clienteIdCriteria(CuentaDto cuentaDto)
    {
        return equals("cliente", cuentaDto.getClienteId());
    }

    public Specification<Cuenta> buildFilter(CuentaDto cuentaDto)
    {
        return Specification.where(numeroCriteria(cuentaDto))
                .and(tipoCriteria(cuentaDto))
                .and(estadoCriteria(cuentaDto)
                .and(clienteIdCriteria(cuentaDto)));
    }
}
