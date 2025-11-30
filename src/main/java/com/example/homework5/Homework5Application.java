package com.example.homework5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class Homework5Application implements CommandLineRunner {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.sendTestMail:true}")
    private boolean sendTestMail;

    public static void main(String[] args) {
        SpringApplication.run(Homework5Application.class, args);
    }

    @Override
    public void run(String... args) {
        if (sendTestMail) {
            sendTestEmail();
        }
    }

    private void sendTestEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("karinaorlova656@gmail.com"); // Твоя почта
            message.setSubject("Тестовое письмо от Spring Boot");
            message.setText("Привет! Это тестовое письмо.");
            mailSender.send(message);
            System.out.println("✅ Тестовое письмо успешно отправлено!");
        } catch (Exception e) {
            System.err.println("❌ Ошибка при отправке письма: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
