package com.example.JavaDevHomeWork8.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.UUID;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}