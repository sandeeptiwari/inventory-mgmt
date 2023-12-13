package com.app.inventorymgmt.service.filter;

import com.app.inventorymgmt.domain.dto.ProductDTO;

public interface IFilter {

    boolean filter(ProductDTO productDTO);
}
