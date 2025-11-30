package com.example.homework5.notificationservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setFrom(from);
            message.setSubject("Уведомление");
            message.setText(text);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Дополнительные удобные методы для тестов/контроллера
    public void sendCreateEmail(String to) {
        sendEmail(to, "Здравствуйте! Ваш аккаунт на сайте был успешно создан.");
    }

    public void sendDeleteEmail(String to) {
        sendEmail(to, "Здравствуйте! Ваш аккаунт был удалён.");
    }
}
