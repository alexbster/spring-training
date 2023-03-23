package com.company.demodata.business.impl;

import com.company.demodata.business.BuscadorClientes;
import com.company.demodata.dto.ClienteDto;
import com.company.demodata.dto.ClienteQueryDto;
import com.company.demodata.dto.ClienteQueryType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("buscadorClientesSistemaExterno")
public class BuscadorClientesSistemaExterno implements BuscadorClientes {

    /**
     * Este método obtiene la lista de clientes de un sistema externo
     * @param queryDto contiene los parámetros de búsqueda
     * @return información dummy
     */
    @Override
    public List<ClienteDto> obtieneListaClientes(ClienteQueryDto queryDto) {
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

        return clientes.stream().filter(filter ->
                        queryDto.getTipoBusqueda() == ClienteQueryType.NOMBRES ?
                                filter.getNombre().contains(queryDto.getTextoBusqueda())
                        : filter.getCedula().contains(queryDto.getTextoBusqueda()))
                .toList();
    }
}
