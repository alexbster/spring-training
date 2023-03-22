package com.company.demodata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    private int id;

    private String nombre;

    private String apellidos;

    private String cedula;

    private String telefono;

    private boolean estado;

    private String paisNacimiento;
}
