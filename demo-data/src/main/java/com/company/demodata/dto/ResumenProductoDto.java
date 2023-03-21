package com.company.demodata.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResumenProductoDto {
    private List<CuentaDto> cuentas = new ArrayList<>();
    private List<TarjetaDto> tarjetas = new ArrayList<>();
    private List<InversionDto> inversiones = new ArrayList<>();
}
