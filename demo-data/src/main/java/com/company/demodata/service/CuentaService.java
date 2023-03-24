package com.company.demodata.service;

import com.company.demodata.criteria.ClienteSpecification;
import com.company.demodata.criteria.CuentaSpecification;
import com.company.demodata.dto.ClienteDto;
import com.company.demodata.dto.CuentaDto;
import com.company.demodata.model.Cliente;
import com.company.demodata.model.Cuenta;
import com.company.demodata.repository.CuentaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CuentaService {
    private CuentaRepository cuentaRepository;

    private CuentaSpecification cuentaSpecification;

    public List<CuentaDto> buscarCuentaPorCriterioUsandoClienteId(CuentaDto cuentaDtoFilter)
    {
        return cuentaRepository.findAll(cuentaSpecification.buildFilter(cuentaDtoFilter))
                .stream().map(e -> Helpers.fromSourceToTarget(e, new CuentaDto())).collect(Collectors.toList());
    }

    public CuentaDto insertCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();
        BeanUtils.copyProperties(cuentaDto, cuenta);
        cuentaRepository.save(cuenta);
        cuentaDto.setId(cuenta.getId());
        return cuentaDto;
    }


    public CuentaDto disableCuenta(int clienteId, int cuentaId) {
        Cuenta cuenta = cuentaRepository.findCuentaByCliente_IdAndId(clienteId, cuentaId);
        if(cuenta == null)
            return null;
        cuenta.setEstado(false);
        cuentaRepository.save(cuenta);
        CuentaDto cuentaDto = new CuentaDto();
        return Helpers.fromSourceToTarget(cuenta, cuentaDto);
    }

    public List<CuentaDto> obtieneCuentasPorCliente(int clienteId) {
        return cuentaRepository.findCuentaByCliente_Id(clienteId)
                .stream().map(e -> Helpers.fromSourceToTarget(e, new CuentaDto()))
                .collect(Collectors.toList());
    }
}
