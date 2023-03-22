package com.company.demodata.dto;

import lombok.Data;

@Data
public class ClienteQueryDto {
    private String textoBusqueda;
    private ClienteQueryType tipoBusqueda;
}
