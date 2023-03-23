package com.company.demodata.service;

import com.company.demodata.dto.*;
import com.company.demodata.model.Cliente;
import com.company.demodata.model.Cuenta;
import com.company.demodata.repository.ClienteRepository;
import com.company.demodata.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministradorCuenta {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<CuentaDto> obtieneCuentas(CuentaQueryDto cuentaQueryDto)
    {
        //Code challenge: usando solo cuentas activas (estado=true)
        List<Cuenta> cuentas = this.cuentaRepository
                                    .findCuentaByCliente_IdAndEstadoIsTrueAndNumeroContains(
                                            cuentaQueryDto.getClienteId()
                                            , cuentaQueryDto.getTextoBusqueda());
        return Optional.ofNullable(cuentas).map(cuentasAux-> cuentasAux.stream().map(cuenta -> {
            var cuentaDto = Helpers.fromSourceToTarget(cuenta, new CuentaDto());
            return cuentaDto;
        }).collect(Collectors.toList())).orElse(new ArrayList<>());
    }
}
