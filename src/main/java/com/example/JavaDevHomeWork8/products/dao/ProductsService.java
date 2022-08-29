package com.example.JavaDevHomeWork8.products.dao;

import com.example.JavaDevHomeWork8.manufacturers.entity.Manufacturers;
import com.example.JavaDevHomeWork8.products.entity.Products;
import com.example.JavaDevHomeWork8.products.dto.ProductsDto;
import com.example.JavaDevHomeWork8.util.err.EntityNoExist;
import com.example.JavaDevHomeWork8.util.err.EntityWithDuplicateData;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepo productsRepo;

    public Products get(UUID id){
        return productsRepo.findById(id).get();
    }

    public List<Products> getAll(){
        List<Products> result = new ArrayList<>();
        productsRepo.findAll().forEach(result::add);
        return result;
    }

    public List<ProductsDto> toDTO(List<Products> products){
        List<ProductsDto> result = new ArrayList<>();
        for (Products product:products)
            result.add(toDTO(product));
        return result;
    }

    public ProductsDto toDTO(Products products){
        ProductsDto build = ProductsDto.builder()
                .id(products.getId())
                .name(products.getName())
                .price(products.getPrice())
                .build();
        if (products.getManufacturer() != null){
            build.setManufacturerId(products.getManufacturer().getId());
            build.setManufacturerName(products.getManufacturer().getName());
        }
        return build;
    }

    public Products toEntity(ProductsDto productsDto, Manufacturers manufacturers){
        return Products.builder()
                .id(productsDto.getId())
                .name(productsDto.getName())
                .price(productsDto.getPrice())
                .manufacturer(manufacturers)
                .build();
    }

    public void delete(UUID id) throws EntityNoExist {
        try{
            productsRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new EntityNoExist("Can't delete products!");
        }
    }

    @Transactional
    public void update(Products product) throws EntityNoExist {
        if(productsRepo.existsById(product.getId())){
            productsRepo.save(product);
        } else {
            throw new EntityNoExist("Can't update products!");
        }
    }

    @Transactional
    public Products save (Products products) throws EntityWithDuplicateData {
        try {
            return productsRepo.save(products);
        } catch (Exception e){
            e.printStackTrace();
            throw new EntityWithDuplicateData(e.getMessage());
        }
    }
}