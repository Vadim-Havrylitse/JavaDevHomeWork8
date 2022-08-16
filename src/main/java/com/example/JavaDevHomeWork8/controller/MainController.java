package com.example.JavaDevHomeWork8.controller;

import com.example.JavaDevHomeWork8.user.Role;
import com.example.JavaDevHomeWork8.user.User;
import com.example.JavaDevHomeWork8.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserRepo userRepo;

    @GetMapping("/")
    public String getMainPage(@RequestParam(name = "p1", required = false, defaultValue = "default") String param1,
                              Model model){
        if (!param1.equals("default")) {
            User user = User.builder()
                    .email("email.222")
                    .firstName(param1)
                    .lastName("Test")
                    .password("kokdnfskdnfsodfnwp")
                    .roles(Role.ADMIN)
                    .build();
            userRepo.save(user);
            StringBuilder str = new StringBuilder();
            userRepo.findAll().forEach(x -> str.append(x.toString()).append("\n"));
            model.addAttribute("label", str.toString());
        }else {
            model.addAttribute("label", param1);
        }
        return "main-page";
    }
}
