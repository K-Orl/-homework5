package com.example.homework5.notificationservice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/send")
    public String sendTest(@RequestParam String email, @RequestParam String message) {
        notificationService.sendEmail(email, message);
        return "Попытка отправки email завершена (смотри консоль)";
    }

    @PostMapping("/send-create")
    public String sendCreate(@RequestParam String email) {
        notificationService.sendCreateEmail(email);
        return "Create email отправлен на " + email;
    }

    @PostMapping("/send-delete")
    public String sendDelete(@RequestParam String email) {
        notificationService.sendDeleteEmail(email);
        return "Delete email отправлен на " + email;
    }
}
