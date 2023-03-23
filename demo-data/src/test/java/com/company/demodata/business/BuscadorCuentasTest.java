package com.company.demodata.business;

import com.company.demodata.dto.CuentaQueryDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@Slf4j
class BuscadorCuentasTest {

    @Autowired
    @Qualifier("buscadorCuentasBdd")
    private BuscadorCuentas buscadorCuentasBdd;
    @Autowired
    @Qualifier("buscadorCuentasSistemaExterno")
    private BuscadorCuentas buscadorCuentasSistemaExterno;


    @Test
    void obtieneListaCuentasBdd() {
        var criteria = new CuentaQueryDto();
        criteria.setTextoBusqueda("456");
        criteria.setClienteId(1);
        var cuentas = buscadorCuentasBdd.obtieneListaCuentasPorCliente(criteria);
        Assert.isTrue(cuentas.size() == 1, "Se esperaban 1 cuentas");
    }

    @Test
    void obtieneListaCuentasSistemExterno() {
        var criteria = new CuentaQueryDto();
        criteria.setTextoBusqueda("123456");
        criteria.setClienteId(1);
        var cuentas = buscadorCuentasSistemaExterno.obtieneListaCuentasPorCliente(criteria);
        Assert.isTrue(cuentas.size() == 1, "Se esperaban 1 cuentas");
    }
}