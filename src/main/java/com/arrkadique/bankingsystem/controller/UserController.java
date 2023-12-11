package com.arrkadique.bankingsystem.controller;

import com.arrkadique.bankingsystem.dto.UserDto;
import com.arrkadique.bankingsystem.entity.User;
import com.arrkadique.bankingsystem.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create/{roleId}")
    public User createUser(@RequestBody UserDto userDto, @PathVariable Long roleId){

        return userService.createUser(User.builder()
                        .username(userDto.getUsername())
                        .lastName(userDto.getLastName())
                        .password(userDto.getPassword())
                        .phoneNumber(userDto.getPhoneNumber())
                        .roleId(roleId)
                .build());
    }

}
