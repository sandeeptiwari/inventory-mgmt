package com.app.inventorymgmt.service.filter;

import com.app.inventorymgmt.domain.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CategoryBasedFilter implements IFilter {

    private String category;

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public boolean filter(ProductDTO productDTO) {
        return Objects.equals(category, productDTO.category());
    }
}
