package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.GenericProductDTO;
import dev.rohit.productservice.exception.NotFoundException;
import dev.rohit.productservice.models.Category;
import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.repositories.CategoryRepository;
import dev.rohit.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductServices{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public SelfProductServiceImpl(ProductRepository productRepository,
                                  CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public GenericProductDTO getProductById(Long id) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("There is no product with this id "+ id);
        }
        Product product = productOptional.get();
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(product.getId());
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setDescription(product.getDescription());
        genericProductDTO.setPrice(product.getPrice());
        genericProductDTO.setImage(product.getImageUrl());
        genericProductDTO.setCategory(product.getCategory().getTitle());
        return genericProductDTO;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {

        Product product = new Product();
        product.setTitle(genericProductDTO.getTitle());
        product.setDescription(genericProductDTO.getDescription());
        product.setPrice(genericProductDTO.getPrice());
        product.setImageUrl(genericProductDTO.getImage());

        Optional<Category> categoryOptional = categoryRepository.findByTitle(genericProductDTO.getCategory());
        Category category ;
        if (categoryOptional.isPresent()) {
            category = categoryOptional.get();
            product.setCategory(category);
        }else {
            category = new Category();
            category.setTitle(genericProductDTO.getCategory());
            categoryRepository.save(category);
            product.setCategory(category);
        }

        Product p = productRepository.save(product);
        genericProductDTO.setId(p.getId());

        return genericProductDTO;
    }

    @Override
    public List<GenericProductDTO> getAllProduct() {
        List<Product> productList = productRepository.findAllOrderById();

        List<GenericProductDTO> genericProductDTOList = new ArrayList<>();
        for (Product product : productList) {
            GenericProductDTO genericProductDTO = new GenericProductDTO();

            genericProductDTO.setId(product.getId());
            genericProductDTO.setTitle(product.getTitle());
            genericProductDTO.setDescription(product.getDescription());
            genericProductDTO.setImage(product.getImageUrl());
            genericProductDTO.setPrice(product.getPrice());
            genericProductDTO.setCategory(product.getCategory().getTitle());

            genericProductDTOList.add(genericProductDTO);
        }
        return genericProductDTOList;
    }

    @Override
    public GenericProductDTO deleteProduct(Long id) {
        Product product = productRepository.getReferenceById(id);
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(id);
//        genericProductDTO.setTitle(product.getTitle());
//        genericProductDTO.setDescription(product.getDescription());
//        genericProductDTO.setImage(product.getImageUrl());
//        genericProductDTO.setPrice(product.getPrice());
//        genericProductDTO.setCategory(product.getCategory().getTitle());
        productRepository.delete(product);
        return genericProductDTO;
    }

    @Override
    public GenericProductDTO updateProduct(GenericProductDTO genericProductDTO, Long id) {
        Product product = productRepository.getReferenceById(id);
        product.setTitle(genericProductDTO.getTitle());
        product.setDescription(genericProductDTO.getDescription());
        product.setPrice(genericProductDTO.getPrice());
        product.setImageUrl(genericProductDTO.getImage());

        Optional<Category> categoryOptional = categoryRepository.findByTitle(genericProductDTO.getCategory());
        Category category ;
        if (categoryOptional.isPresent()) {
            category = categoryOptional.get();
            product.setCategory(category);
        }else {
            category = new Category();
            category.setTitle(genericProductDTO.getCategory());
            categoryRepository.save(category);
            product.setCategory(category);
        }

        productRepository.save(product);
        genericProductDTO.setId(product.getId());
        return genericProductDTO;
    }


}
