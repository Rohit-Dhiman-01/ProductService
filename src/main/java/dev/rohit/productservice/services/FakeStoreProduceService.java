package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.ExceptionDTO;
import dev.rohit.productservice.thirdPartyClients.dtos.FakeStoreProductDTO;
import dev.rohit.productservice.exception.NotFoundException;
import dev.rohit.productservice.thirdPartyClients.fakeStoreClient.FakeStoreProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.rohit.productservice.dtos.GenericProductDTO;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProduceDTO")
public class FakeStoreProduceService implements ProductServices{
    private FakeStoreProductClient fakeStoreProductClient;
    @Autowired
    public FakeStoreProduceService(FakeStoreProductClient fakeStoreProductClient) {
        this.fakeStoreProductClient = fakeStoreProductClient;
    }
//@Bean @Configration
    @Override
    public GenericProductDTO getProductById(Long id) throws NotFoundException {

        return convertFakeStoreDtoTOGenericProductDTO(fakeStoreProductClient.getProductById(id));
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        return convertFakeStoreDtoTOGenericProductDTO(fakeStoreProductClient.createProduct(product));
    }

    @Override
    public List<GenericProductDTO> getAllProduct() {

        List< FakeStoreProductDTO> fakeStoreProductDTOS = fakeStoreProductClient.getAllProduct();
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOS) {
            GenericProductDTO genericProductDTO = convertFakeStoreDtoTOGenericProductDTO(fakeStoreProductDTO);

            genericProductDTOS.add(genericProductDTO);
        }

        return genericProductDTOS;
    }

    @Override
    public GenericProductDTO deleteProduct(Long id) {
        return convertFakeStoreDtoTOGenericProductDTO(fakeStoreProductClient.deleteProduct(id));
    }

    @Override
    public GenericProductDTO updateProduct(GenericProductDTO genericProductDTO, Long id) {
        return convertFakeStoreDtoTOGenericProductDTO(fakeStoreProductClient.updateProduct(genericProductDTO,id));
    }


    public GenericProductDTO convertFakeStoreDtoTOGenericProductDTO(FakeStoreProductDTO fakeStoreProductDTO) {
        if (fakeStoreProductDTO == null) {
            return null;
        }
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        return genericProductDTO;

    }

}
