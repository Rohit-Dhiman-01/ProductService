package dev.rohit.productservice.controllers;

import dev.rohit.productservice.dtos.GenericProductDTO;
import dev.rohit.productservice.dtos.SearchRequestDTO;
import dev.rohit.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @PostMapping
    public Page<GenericProductDTO> search(@RequestBody SearchRequestDTO searchRequestDTO) {
        return searchService.search(searchRequestDTO.getQuery(),
                searchRequestDTO.getPageSize(),
                searchRequestDTO.getPageNumber());
    }

}
