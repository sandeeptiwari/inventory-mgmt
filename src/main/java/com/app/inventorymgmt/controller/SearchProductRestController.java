package com.app.inventorymgmt.controller;


import com.app.inventorymgmt.domain.dto.ProductDTO;
import com.app.inventorymgmt.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inv/v0/api")
public class SearchProductRestController {

    private final IProductService IProductService;

    public SearchProductRestController(IProductService IProductService) {
        this.IProductService = IProductService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(
            @RequestParam String keyword,
            @RequestParam(required = false, value = "filter") List<String> filters
    ) {
        List<ProductDTO> searchResult = IProductService.search(keyword, filters);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }
}
