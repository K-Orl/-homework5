package com.example.homework5.userservice;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "API для управления пользователями")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Создать пользователя")
    @PostMapping("/create")
    public EntityModel<String> createUser(@RequestParam String email) {

        userService.createUser(email);

        EntityModel<String> model = EntityModel.of("CREATE событие отправлено");

        model.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UserController.class).createUser(email)
        ).withSelfRel());

        model.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UserController.class).deleteUser(email)
        ).withRel("delete-user"));

        return model;
    }

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping("/delete")
    public EntityModel<String> deleteUser(@RequestParam String email) {

        userService.deleteUser(email);

        EntityModel<String> model = EntityModel.of("DELETE событие отправлено");

        model.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UserController.class).deleteUser(email)
        ).withSelfRel());

        model.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UserController.class).createUser(email)
        ).withRel("create-user"));

        return model;
    }
}
