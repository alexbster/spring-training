package com.company.demodata.service;

import com.company.demodata.dto.TarjetaDto;
import com.company.demodata.model.Tarjeta;
import com.company.demodata.repository.TarjetaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class TarjetaService {
    private TarjetaRepository tarjetaRepository;

    TarjetaDto findTarjetaById(int id){
        var tarjeta = tarjetaRepository.findTarjetaById(id);
        return Helpers.fromTarjetaToDto(tarjeta);
    }
    void actualizarEstadoUsandoId(int id, boolean estado){
        tarjetaRepository.actualizarEstadoUsandoId(id, estado);
    }
}
