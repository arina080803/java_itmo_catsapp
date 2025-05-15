package ru.startseva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.startseva.dtos.UserDto;
import ru.startseva.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public void userRegistration(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @PostMapping("/owner")
    public void addOwnerToUser(
        @RequestParam("ownerid") int ownerid, @RequestParam("userid") int userid) {
        userService.addOwner(userid, ownerid);
    }
}
