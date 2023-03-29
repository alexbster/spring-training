package com.company.demodata.service;

import com.company.demodata.criteria.CuentaSpecification;
import com.company.demodata.dto.CuentaDto;
import com.company.demodata.jms.dto.NotificationDto;
import com.company.demodata.jms.publisher.NotificationPublisher;
import com.company.demodata.jms.subscriber.NotificationPubSubSender;
import com.company.demodata.model.Cuenta;
import com.company.demodata.repository.ClienteRepository;
import com.company.demodata.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CuentaService {

    private ClienteRepository clienteRepository;
    private CuentaRepository cuentaRepository;

    private CuentaSpecification cuentaSpecification;

    @Autowired
    private NotificationPublisher noticationPublisher;

    private NotificationPubSubSender notificationPubSubSender;

    public List<CuentaDto> buscarCuentaPorCriterioUsandoClienteId(CuentaDto cuentaDtoFilter)
    {
        return cuentaRepository.findAll(cuentaSpecification.buildFilter(cuentaDtoFilter))
                .stream().map(e -> Helpers.fromSourceToTarget(e, new CuentaDto())).collect(Collectors.toList());
    }

    public CuentaDto insertCuenta(CuentaDto cuentaDto) {
        var cliente = clienteRepository.findById(cuentaDto.getClienteId());
        if(cliente.isEmpty())
            return null;
        var cuenta = Helpers.fromSourceToTarget(cuentaDto, new Cuenta());
        cuenta.setCliente(cliente.get());
        cuentaRepository.save(cuenta);
        cuentaDto.setId(cuenta.getId());

        // Envio de notificacion
        this.sendNotification(cuentaDto);

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

    private void sendNotification(CuentaDto cuentaDto) {

        clienteRepository.findById(cuentaDto.getClienteId()).ifPresent(cliente -> {
            var notificationDto = new NotificationDto();
            notificationDto.setPhoneNumber(cliente.getTelefono());
            notificationDto.setMessage(String.format("Estimado: %s, se ha creado la cuenta exitosamente.", cliente.getNombre()));
            noticationPublisher.sendNotification(notificationDto);
        });


        var message = MessageBuilder.withPayload(cuentaDto).build();
        notificationPubSubSender.sendNotification(message);

    }
}
