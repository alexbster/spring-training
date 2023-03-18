package com.company.demodata.service;

import com.company.demodata.dto.ClienteDto;
import com.company.demodata.model.Cliente;
import com.company.demodata.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;
    public void insertClient(ClienteDto clienteDto)
    {
        var clienteEntity = new Cliente();
        clienteEntity.setNombre(clienteDto.getNombre());
        clienteEntity.setTelefono(clienteDto.getTelefono());
        clienteEntity.setCedula(clienteDto.getCedula());
        clienteEntity.setApellidos(clienteDto.getApellidos());
        clienteEntity.setEstado(clienteDto.isEstado());
        clienteEntity.setCodigoPais(clienteDto.getCodigoPais());
        clienteRepository.save(clienteEntity);
        clienteDto.setId(clienteEntity.getId());
    }

    public ClienteDto getClient(int clientId)
    {
        var clienteEntity = clienteRepository.findById(clientId)
                .orElseThrow(() -> {
                    throw new RuntimeException("Cliente no existe");
                });
        var clienteDto = new ClienteDto();
        clienteDto.setNombre(clienteEntity.getNombre());
        clienteDto.setTelefono(clienteEntity.getTelefono());
        clienteDto.setCedula(clienteEntity.getCedula());
        clienteDto.setApellidos(clienteEntity.getApellidos());
        clienteDto.setEstado(clienteEntity.isEstado());
        clienteDto.setId(clienteEntity.getId());
        clienteDto.setCodigoPais(clienteEntity.getCodigoPais());
        return clienteDto;
    }

    public List<ClienteDto> getClientUsingCountryCodeWithActiveAccounts(String codigoPais)
    {
        List<ClienteDto> result = new ArrayList<>();
        var clienteEntities = clienteRepository.getClientUsingCountryCodeWithActiveAccounts(codigoPais);
        clienteEntities.forEach(entity ->
            {
                var clienteDto = new ClienteDto();
                clienteDto.setNombre(entity.getNombre());
                clienteDto.setTelefono(entity.getTelefono());
                clienteDto.setCedula(entity.getCedula());
                clienteDto.setApellidos(entity.getApellidos());
                clienteDto.setEstado(entity.isEstado());
                clienteDto.setId(entity.getId());
                clienteDto.setCodigoPais(entity.getCodigoPais());
                result.add(clienteDto);
            }
        );
        return result;
    }

    public ClienteDto updateClient(ClienteDto clienteDto)
    {
        var clienteEntity = clienteRepository.findById(clienteDto.getId())
            .orElseThrow(() -> {
                throw new RuntimeException("Cliente no existe");
            });
        clienteEntity.setNombre(clienteDto.getNombre());
        clienteEntity.setTelefono(clienteDto.getTelefono());
        clienteEntity.setCedula(clienteDto.getCedula());
        clienteEntity.setApellidos(clienteDto.getApellidos());
        clienteEntity.setEstado(clienteDto.isEstado());
        clienteEntity.setCodigoPais(clienteDto.getCodigoPais());
        clienteRepository.save(clienteEntity);
        return clienteDto;
    }

    public void deleteClient(int clientId)
    {
        clienteRepository.deleteById(clientId);
    }
}
