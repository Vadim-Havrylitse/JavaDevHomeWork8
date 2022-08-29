package com.example.JavaDevHomeWork8.user.dao;

import com.example.JavaDevHomeWork8.user.entity.Role;
import com.example.JavaDevHomeWork8.user.entity.User;
import com.example.JavaDevHomeWork8.user.dto.UserDto;
import com.example.JavaDevHomeWork8.user.dto.UserRequestDto;
import com.example.JavaDevHomeWork8.util.err.EntityNoExist;
import com.example.JavaDevHomeWork8.util.err.EntityWithDuplicateData;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;

    public User get(UUID id){
        return userRepo.findById(id).get();
    }

    public List<User> getAll(){
        List<User> result = new ArrayList<>();
        userRepo.findAll().forEach(result::add);
        return result;
    }

    public List<UserDto> toDto(List<User> users){
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
    public UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().name())
                .build();
    }

    public User toEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .role(Role.valueOf(userDto.getRole()))
                .build();
    }

    public User toEntity(UserRequestDto userRequestDto){
        return User.builder()
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .role(Role.valueOf(userRequestDto.getRole()))
                .build();
    }

    @Transactional
    public User save(User user) throws EntityWithDuplicateData {
        try {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return userRepo.save(user);
        } catch (Exception e){
            e.printStackTrace();
            throw new EntityWithDuplicateData(e.getMessage());
        }
    }

    public void delete(UUID id) throws EntityNoExist {
        try{
            userRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new EntityNoExist("Can't delete user!");
        }
    }

    @Transactional
    public void update(User user) throws EntityNoExist {
        if(userRepo.existsById(user.getId())){
            if(user.getPassword() != null) {
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            }
            userRepo.save(user);
        } else {
            throw new EntityNoExist("Can't update user!");
        }
    }
}