package com.example.JavaDevHomeWork8.user;

import com.example.JavaDevHomeWork8.user.dao.UserService;
import com.example.JavaDevHomeWork8.user.dto.UserDto;
import com.example.JavaDevHomeWork8.user.dto.UserRequestDto;
import com.example.JavaDevHomeWork8.user.entity.Role;
import com.example.JavaDevHomeWork8.user.entity.User;
import com.example.JavaDevHomeWork8.util.err.EntityNoExist;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = {"", "/update"})
    public String getUsers(Model model) {
        model.addAttribute("users", userService.toDto(userService.getAll()));
        return "users-main";
    }

    @GetMapping("/get/{id}")
    public String getUser(@PathVariable UUID id, Model model) {
        model.addAttribute("user", userService.toDto(userService.get(id)));
        return "users-info";
    }

    @PreAuthorize("ADMIN")
    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable UUID id, Model model) {

        model.addAttribute("roles", List.of(Role.values()));
        model.addAttribute("user", userService.toDto(userService.get(id)));
        return "users-update";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam Map<String, String> inputValue, Model model) {
        ObjectMapper mapper = new ObjectMapper();
        UserDto userDto = mapper.convertValue(inputValue, UserDto.class);
        User user = userService.toEntity(userDto);
        try {
            userService.update(user);
        } catch (EntityNoExist e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        return getUsers(model);
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable UUID id, Model model) {
        try {
            userService.delete(id);
        } catch (EntityNoExist e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        return getUsers(model);
    }

    @GetMapping("/save")
    public String saveUser(Model model){
        model.addAttribute("roles", List.of(Role.values()));
        return "users-insert";
    }

    @SneakyThrows
    @PostMapping("/save")
    public String saveUser(@RequestParam Map<String, String> inputValue, Model model) {
        ObjectMapper mapper = new ObjectMapper();
        UserRequestDto userRequestDto = mapper.convertValue(inputValue, UserRequestDto.class);
        User user = userService.toEntity(userRequestDto);
        userService.save(user);
        return getUsers(model);
    }
}