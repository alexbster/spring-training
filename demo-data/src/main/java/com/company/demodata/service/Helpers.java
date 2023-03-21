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
    public static <S,T> T fromSourceToTarget(S origen, T destino){
        BeanUtils.copyProperties(origen, destino);
        return destino;
    }
}
