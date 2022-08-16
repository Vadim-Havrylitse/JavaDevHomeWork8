package com.example.JavaDevHomeWork8.products;

import com.example.JavaDevHomeWork8.products.dto.ProductPrintDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepo productsRepo;

    public List<Products> getAll(){
        List<Products> result = new ArrayList<>();
        productsRepo.findAll().forEach(result::add);
        return result;
    }

    public List<ProductPrintDto> toDTO(List<Products> products){
        List<ProductPrintDto> result = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (Products product:products)
            result.add(mapper.map(product, ProductPrintDto.class));
        return result;
    }

}
