package com.example.JavaDevHomeWork8.products.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPrintDto {
    private UUID id;
    private String name;
    private String price;
    private String manufacturerName;
}
