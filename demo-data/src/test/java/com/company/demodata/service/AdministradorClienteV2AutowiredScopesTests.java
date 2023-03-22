package com.company.demodata.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author aburgos
 * @since 2023-03-22
 * @version 1.0
 * @category Test
 * @implSpec Test de la clase AdministradorClienteV2 donde se inyecta el repositorio de clientes
 * por medio del constructor y una default a través del constructor también
 */
@SpringBootTest
@Slf4j
public class AdministradorClienteV2AutowiredScopesTests {

    @Autowired
    private ClienteService clienteService;

    @Qualifier("defaultNombresSingleton")
    @Autowired
    private AdministradorClienteV2 administradorClienteV2ByNamesSingleton1;

    @Qualifier("defaultNombresSingleton")
    @Autowired
    private AdministradorClienteV2 administradorClienteV2ByNamesSingleton2;

    @Qualifier("defaultNombresPrototype")
    @Autowired
    private AdministradorClienteV2 administradorClienteV2ByNamesPrototype1;

    @Qualifier("defaultNombresPrototype")
    @Autowired
    private AdministradorClienteV2 administradorClienteV2ByNamesPrototype2;

    @Qualifier("defaultNombresSession")
    @Autowired
    private AdministradorClienteV2 administradorClienteV2ByNamesSession1;

    @Qualifier("defaultNombresSession")
    @Autowired
    private AdministradorClienteV2 administradorClienteV2ByNamesSession2;

    @Qualifier("defaultNombresRequest")
    @Autowired
    private AdministradorClienteV2 administradorClienteV2ByNamesRequest1;

    @Qualifier("defaultNombresRequest")
    @Autowired
    private AdministradorClienteV2 administradorClienteV2ByNamesRequest2;


    @Test
    void instancias() {

        System.out.println("singleton1 " + administradorClienteV2ByNamesSingleton1);
        System.out.println("singleton2 " + administradorClienteV2ByNamesSingleton2);
        System.out.println("prototype1 " + administradorClienteV2ByNamesPrototype1);
        System.out.println("prototype2 " + administradorClienteV2ByNamesPrototype2);
        System.out.println("session1 " + administradorClienteV2ByNamesSession1);
        System.out.println("session2 " + administradorClienteV2ByNamesSession2);
        System.out.println("request1 " + administradorClienteV2ByNamesRequest1);
        System.out.println("request2 " + administradorClienteV2ByNamesRequest2);

        Assertions.assertEquals(administradorClienteV2ByNamesSingleton1.hashCode(), administradorClienteV2ByNamesSingleton1.hashCode());
        Assertions.assertNotSame(administradorClienteV2ByNamesPrototype1.hashCode(), administradorClienteV2ByNamesPrototype2.hashCode());
        Assertions.assertEquals(administradorClienteV2ByNamesSession1.hashCode(), administradorClienteV2ByNamesSession2.hashCode());
        Assertions.assertEquals(administradorClienteV2ByNamesRequest1.hashCode(), administradorClienteV2ByNamesRequest2.hashCode());
    }
}
