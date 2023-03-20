package com.company.demodata.dto;

import lombok.Data;

@Data
public class ClienteDto {

    private int id;

    private String nombre;

    private String apellidos;

    private String cedula;

    private String telefono;

    private boolean estado;

    private String paisNacimiento;
}
