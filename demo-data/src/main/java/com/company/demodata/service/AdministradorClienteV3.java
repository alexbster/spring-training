package com.company.demodata.service;

import com.company.demodata.dto.ClienteDto;
import com.company.demodata.dto.ClienteQueryDto;
import com.company.demodata.dto.ClienteQueryType;
import com.company.demodata.model.Cliente;
import com.company.demodata.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdministradorClienteV3 {

    private ClienteRepository clienteRepository;
    private ClienteQueryType defaultClienteQueryType;
    public AdministradorClienteV3(ClienteQueryType defaultClienteQueryType)
    {
        this.defaultClienteQueryType = defaultClienteQueryType;
    }

    //Setter of clienteRepository
    public void setClienteRepository(ClienteRepository clienteRepository)
    {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDto> obtieneClientes(ClienteQueryDto clienteQueryDto)
    {
        if (clienteQueryDto.getTipoBusqueda() == null) {
            clienteQueryDto.setTipoBusqueda(defaultClienteQueryType);
        }

        List<Cliente> clientes = null;
        if (ClienteQueryType.CEDULA.equals(clienteQueryDto.getTipoBusqueda())) {
            clientes = this.clienteRepository.findByCedula(clienteQueryDto.getTextoBusqueda());
        } else if (ClienteQueryType.NOMBRES.equals(clienteQueryDto.getTipoBusqueda())) {
            clientes = this.clienteRepository.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(clienteQueryDto.getTextoBusqueda(), clienteQueryDto.getTextoBusqueda());
        }
        return Optional.ofNullable(clientes).map(clientesAux-> clientesAux.stream().map(cliente -> {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setNombre(cliente.getNombre());
            clienteDto.setApellidos(cliente.getApellidos());
            clienteDto.setCedula(cliente.getCedula());
            clienteDto.setTelefono(cliente.getTelefono());
            return clienteDto;
        }).collect(Collectors.toList())).orElse(new ArrayList<>());
    }
}
