package com.company.demodata.service;

import com.company.demodata.dto.ClienteDto;
import com.company.demodata.model.Cliente;
import com.company.demodata.repository.ClienteRepository;
import com.company.demodata.repository.CuentaRepository;
import com.company.demodata.repository.DireccionRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;
    private DireccionRepository direccionRepository;
    private CuentaRepository cuentaRepository;

    public void insertClient(ClienteDto clienteDto)
    {
        var clienteEntity = new Cliente();
        clienteEntity.setNombre(clienteDto.getNombre());
        clienteEntity.setTelefono(clienteDto.getTelefono());
        clienteEntity.setCedula(clienteDto.getCedula());
        clienteEntity.setApellidos(clienteDto.getApellidos());
        clienteEntity.setEstado(clienteDto.isEstado());
        clienteEntity.setPaisNacimiento(clienteDto.getPaisNacimiento());
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
        clienteDto.setPaisNacimiento(clienteEntity.getPaisNacimiento());
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
                clienteDto.setPaisNacimiento(entity.getPaisNacimiento());
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
        clienteEntity.setPaisNacimiento(clienteDto.getPaisNacimiento());
        clienteRepository.save(clienteEntity);
        return clienteDto;
    }

    public void deleteClient(int clientId)
    {
        direccionRepository.deleteAllByCliente_Id(clientId);
        cuentaRepository.deleteAllByCliente_Id(clientId);
        clienteRepository.deleteById(clientId);
    }


    public List<ClienteDto> obtenerClientesPorCodigoISOPaisYCuentasActivas(String codigoIsoPais){
        List<ClienteDto> result = new ArrayList<>();
        var clienteEntities = clienteRepository.findClientesByPaisNacimientoAndCuentas_EstadoIsTrue(codigoIsoPais);
        clienteEntities.forEach(entity ->
                {
                    var clienteDto = new ClienteDto();
                    clienteDto.setNombre(entity.getNombre());
                    clienteDto.setTelefono(entity.getTelefono());
                    clienteDto.setCedula(entity.getCedula());
                    clienteDto.setApellidos(entity.getApellidos());
                    clienteDto.setEstado(entity.isEstado());
                    clienteDto.setId(entity.getId());
                    clienteDto.setPaisNacimiento(entity.getPaisNacimiento());
                    result.add(clienteDto);
                }
        );
        return result;
    }

    public List<ClienteDto> obtieneClientesPorApellido(String apellido){
        List<ClienteDto> result = new ArrayList<>();
        var clienteEntities = clienteRepository.obtieneClientesPorApellido(apellido);
        clienteEntities.forEach(entity ->
                {
                    var clienteDto = new ClienteDto();
                    clienteDto.setNombre(entity.getNombre());
                    clienteDto.setTelefono(entity.getTelefono());
                    clienteDto.setCedula(entity.getCedula());
                    clienteDto.setApellidos(entity.getApellidos());
                    clienteDto.setEstado(entity.isEstado());
                    clienteDto.setId(entity.getId());
                    clienteDto.setPaisNacimiento(entity.getPaisNacimiento());
                    result.add(clienteDto);
                }
        );
        return result;
    }
}
