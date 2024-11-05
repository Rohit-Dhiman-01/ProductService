package dev.rohit.productservice.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO{
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