package com.company.demodata.config;

import com.company.demodata.dto.ClienteQueryType;
import com.company.demodata.repository.ClienteRepository;
import com.company.demodata.repository.CuentaRepository;
import com.company.demodata.service.AdministradorClienteV2;
import com.company.demodata.service.AdministradorCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

/**
 * @author aburgos
 * @since 2023-03-22
 * @version 1.0
 * @category Config
 * @implSpec Se retorna la instancia con los dos parámetros necesarios para el funcionamiento
 * a nivel del constructor
 */
@Configuration
public class BeanConfiguration {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Bean({"defaultCedula", "criteriaCedula"})
    public AdministradorClienteV2 administradorClienteV2ByCedula() {
        return new AdministradorClienteV2(clienteRepository, ClienteQueryType.CEDULA);
    }

    @Bean("defaultNombres")
    public AdministradorClienteV2 administradorClienteV2ByNombres() {
        return new AdministradorClienteV2(clienteRepository, ClienteQueryType.NOMBRES);
    }

    @Bean("defaultCedulaSingleton")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Lazy
    public AdministradorClienteV2 administradorClienteV2ByCedulaSingleton() {
        return new AdministradorClienteV2(clienteRepository, ClienteQueryType.CEDULA);
    }

    @Bean("defaultNombresSingleton")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Lazy
    public AdministradorClienteV2 administradorClienteV2ByNombresSingleton() {
        return new AdministradorClienteV2(clienteRepository, ClienteQueryType.NOMBRES);
    }

    @Bean("defaultCedulaPrototype")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AdministradorClienteV2 administradorClienteV2ByCedulaPrototype() {
        return new AdministradorClienteV2(clienteRepository, ClienteQueryType.CEDULA);
    }

    @Bean("defaultNombresPrototype")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AdministradorClienteV2 administradorClienteV2ByNombresPrototype() {
        return new AdministradorClienteV2(clienteRepository, ClienteQueryType.NOMBRES);
    }

    @Bean("defaultCedulaSession")
    @Scope(WebApplicationContext.SCOPE_SESSION)
    public AdministradorClienteV2 administradorClienteV2ByCedulaSession() {
        return new AdministradorClienteV2(clienteRepository, ClienteQueryType.CEDULA);
    }

    @Bean("defaultNombresSession")
    @SessionScope
    public AdministradorClienteV2 administradorClienteV2ByNombresSession() {
        return new AdministradorClienteV2(clienteRepository, ClienteQueryType.NOMBRES);
    }

    @Bean("defaultCedulaRequest")
    @Scope(WebApplicationContext.SCOPE_REQUEST)
    public AdministradorClienteV2 administradorClienteV2ByCedulaRequest() {
        return new AdministradorClienteV2(clienteRepository, ClienteQueryType.CEDULA);
    }

    @Bean("defaultNombresRequest")
    @RequestScope
    public AdministradorClienteV2 administradorClienteV2ByNombresRequest() {
        return new AdministradorClienteV2(clienteRepository, ClienteQueryType.NOMBRES);
    }
}
