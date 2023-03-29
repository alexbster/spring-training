package com.company.demodata.jms.subscriber;

import com.company.demodata.jms.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationSubscriber {

    private static final String SMS_QUEUE = "smsReceiverJms";
    @JmsListener(destination = SMS_QUEUE)// va a representar el nombre de la cola donde se está escuchando
    public void processMessage(NotificationDto noticationDto){
        // Logica de envio de SMS
        log.info("sms info: {}", noticationDto);
    }
}