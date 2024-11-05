package dev.rohit.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.rohit.productservice.dtos.GenericProductDTO;
import dev.rohit.productservice.exception.NotFoundException;
import dev.rohit.productservice.services.ProductServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
//@WebMvcTest(controllers = ProductController.class)
public class ProductControllerMockMVCTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductServices productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetProductByIdAPI() throws Exception {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(1l);
        genericProductDTO.setTitle("iPhone");
        genericProductDTO.setCategory("electronics");

        when(productService.getProductById(1L))
                .thenReturn(genericProductDTO);
        ResultActions resultActions = mockMvc.perform(get("/products/1"))
                .andExpect(status().is(200))
               .andExpect(content().json("{\"id\":1,\"title\":\"iPhone\",\"description\":null,\"image\":null,\"category\":\"electronics\",\"price\":0.0}"))
               .andExpect(jsonPath("$.id").value(1L));
        String responseString = resultActions.andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("{\"id\":1,\"title\":\"iPhone\",\"description\":null,\"image\":null,\"category\":\"electronics\",\"price\":0.0}",
                responseString);

        GenericProductDTO responseDto = objectMapper.readValue(responseString, GenericProductDTO.class);
        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(1L, responseDto.getId());
    }
}
