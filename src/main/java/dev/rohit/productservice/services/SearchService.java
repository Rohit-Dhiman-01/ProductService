package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.GenericProductDTO;
import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.repositories.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    public ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<GenericProductDTO> search(String query, int pageSize, int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by("price"));
        Page<Product> productPage = productRepository
                .findAllByTitleContaining(query, pageable);

        List<Product> products = productPage.get().toList();
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for(Product product: products) {
            genericProductDTOS.add(mapProductToGenericProduct(product));
        }

        Page<GenericProductDTO> genericProductDTOPage =
                new PageImpl<>(genericProductDTOS,
                        productPage.getPageable(), productPage.getTotalElements());

        return genericProductDTOPage;
    }
    private GenericProductDTO mapProductToGenericProduct(Product product) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setImage(product.getImageUrl());
        genericProductDTO.setDescription(product.getDescription());

        return genericProductDTO;
    }
}
