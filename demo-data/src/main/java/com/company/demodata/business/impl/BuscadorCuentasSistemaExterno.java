package com.company.demodata.business.impl;

import com.company.demodata.business.BuscadorCuentas;
import com.company.demodata.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("buscadorCuentasSistemaExterno")
public class BuscadorCuentasSistemaExterno implements BuscadorCuentas {

    @Override
    public List<CuentaDto> obtieneListaCuentasPorCliente(CuentaQueryDto queryDto) {
        var cuentas = List.of(
                new CuentaDto(1, "12345", "EFEC", true, 1),
                new CuentaDto(2, "123456", "EFEC", true, 1),
                new CuentaDto(3, "123457", "EFEC", true, 1),
                new CuentaDto(4, "12345678", "EFEC", true, 2),
                new CuentaDto(5, "12345679", "EFEC", true, 3),
                new CuentaDto(6, "12345670", "EFEC", true, 4)
        );
        return cuentas.stream().filter(filter ->
                        filter.getClienteId() == queryDto.getClienteId() &&
                        filter.getNumero().contains(queryDto.getTextoBusqueda()))
                .toList();
    }
}
