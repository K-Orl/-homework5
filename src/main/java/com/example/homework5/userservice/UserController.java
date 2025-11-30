package com.example.homework5.userservice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestParam String email) {
        userService.createUser(email);
        return "CREATE событие отправлено";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String email) {
        userService.deleteUser(email);
        return "DELETE событие отправлено";
    }
}
