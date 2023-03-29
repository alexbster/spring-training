package com.company.demodata.jms.publisher;

import com.company.demodata.jms.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationPublisher {

    private static final String SMS_QUEUE = "smsReceiverJms";

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendNotification(NotificationDto notificationDto){
        log.info("sending notification to: {}", notificationDto);
        jmsTemplate.convertAndSend(SMS_QUEUE, notificationDto);
    }
}
