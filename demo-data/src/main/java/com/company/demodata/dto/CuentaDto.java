package com.company.demodata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDto {
    private int id;

    private String numero;

    private String tipo;

    private boolean estado;

    private int clienteId;

}
