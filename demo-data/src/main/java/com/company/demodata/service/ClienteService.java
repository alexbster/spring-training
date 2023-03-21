package com.company.demodata.service;

import com.company.demodata.criteria.ClienteSpecification;
import com.company.demodata.dto.ClienteDto;
import com.company.demodata.model.Cliente;
import com.company.demodata.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;
    private DireccionRepository direccionRepository;
    private CuentaRepository cuentaRepository;

    private TarjetaRepository tarjetaRepository;

    private InversionRepository inversionRepository;

    private ClienteSpecification clienteSpecification;

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
        var entity = clienteRepository.findById(clientId)
                .orElseThrow(() -> {
                    throw new RuntimeException("Cliente no existe");
                });
        var clienteDto = Helpers.fromSourceToTarget(entity, new ClienteDto());
        return clienteDto;
    }

    public List<ClienteDto> getClientUsingCountryCodeWithActiveAccounts(String codigoPais)
    {
        List<ClienteDto> result = new ArrayList<>();
        var clienteEntities = clienteRepository.getClientUsingCountryCodeWithActiveAccounts(codigoPais);
        clienteEntities.forEach(entity ->
            {
                var clienteDto = Helpers.fromSourceToTarget(entity, new ClienteDto());
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
                    var clienteDto = Helpers.fromSourceToTarget(entity, new ClienteDto());
                    result.add(clienteDto);
                }
        );
        return result;
    }

    public List<ClienteDto> obtieneClientesPorApellidoQueryLanguage(String apellido){
        List<ClienteDto> result = new ArrayList<>();
        var clienteEntities = clienteRepository.obtieneClientesPorApellidoQueryLanguage(apellido);
        clienteEntities.forEach(entity ->
                {
                    var clienteDto = Helpers.fromSourceToTarget(entity, new ClienteDto());
                    result.add(clienteDto);
                }
        );
        return result;
    }


    public List<ClienteDto> obtieneClientesPorApellidoQueryLanguageNativeQuery(String apellido){
        List<ClienteDto> result = new ArrayList<>();
        var clienteEntities = clienteRepository.obtieneClientesPorApellidoQueryLanguageNativeQuery(apellido);
        clienteEntities.forEach(entity ->
                {
                    var clienteDto = new ClienteDto();
                    clienteDto.setNombre((String) entity.get("nombre"));
                    clienteDto.setApellidos((String) entity.get("apellidos"));
                    clienteDto.setCedula((String) entity.get("cedula"));
                    clienteDto.setTelefono(entity.get("telefono").toString());
                    clienteDto.setId((Integer) entity.get("id"));
                    result.add(clienteDto);
                }
        );
        return result;
    }


    public List<ClienteDto> obtieneClientesExtrajerosConTarjetasInactivas(String codigoIsoPaisLocal){
        List<ClienteDto> result = new ArrayList<>();
        var clienteEntities = clienteRepository.obtieneClientesExtrajerosConTarjetasInactivas(codigoIsoPaisLocal);
        clienteEntities.forEach(entity ->
                {
                    var clienteDto = Helpers.fromSourceToTarget(entity, new ClienteDto());
                    result.add(clienteDto);
                }
        );
        return result;
    }

    public List<ClienteDto> obtieneClientesExtrajerosConTarjetasInactivasDerivedMethod(String codigoIsoPaisLocal){
        List<ClienteDto> result = new ArrayList<>();
        var clienteEntities = clienteRepository.findClientesByPaisNacimientoIsNotAndTarjetas_EstadoIsFalse(codigoIsoPaisLocal);
        clienteEntities.forEach(entity ->
                {
                    var clienteDto = Helpers.fromSourceToTarget(entity, new ClienteDto());
                    result.add(clienteDto);
                }
        );
        return result;
    }

    public List<ClienteDto> buscarClienteDinamicamentePorCriterio(ClienteDto clienteDtoFilter)
    {
        return clienteRepository.findAll(clienteSpecification.buildFilter(clienteDtoFilter))
                .stream().map(e -> Helpers.fromSourceToTarget(e, new ClienteDto())).collect(Collectors.toList());
    }
}
