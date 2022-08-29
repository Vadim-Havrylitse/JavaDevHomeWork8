package com.example.JavaDevHomeWork8.manufacturers.dao;

import com.example.JavaDevHomeWork8.manufacturers.entity.Manufacturers;
import com.example.JavaDevHomeWork8.manufacturers.dto.ManufacturersDto;
import com.example.JavaDevHomeWork8.products.entity.Products;
import com.example.JavaDevHomeWork8.util.err.EntityNoExist;
import com.example.JavaDevHomeWork8.util.err.EntityWithDuplicateData;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.SQLNonTransientException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManufacturersService {
    private final ManufacturerRepo manufacturerRepo;

    public Manufacturers get(UUID id){
        return manufacturerRepo.findById(id).get();
    }

    public List<Manufacturers> getAll(){
        List<Manufacturers> result = new ArrayList<>();
        manufacturerRepo.findAll().forEach(result::add);
        return result;
    }

    public List<ManufacturersDto> toDto(List<Manufacturers> manufacturers){
        return manufacturers.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ManufacturersDto toDto(Manufacturers manufacturer){
        Map<UUID, String> productIdName = new HashMap<>();
        if(manufacturer.hasProducts()) {
            manufacturer.getProducts().forEach(el -> productIdName.put(el.getId(), el.getName()));
        }
        return ManufacturersDto.builder()
                .id(manufacturer.getId())
                .name(manufacturer.getName())
                .productsIdName(productIdName)
                .build();
    }

    public Manufacturers toEntity(ManufacturersDto manufacturersDto){
        return Manufacturers.builder()
                .id(manufacturersDto.getId())
                .name(manufacturersDto.getName())
                .products(manufacturersDto.getId() != null ?
                        manufacturerRepo.findById(manufacturersDto.getId()).get().getProducts() : null)
                .build();
    }

    public void delete(UUID id) throws EntityNoExist {
        try{
            manufacturerRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new EntityNoExist("Can't delete manufacturers!");
        }
    }

    @Transactional
    public void update(Manufacturers manufacturers) throws EntityNoExist {
        if (manufacturerRepo.existsById(manufacturers.getId())){
            manufacturerRepo.save(manufacturers);
        } else {
            throw new EntityNoExist("Can't update manufacturers!");
        }
    }

    @Transactional
    public Manufacturers save(Manufacturers manufacturers) throws EntityWithDuplicateData {
        try {
            return manufacturerRepo.save(manufacturers);
        } catch (Exception e){
            e.printStackTrace();
            throw new EntityWithDuplicateData(e.getMessage());
        }
    }
}