package com.example.JavaDevHomeWork8.manufacturers.dao;

import com.example.JavaDevHomeWork8.manufacturers.entity.Manufacturers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ManufacturerRepo extends CrudRepository<Manufacturers, UUID> {
}
