package com.example.JavaDevHomeWork8.products.dao;

import com.example.JavaDevHomeWork8.products.entity.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductsRepo extends CrudRepository<Products, UUID> {
}
