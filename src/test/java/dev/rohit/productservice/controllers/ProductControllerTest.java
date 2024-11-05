package dev.rohit.productservice.controllers;

import dev.rohit.productservice.dtos.GenericProductDTO;
import dev.rohit.productservice.exception.NotFoundException;
import dev.rohit.productservice.services.ProductServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductServices productServicesMock;


//    @Test
//    public void testGetProductByIdReturnsEmptyObjectWhenNoProductIsFound() throws NotFoundException {
//        when(productServicesMock.getProductById(any(Long.class)))
//                .thenReturn(null);
//
//        GenericProductDTO response = productController.getProductById(1L);
//
//        Assertions.assertNotNull(response);
//    }


//------
    @Test
    public void testMethOperation(){
        int result = addTwoNumber(1,2);
        Assertions.assertEquals(3,result,"Something went wrong in addTwoNumber");
    }
    private int addTwoNumber(int a, int b){
        return a+b;
    }
}
