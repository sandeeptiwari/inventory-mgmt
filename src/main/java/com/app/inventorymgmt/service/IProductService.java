package com.app.inventorymgmt.service;

import com.app.inventorymgmt.domain.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    /**
     *
     * @param productDTO
     */
    void addProduct(ProductDTO productDTO);

    void deleteProduct(Long productId);

    void updateProduct(Long id, ProductDTO productDTO);

    List<ProductDTO> search(String keyword, List<String> filters);
}
