package com.example.homework5.notificationservice;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/notifications")
@Tag(name = "Notifications", description = "API для отправки email-уведомлений")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Operation(summary = "Отправить тестовое письмо")
    @GetMapping("/send")
    public EntityModel<String> sendTest(
            @RequestParam String email,
            @RequestParam String message
    ) {
        notificationService.sendEmail(email, message);

        EntityModel<String> model = EntityModel.of(
                "Попытка отправки email завершена (смотри консоль)"
        );

        model.add(linkSelf("send", email, message));
        model.add(linkCreate(email));
        model.add(linkDelete(email));

        return model;
    }

    @Operation(summary = "Отправить CREATE email")
    @PostMapping("/send-create")
    public EntityModel<String> sendCreate(@RequestParam String email) {
        notificationService.sendCreateEmail(email);

        EntityModel<String> model = EntityModel.of("Create email отправлен на " + email);
        model.add(linkSelfCreate(email));
        model.add(linkDelete(email));
        return model;
    }

    @Operation(summary = "Отправить DELETE email")
    @PostMapping("/send-delete")
    public EntityModel<String> sendDelete(@RequestParam String email) {
        notificationService.sendDeleteEmail(email);

        EntityModel<String> model = EntityModel.of("Delete email отправлен на " + email);
        model.add(linkSelfDelete(email));
        model.add(linkCreate(email));
        return model;
    }

    // === HATEOAS LINKS ===

    private Link linkSelf(String method, String email, String message) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(NotificationController.class)
                        .sendTest(email, message)
        ).withSelfRel();
    }

    private Link linkSelfCreate(String email) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(NotificationController.class)
                        .sendCreate(email)
        ).withSelfRel();
    }

    private Link linkSelfDelete(String email) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(NotificationController.class)
                        .sendDelete(email)
        ).withSelfRel();
    }

    private Link linkCreate(String email) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(NotificationController.class)
                        .sendCreate(email)
        ).withRel("send-create");
    }

    private Link linkDelete(String email) {
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(NotificationController.class)
                        .sendDelete(email)
        ).withRel("send-delete");
    }
}
