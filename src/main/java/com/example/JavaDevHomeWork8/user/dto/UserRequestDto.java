package com.example.JavaDevHomeWork8.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestDto {
    private String email;
    private String firstName;
    private String password;
    private String lastName;
    private String role;
}
