package dev.rohit.productservice.thirdPartyClients.fakeStoreClient;

import dev.rohit.productservice.thirdPartyClients.dtos.FakeStoreProductDTO;
import dev.rohit.productservice.dtos.GenericProductDTO;
import dev.rohit.productservice.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductClient {

    @Value("${fakeStore.api.baseurl}")
    private String fakeStoreApiBaseUrl;

    @Value("${fakeStore.api.product}")
    private String fakeStoreProductPath;

    private final String productPath = "/products";

    private String productURL = fakeStoreApiBaseUrl + productPath + "/{id}";
    private String productRequestURL = fakeStoreApiBaseUrl + fakeStoreProductPath;
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakeStore.api.baseurl}") String fakeStoreApiBaseUrl,
                                  @Value("${fakeStore.api.product}") String fakeStoreProductPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productURL = fakeStoreApiBaseUrl + productPath + "/{id}";
        this.productRequestURL = fakeStoreApiBaseUrl + fakeStoreProductPath;
    }

    public FakeStoreProductDTO getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity(productURL,
                FakeStoreProductDTO.class,
                id);
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();

        if (fakeStoreProductDTO == null) {
//            return null;
            throw new NotFoundException("Product with id: " + id + " not found");
        }

        return fakeStoreProductDTO;
    }

    public FakeStoreProductDTO createProduct(GenericProductDTO product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity(
                productRequestURL,
                product,
                FakeStoreProductDTO.class);

        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();

        return fakeStoreProductDTO;
    }

    public List<FakeStoreProductDTO> getAllProduct() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate
                .getForEntity(productRequestURL,FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] fakeStoreProductDTOS = response.getBody();


        return Arrays.asList(fakeStoreProductDTOS);
    }

    public FakeStoreProductDTO deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO> response = restTemplate.
                exchange(productURL, HttpMethod.DELETE,null,FakeStoreProductDTO.class,id);

        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();

        return fakeStoreProductDTO;
    }

    public FakeStoreProductDTO updateProduct(GenericProductDTO product, Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO> response = restTemplate
                .exchange(productURL, HttpMethod.PUT,new HttpEntity<>(product),FakeStoreProductDTO.class,id);

        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        return fakeStoreProductDTO;
    }
}
