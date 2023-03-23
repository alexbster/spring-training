package com.company.demodata.business.impl;

import com.company.demodata.business.BuscadorClientes;
import com.company.demodata.dto.ClienteDto;
import com.company.demodata.dto.ClienteQueryDto;
import com.company.demodata.service.AdministradorClienteV2;
import com.company.demodata.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BuscadorClientesBdd")
public class BuscadorClientesBdd implements BuscadorClientes {

    @Qualifier("defaultNombresSingleton")
    @Autowired
    private AdministradorClienteV2 administradorClienteV2;
    /**
     * Este método obtiene la lista de clientes de la base de datos
     * @param queryDto contiene los parámetros de búsqueda
     * @return clientes que satisfacen la consulta
     */
    @Override
    public List<ClienteDto> obtieneListaClientes(ClienteQueryDto queryDto) {
        return administradorClienteV2.obtieneClientes(queryDto);
    }
}
