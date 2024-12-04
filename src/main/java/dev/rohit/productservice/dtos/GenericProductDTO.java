package dev.rohit.productservice.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GenericProductDTO implements Serializable {
    private Long Id;
    @NotNull(message = "title cannot be null")
    private String title;
    @NotNull(message = "description cannot be null")
    private String description;
    @NotNull(message = "image cannot be null")
    private String image;
    @NotNull(message = "category cannot be null")
    private String category;
    @NotNull(message = "price cannot be null")
    private double price;    
}