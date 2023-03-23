package com.company.demodata.business.impl;

import com.company.demodata.business.BuscadorClientes;
import com.company.demodata.business.BuscadorCuentas;
import com.company.demodata.dto.ClienteDto;
import com.company.demodata.dto.ClienteQueryDto;
import com.company.demodata.dto.CuentaDto;
import com.company.demodata.dto.CuentaQueryDto;
import com.company.demodata.service.AdministradorClienteV2;
import com.company.demodata.service.AdministradorCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("buscadorCuentasBdd")
public class BuscadorCuentasBdd implements BuscadorCuentas {

    @Autowired
    private AdministradorCuenta administradorCuenta;

    @Override
    public List<CuentaDto> obtieneListaCuentasPorCliente(CuentaQueryDto queryDto) {
        return administradorCuenta.obtieneCuentas(queryDto);
    }
}
