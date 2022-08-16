package com.example.JavaDevHomeWork8.manufacturers;

import com.example.JavaDevHomeWork8.products.Products;
import com.example.JavaDevHomeWork8.products.ProductsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepo manufacturerRepo;

    public List<Manufacturers> getAll(){
        List<Manufacturers> result = new ArrayList<>();
        manufacturerRepo.findAll().forEach(result::add);
        return result;
    }
}
