package com.company.demodata.jms.publisher;

import com.company.demodata.dto.CuentaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ProcesadorNotificacionEjecutivos {

    @ServiceActivator(inputChannel = "pubSubNotification")
    public Message<String> procesarNotificacion(Message<CuentaDto> message) {
        log.info("Procesando notificacion: {}", message.getPayload());
        return MessageBuilder.withPayload("Mensaje recibido").build();
    }
}