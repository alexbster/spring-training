package com.company.demodata.service;

import com.company.demodata.dto.*;
import com.company.demodata.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.company.demodata.service.Helpers.*;

@Service
@Transactional
@AllArgsConstructor
public class ProductoService {

    private CuentaRepository cuentaRepository;

    private TarjetaRepository tarjetaRepository;

    private InversionRepository inversionRepository;


    public ResumenProductoDto obtieneProductosActivosPorCliente(int clienteId)
    {
        var result = new ResumenProductoDto();

        var cuentas = cuentaRepository.findCuentaByClienteIdAndEstadoIsTrue(clienteId);
        var tarjetas = tarjetaRepository.findTarjetaByClienteIdAndEstadoIsTrue(clienteId);
        var inversiones = inversionRepository.findInversionByClienteIdAndEstadoIsTrue(clienteId);

        cuentas.forEach(cuenta -> {
            result.getCuentas().add(Helpers.fromCuentaToDto(cuenta));
        });
        tarjetas.forEach(tarjeta -> {
            result.getTarjetas().add(Helpers.fromTarjetaToDto(tarjeta));
        });
        inversiones.forEach(inversion -> {
            result.getInversiones().add(Helpers.fromInversionToDto(inversion));
        });

        return result;
    }
}
