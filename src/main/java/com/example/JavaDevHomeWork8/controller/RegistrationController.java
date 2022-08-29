package com.example.JavaDevHomeWork8.controller;

import com.example.JavaDevHomeWork8.user.entity.User;
import com.example.JavaDevHomeWork8.user.dto.UserDto;
import com.example.JavaDevHomeWork8.user.dao.UserService;
import com.example.JavaDevHomeWork8.util.err.EntityWithDuplicateData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private String registration(){
        return "registration";
    }

    private String saveUser(@RequestBody UserDto userDto){
        System.out.println("saveUser method********************** " + userDto.toString());
        User user = userService.toEntity(userDto);
        System.out.println(user.toString());
        User save = null;
        try {
            save = userService.save(user);
        } catch (EntityWithDuplicateData e) {
            e.printStackTrace();
        }
        System.out.println(save.toString());
        return "redirect:/login";
    }
}
