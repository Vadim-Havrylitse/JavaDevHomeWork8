package com.example.JavaDevHomeWork8.products;

import com.example.JavaDevHomeWork8.manufacturers.ManufacturerRepo;
import com.example.JavaDevHomeWork8.manufacturers.ManufacturerService;
import com.example.JavaDevHomeWork8.manufacturers.Manufacturers;
import com.example.JavaDevHomeWork8.products.dto.ProductPrintDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;
    private final ProductsRepo productsRepo;
    private final ManufacturerRepo manufacturerRepo;

    @GetMapping(value = "/products")
    private String getProducts(Model model){

        setDB();

        List<Products> products = productsService.getAll();
        System.out.println("PRINT productsService.getAll()");
        products.forEach(System.out::println);
        List<ProductPrintDto> productPrintDtos = productsService.toDTO(products);
        System.out.println("PRINT productsService.toDTO(products)");
        productPrintDtos.forEach(System.out::println);
        System.out.println("ADD TO HTML!!!!!");
        model.addAttribute("products", productPrintDtos);
        return "products-main";
    }

    private void setDB(){
        for(int i=0; i<5; i++){
            Manufacturers save = manufacturerRepo.save(Manufacturers.builder()
                    .name("ManufacturersTestName " + i)
                    .products(null)
                    .build());
            System.out.println(save);
            Products save1 = productsRepo.save(Products.builder()
                    .name("ProductTestName " + i)
                    .manufacturer(save)
                    .price(123L)
                    .build());
            System.out.println(save1);
        }
    }
}
