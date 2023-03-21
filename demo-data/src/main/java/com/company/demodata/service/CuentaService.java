package com.company.demodata.service;

import com.company.demodata.criteria.ClienteSpecification;
import com.company.demodata.criteria.CuentaSpecification;
import com.company.demodata.dto.ClienteDto;
import com.company.demodata.dto.CuentaDto;
import com.company.demodata.model.Cliente;
import com.company.demodata.model.Cuenta;
import com.company.demodata.repository.CuentaRepository;
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
                .stream().map(this::fromCuentaDto).collect(Collectors.toList());
    }

    private CuentaDto fromCuentaDto(Cuenta cuenta){
        CuentaDto cuentaDto = new CuentaDto();
        BeanUtils.copyProperties(cuenta, cuentaDto);
        return cuentaDto;
    }
}
