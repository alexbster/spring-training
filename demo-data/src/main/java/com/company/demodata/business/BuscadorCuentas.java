package com.company.demodata.business;

import com.company.demodata.dto.ClienteDto;
import com.company.demodata.dto.ClienteQueryDto;
import com.company.demodata.dto.CuentaDto;
import com.company.demodata.dto.CuentaQueryDto;

import java.util.List;

public interface BuscadorCuentas {
    List<CuentaDto> obtieneListaCuentasPorCliente(CuentaQueryDto queryDto);
}
