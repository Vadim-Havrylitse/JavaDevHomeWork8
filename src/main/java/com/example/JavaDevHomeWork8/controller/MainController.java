package com.example.JavaDevHomeWork8.controller;

import com.example.JavaDevHomeWork8.config.MyUserDetailsService;
import com.example.JavaDevHomeWork8.manufacturers.dao.ManufacturersService;
import com.example.JavaDevHomeWork8.manufacturers.entity.Manufacturers;
import com.example.JavaDevHomeWork8.products.dao.ProductsService;
import com.example.JavaDevHomeWork8.products.entity.Products;
import com.example.JavaDevHomeWork8.user.entity.Role;
import com.example.JavaDevHomeWork8.user.entity.User;
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
    private final ProductsService productsService;
    private final ManufacturersService manufacturersService;
    private final UserService userService;
    static boolean flagForSetUtilDataToDB = false;

    @SneakyThrows
    @GetMapping("/")
    public String getMainPage(@RequestParam(name = "p1", required = false, defaultValue = "default") String param1,
                              Model model){
        if (!flagForSetUtilDataToDB) {
            setDB();
            User user = User.builder()
                    .email("admin@com.ua")
                    .firstName("firstName1")
                    .lastName("lastName1")
                    .password("1")
                    .role(Role.ADMIN)
                    .build();
            userService.save(user);
            User user2 = User.builder()
                    .email("user@com.ua")
                    .firstName("firstName2")
                    .lastName("lastName2")
                    .password("1")
                    .role(Role.USER)
                    .build();
            userService.save(user2);
            flagForSetUtilDataToDB = true;
        }
            StringBuilder str = new StringBuilder();
            userService.getAll().forEach(x -> str.append(x.toString()).append("\n"));
            model.addAttribute("label", str.toString());
        return "main-page";
    }

    @SneakyThrows
    private void setDB(){
        for(int i=0; i<5; i++){
            Manufacturers save = manufacturersService.save(Manufacturers.builder()
                    .name("ManufacturersTestName " + i)
                    .build());
            productsService.save(Products.builder()
                    .name("ProductTestName " + i)
                    .manufacturer(save)
                    .price(1111+i)
                    .build());
        }
    }
}
