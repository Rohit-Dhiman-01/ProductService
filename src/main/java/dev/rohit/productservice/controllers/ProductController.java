package dev.rohit.productservice.controllers;

import dev.rohit.productservice.dtos.ExceptionDTO;
import dev.rohit.productservice.exception.NotFoundException;
import dev.rohit.productservice.security.JwtData;
import dev.rohit.productservice.security.TokenValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import dev.rohit.productservice.dtos.GenericProductDTO;
import dev.rohit.productservice.services.ProductServices;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductServices fakeStoreProductServices;
    private ProductServices selfProductServices;
    private TokenValidator tokenValidator;

    @Autowired
    public ProductController(@Qualifier("fakeStoreProduceDTO") ProductServices fakeStoreProductServices,
                             @Qualifier("selfProductServiceImpl") ProductServices selfProductServices,
                             TokenValidator tokenValidator) {
        this.selfProductServices = selfProductServices;
        this.fakeStoreProductServices = fakeStoreProductServices;
        this.tokenValidator = tokenValidator;
    }
//  The following are API calls for the MY API SERVICES.
    @GetMapping("getProducts")
    public ResponseEntity<List<GenericProductDTO>> getAllProductsFromSelfProductServices() {
        return new ResponseEntity<>(selfProductServices.getAllProduct(), HttpStatus.OK);
    }
    @GetMapping("getProduct/{id}")
    public ResponseEntity<GenericProductDTO> getProductFromSelfProductServicesWithId(
            @PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(selfProductServices.getProductById(id), HttpStatus.OK);
    }
    @PostMapping("setProduct")
    public ResponseEntity<GenericProductDTO> createProductFromSelfProductServices(
            @Valid @RequestBody GenericProductDTO productDTO) {

        return new ResponseEntity<>(selfProductServices.createProduct(productDTO), HttpStatus.CREATED);
    }
    @PutMapping("setProduct/{id}")
    public ResponseEntity<GenericProductDTO> updateProductFromSelfProductServices(
            @Valid @RequestBody GenericProductDTO productDTO,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(selfProductServices.updateProduct(productDTO, id), HttpStatus.OK);
    }
    @DeleteMapping("setProduct/{id}")
    public ResponseEntity<GenericProductDTO> deleteProductFromSelfProductServices(
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(selfProductServices.deleteProduct(id), HttpStatus.NO_CONTENT);
    }
//  The following are API calls for the fake store (which is a third party services) API SERVICES.
    
    @GetMapping("thirdPartyService")
    public List<GenericProductDTO> getAllProductFromThirdPartyService() {
        return fakeStoreProductServices.getAllProduct();
    }

    @GetMapping("thirdPartyService/{id}")
    public GenericProductDTO getProductById(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @PathVariable("id") Long id) throws NotFoundException {

        GenericProductDTO genericProductDTO= fakeStoreProductServices.getProductById(id);
        if (genericProductDTO == null) {
            return new GenericProductDTO();
        }
        return genericProductDTO;
    }

    @PutMapping("thirdPartyService/{id}")
    public GenericProductDTO updateProductById(@PathVariable("id") Long id,
                                               @RequestBody GenericProductDTO genericProductDTO) throws NotFoundException {
        return fakeStoreProductServices.updateProduct(genericProductDTO,id);
    }

    @PostMapping("thirdPartyService")
    public GenericProductDTO createProduct(@Valid @RequestBody GenericProductDTO genericProductDTO){
        return fakeStoreProductServices.createProduct(genericProductDTO);
    }

    @DeleteMapping("thirdPartyService/{id}")
    public GenericProductDTO deleteProductById(@PathVariable("id") Long id){
        return fakeStoreProductServices.deleteProduct(id);
    }

}
