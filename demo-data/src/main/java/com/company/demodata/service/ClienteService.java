package com.company.demodata.service;

import com.company.demodata.dto.ClienteDto;
import com.company.demodata.model.Cliente;
import com.company.demodata.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return clienteDto;
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
        clienteRepository.save(clienteEntity);
        return clienteDto;
    }

    public void deleteClient(int clientId)
    {
        clienteRepository.deleteById(clientId);
    }
}
