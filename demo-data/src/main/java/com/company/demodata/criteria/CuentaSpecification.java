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
public class CuentaSpecification extends SpecificationBase {

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
