package com.app.inventorymgmt.service.filter;

import com.app.inventorymgmt.domain.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BrandBasedFilter implements IFilter {

    private String brand;


    public void setBrand(String brand) {
        this.brand = brand;
    }


    @Override
    public boolean filter(ProductDTO productDTO) {
        return Objects.equals(brand, productDTO.brand());
    }
}
