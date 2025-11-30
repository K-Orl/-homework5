package com.example.homework5;

import com.example.homework5.notificationservice.NotificationService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NotificationIntegrationTest {

    @Autowired
    private NotificationService notificationService;

    @MockBean
    private JavaMailSender mailSender;

    @Test
    public void testSendCreateEmail() {
        String testEmail = "test@example.com";

        // Вызываем метод для отправки "создания"
        notificationService.sendCreateEmail(testEmail);

        // Проверяем, что был отправлен правильный email
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(1)).send(captor.capture());

        SimpleMailMessage sentMessage = captor.getValue();
        assertThat(sentMessage.getTo()).contains(testEmail);
        assertThat(sentMessage.getText()).contains("Ваш аккаунт на сайте был успешно создан");
    }

    @Test
    public void testSendDeleteEmail() {
        String testEmail = "test@example.com";

        // Вызываем метод для отправки "удаления"
        notificationService.sendDeleteEmail(testEmail);

        // Проверяем, что был отправлен правильный email
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(1)).send(captor.capture());

        SimpleMailMessage sentMessage = captor.getValue();
        assertThat(sentMessage.getTo()).contains(testEmail);
        assertThat(sentMessage.getText()).contains("Ваш аккаунт был удалён");
    }
}
