package com.example.JavaDevHomeWork8.products.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsDto {
    private UUID id;
    private String name;
    private Integer price;
    private UUID manufacturerId;
    private String manufacturerName;
}
