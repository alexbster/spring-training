package com.company.demodata.service;

import com.company.demodata.dto.ClienteDto;
import com.company.demodata.dto.ClienteQueryDto;
import com.company.demodata.dto.ClienteQueryType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;


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
public class AdministradorClienteV2AutowiredTests {

    @Autowired
    private ClienteService clienteService;

    @Qualifier("defaultNombres")
    @Autowired
    private AdministradorClienteV2 administradorClienteV2ByNames;

    @Autowired
    private AdministradorClienteV2 defaultCedula;


    @BeforeEach
    void setupClientes()
    {
        var clientes = List.of(
                new ClienteDto(1, "Alberto", "Salazar", "1890000000", "0999714563", true, "CR"),
                new ClienteDto(2, "Rosa", "Salazar", "1890000001", "0983475616", true, "CR"),
                new ClienteDto(3, "Alexis", "Vivanco", "1890000002", "0983475616", true, "CR"),
                new ClienteDto(4, "Natalie", "Vivanco", "1890000003", "0983665616", true, "CR"),
                new ClienteDto(5, "Ximena", "Silva", "1890000004", "0983475616", true, "CR"),
                new ClienteDto(6, "Thalia", "Rodriguez", "1890000005", "0983475616", true, "CR"),
                new ClienteDto(7, "Jonh", "Rodriguez", "1890000006", "0983475616", true, "CR"),
                new ClienteDto(8, "Eduardo", "Guerra", "1890000007", "0983475616", true, "CR"),
                new ClienteDto(9, "Juan", "Vaca", "1890000008", "0983475616", true, "CR"),
                new ClienteDto(10, "Cristina", "Ortiz", "1890000009", "0983475616", true, "CR")
        );
        clientes.forEach(cliente -> clienteService.insertClient(cliente));
    }

    @Test
    void obtieneClientesSinDatos()
    {
        var consulta = new ClienteQueryDto();
        consulta.setTextoBusqueda("mi texto");
        var resultado = administradorClienteV2ByNames.obtieneClientes(consulta);
        Assert.isTrue(resultado.size() == 0, "Sin resultados");
    }
    @Test
    void obtieneClientesConNombreExitoso()
    {
        var consulta = new ClienteQueryDto();
        consulta.setTextoBusqueda("RAUL");
        var resultado = administradorClienteV2ByNames.obtieneClientes(consulta);
        Assert.isTrue(resultado.size() == 1, "Con resultados");
    }

    @Test
    void obtieneClientesConCedulaExitoso()
    {
        var consulta = new ClienteQueryDto();
        consulta.setTextoBusqueda("1100");
        var resultado = defaultCedula.obtieneClientes(consulta);
        Assert.isTrue(resultado.size() == 1, "Con resultados");
    }

    @Test
    void obtieneClientesPreCargadosConCedulaExitoso()
    {
        var consulta = new ClienteQueryDto();
        consulta.setTextoBusqueda("1890000007");
        var resultado = defaultCedula.obtieneClientes(consulta);
        Assert.isTrue(resultado.size() >= 1, "Con resultados");
    }
}
