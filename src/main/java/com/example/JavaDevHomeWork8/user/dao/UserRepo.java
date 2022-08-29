package com.example.JavaDevHomeWork8.user.dao;

import com.example.JavaDevHomeWork8.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepo extends CrudRepository<User, UUID> {
}
