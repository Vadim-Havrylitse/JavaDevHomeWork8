package com.example.JavaDevHomeWork8.products;

import com.example.JavaDevHomeWork8.manufacturers.dto.ManufacturersDto;
import com.example.JavaDevHomeWork8.manufacturers.dao.ManufacturersService;
import com.example.JavaDevHomeWork8.products.dao.ProductsService;
import com.example.JavaDevHomeWork8.products.dto.ProductsDto;
import com.example.JavaDevHomeWork8.products.entity.Products;
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
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;
    private final ManufacturersService manufacturersService;


    @GetMapping("")
    public String getProducts(Model model){
        fillModelForMainPage(model);
        return "products-main";
    }

    private void fillModelForMainPage(Model model) {
        List<Products> products = productsService.getAll();
        List<ProductsDto> productPrintDtos = productsService.toDTO(products);
        model.addAttribute("products", productPrintDtos);
    }

    @GetMapping("/get/{id}")
    public String getProduct(@PathVariable UUID id, Model model){
        Products products = productsService.get(id);
        ProductsDto productDto = productsService.toDTO(products);
        model.addAttribute("product", productDto);
        return "products-info";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable UUID id, Model model){
        try {
            productsService.delete(id);
        } catch (EntityNoExist e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        fillModelForMainPage(model);
        return "products-main";
    }

    @GetMapping("/update")
    public String updateProduct(Model model){
        return getProducts(model);
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable UUID id, Model model){
        ProductsDto productsDto = productsService.toDTO(productsService.get(id));
        List<ManufacturersDto> manufacturers = manufacturersService.toDto(manufacturersService.getAll());
        model.addAttribute("product", productsDto);
        model.addAttribute("manufacturers", manufacturers);
        return "products-update";
    }

    @PostMapping(value = "/update")
    public String updateProduct(@RequestParam Map<String, String> inputValue, Model model){
        ObjectMapper mapper = new ObjectMapper();
        ProductsDto map = mapper.convertValue(inputValue, ProductsDto.class);
        Products products = productsService.toEntity(map, manufacturersService.get(map.getManufacturerId()));
        try {
            productsService.update(products);
        } catch (EntityNoExist e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        return getProduct(map.getId(), model);
    }

    @GetMapping(value = "/save")
    public String saveProduct(Model model){
        model.addAttribute("manufacturers", manufacturersService.getAll());
        return "products-insert";
    }

    @PostMapping(value = "/save")
    public String saveProduct(@RequestParam Map<String, String> inputValue, Model model){
        ObjectMapper mapper = new ObjectMapper();
        ProductsDto productsDto = mapper.convertValue(inputValue, ProductsDto.class);
        Products products = productsService.toEntity(productsDto, manufacturersService.get(productsDto.getManufacturerId()));
        Products save = null;
        try {
            save = productsService.save(products);
        } catch (EntityWithDuplicateData e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        return getProduct(save.getId(), model);
    }
}