package dev.rohit.productservice.services;

import dev.rohit.productservice.ProductServiceApplication;
import dev.rohit.productservice.dtos.GenericProductDTO;
import dev.rohit.productservice.exception.NotFoundException;
import dev.rohit.productservice.thirdPartyClients.fakeStoreClient.FakeStoreProductClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FakeStoreProductServiceTest {
    @Autowired
    private FakeStoreProduceService fakeStoreProduceService;

    @MockBean
    private FakeStoreProductClient fakeStoreProductClient;

    @Test
    public void testGetProductByIdWhenClientReturnsNull() throws NotFoundException{
        when(fakeStoreProductClient.getProductById(any()))
                .thenReturn(null);
        GenericProductDTO response = fakeStoreProduceService.getProductById(1L);

        Assertions.assertNull(response);

//        Assertions.assertThrows(NotFoundException.class, ()-> fakeStoreProduceService.getProductById(1L));
    }
}
