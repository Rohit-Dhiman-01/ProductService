package dev.rohit.productservice.thirdPartyClients.fakeStore;

import dev.rohit.productservice.exception.NotFoundException;
import dev.rohit.productservice.thirdPartyClients.dtos.FakeStoreProductDTO;
import dev.rohit.productservice.thirdPartyClients.fakeStoreClient.FakeStoreProductClient;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;

public class FakeStoreProductClientTest {
    private FakeStoreProductClient fakeStoreProductClient;

    private RestTemplateBuilder restTemplateBuilder;

    public void testGetProductById() throws NotFoundException {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);

        ResponseEntity<FakeStoreProductDTO> responseMock =
                new ResponseEntity<>(null, HttpStatus.OK);

        when(restTemplate.getForEntity(any(String.class), eq(FakeStoreProductDTO.class), any(Long.class)))
                .thenReturn(responseMock);

        FakeStoreProductDTO response = fakeStoreProductClient.getProductById(1L);

        Assertions.assertNull(response);
    }
}
