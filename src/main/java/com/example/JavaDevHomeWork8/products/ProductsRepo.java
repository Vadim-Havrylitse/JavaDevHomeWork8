package com.example.JavaDevHomeWork8.products;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductsRepo extends CrudRepository<Products, UUID> {
    Products findByNameContaining(String name);
}
