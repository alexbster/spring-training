package com.company.demodata.service;

import com.company.demodata.dto.ClienteDto;
import com.company.demodata.dto.CuentaDto;
import com.company.demodata.dto.InversionDto;
import com.company.demodata.dto.TarjetaDto;
import com.company.demodata.model.Cliente;
import com.company.demodata.model.Cuenta;
import com.company.demodata.model.Inversion;
import com.company.demodata.model.Tarjeta;
import org.springframework.beans.BeanUtils;

public class Helpers {

    public static ClienteDto fromClienteToDto(Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteDto);
        return clienteDto;
    }
    public static CuentaDto fromCuentaToDto(Cuenta cuenta){
        CuentaDto cuentaDto = new CuentaDto();
        BeanUtils.copyProperties(cuenta, cuentaDto);
        return cuentaDto;
    }
    public static InversionDto fromInversionToDto(Inversion inversion){
        InversionDto inversionDto = new InversionDto();
        BeanUtils.copyProperties(inversion, inversionDto);
        return inversionDto;
    }

    public static TarjetaDto fromTarjetaToDto(Tarjeta tarjeta){
        TarjetaDto tarjetaDto = new TarjetaDto();
        BeanUtils.copyProperties(tarjeta, tarjetaDto);
        return tarjetaDto;
    }
}
