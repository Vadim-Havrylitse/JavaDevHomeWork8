package com.example.JavaDevHomeWork8.manufacturers;

import com.example.JavaDevHomeWork8.manufacturers.dao.ManufacturersService;
import com.example.JavaDevHomeWork8.manufacturers.dto.ManufacturersDto;
import com.example.JavaDevHomeWork8.manufacturers.entity.Manufacturers;
import com.example.JavaDevHomeWork8.products.dao.ProductsService;
import com.example.JavaDevHomeWork8.util.err.EntityNoExist;
import com.example.JavaDevHomeWork8.util.err.EntityWithDuplicateData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/manufacturers")
@RequiredArgsConstructor
public class ManufacturerController {
    private final ManufacturersService manufacturerService;
    private final ProductsService productsService;

    @GetMapping("")
    public String getManufacturers(Model model){
        fillModelForMainPage(model);
        return "manufacturers-main";
    }

    private void fillModelForMainPage(Model model) {
        List<Manufacturers> manufacturers = manufacturerService.getAll();
        List<ManufacturersDto> manufacturerDtos = manufacturerService.toDto(manufacturers);
        model.addAttribute("manufacturers", manufacturerDtos);
    }

    @GetMapping("/get/{id}")
    public String getManufacturer(@PathVariable UUID id, Model model){
        Manufacturers manufacturer = manufacturerService.get(id);
        ManufacturersDto manufacturerDto = manufacturerService.toDto(manufacturer);
        model.addAttribute("manufacturer", manufacturerDto);
        return "manufacturers-info";
    }

    @GetMapping("/delete/{id}")
    public String deleteManufacturer(@PathVariable UUID id, Model model){
        try {
            manufacturerService.delete(id);
        } catch (EntityNoExist e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        fillModelForMainPage(model);
        return "manufacturers-main";
    }

    @GetMapping("/update/{id}")
    public String updateManufacturer(@PathVariable UUID id, Model model){
        ManufacturersDto manufacturerDto = manufacturerService.toDto(manufacturerService.get(id));
        model.addAttribute("products", productsService.getAll());
        model.addAttribute("manufacturer", manufacturerDto);
        return "manufacturers-update";
    }

    @GetMapping("/update")
    public String updateManufacturer(Model model){
        return getManufacturers(model);
    }

    @PostMapping(value = "/update")
    public String updateManufacturer(@RequestParam Map<String, String> inputValue, Model model){
        ManufacturersDto manufacturersDto = new ObjectMapper().convertValue(inputValue, ManufacturersDto.class);
        Manufacturers manufacturers = manufacturerService.toEntity(manufacturersDto);
        try {
            manufacturerService.update(manufacturers);
        } catch (EntityNoExist e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        return getManufacturer(manufacturers.getId(), model);
    }

    @GetMapping(value = "/save")
    public String saveManufacturer(Model model){
        System.out.println("saveManufacturer GET METHOD");
        model.addAttribute("products", productsService.getAll());
        return "manufacturers-insert";
    }

    @PostMapping(value = "/save")
    public String saveManufacturer(@RequestParam Map<String, String> inputValue, Model model){
        ManufacturersDto manufacturersDto = new ObjectMapper().convertValue(inputValue, ManufacturersDto.class);
        Manufacturers manufacturer = manufacturerService.toEntity(manufacturersDto);
        Manufacturers saveManufacturer = null;
        try {
            saveManufacturer = manufacturerService.save(manufacturer);
        } catch (EntityWithDuplicateData e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        return getManufacturer(saveManufacturer.getId(), model);
    }


}
