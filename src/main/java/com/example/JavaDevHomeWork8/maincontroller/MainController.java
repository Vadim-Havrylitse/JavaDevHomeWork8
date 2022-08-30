package com.example.JavaDevHomeWork8.maincontroller;

import com.example.JavaDevHomeWork8.user.dao.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @SneakyThrows
    @GetMapping("/")
    public String getMainPage(@RequestParam(name = "p1", required = false, defaultValue = "default") String param1,
                              Model model){
            StringBuilder str = new StringBuilder();
            userService.getAll().forEach(x -> str.append(x.toString()).append("\n"));
            model.addAttribute("label", str.toString());
        return "main-page";
    }
}