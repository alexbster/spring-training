package com.company.demodata.business;

import com.company.demodata.dto.ClienteDto;
import com.company.demodata.dto.ClienteQueryDto;

import java.util.List;

public interface BuscadorClientes {
    List<ClienteDto> obtieneListaClientes(ClienteQueryDto queryDto);
}
