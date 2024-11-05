package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.GenericProductDTO;
import dev.rohit.productservice.exception.NotFoundException;

import java.util.List;

public interface ProductServices {
    public GenericProductDTO getProductById(Long id) throws NotFoundException;
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
    public List<GenericProductDTO> getAllProduct();
    public GenericProductDTO deleteProduct(Long id);
    public GenericProductDTO updateProduct(GenericProductDTO genericProductDTO, Long id);
}
