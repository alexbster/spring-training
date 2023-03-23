package com.company.demodata.business;

import com.company.demodata.dto.ClienteQueryDto;
import com.company.demodata.dto.ClienteQueryType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BuscadorClientesTest {

    @Autowired
    @Qualifier("BuscadorClientesBdd")
    private BuscadorClientes buscadorClientesBdd;

    @Autowired
    @Qualifier("BuscadorClientesSistemaExterno")
    private BuscadorClientes buscadorClientesSistemaExterno;

    @Test
    void obtieneListaClientesBdd() {
        var criteria = new ClienteQueryDto();
        criteria.setTipoBusqueda(ClienteQueryType.NOMBRES);
        criteria.setTextoBusqueda("JOHN");
        var clientes = buscadorClientesBdd.obtieneListaClientes(criteria);
        Assert.isTrue(clientes.size() == 2, "Se esperaban 2 clientes");
    }

    @Test
    void obtieneListaClientesSistemExterno() {
        var criteria = new ClienteQueryDto();
        criteria.setTipoBusqueda(ClienteQueryType.NOMBRES);
        criteria.setTextoBusqueda("Ros");
        var clientes = buscadorClientesSistemaExterno.obtieneListaClientes(criteria);
        Assert.isTrue(clientes.size() == 1, "Se esperaban 10 clientes");
    }

    @Test
    void obtieneListaClientesUsandoCedulaSistemExterno() {
        var criteria = new ClienteQueryDto();
        criteria.setTipoBusqueda(ClienteQueryType.CEDULA);
        criteria.setTextoBusqueda("1890000005");
        var clientes = buscadorClientesSistemaExterno.obtieneListaClientes(criteria);
        Assert.isTrue(clientes.size() == 1, "Se esperaban 10 clientes");
    }
}