package com.example.homework5.kafka;

import com.example.homework5.notificationservice.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final NotificationService notificationService;

    public KafkaConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Метод, который имитирует получение сообщения
    public void simulateReceive(String operation, String email) {
        if ("CREATE".equals(operation)) {
            notificationService.sendEmail(email, "Здравствуйте! Ваш аккаунт на сайте был успешно создан.");
        } else if ("DELETE".equals(operation)) {
            notificationService.sendEmail(email, "Здравствуйте! Ваш аккаунт был удалён.");
        }
    }
}
