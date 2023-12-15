package com.app.inventorymgmt.service;

import com.app.inventorymgmt.domain.dto.ProductDTO;
import com.app.inventorymgmt.domain.entity.ProductEntity;
import com.app.inventorymgmt.domain.entity.enums.Brand;
import com.app.inventorymgmt.domain.entity.enums.Category;
import com.app.inventorymgmt.repository.ProductRepository;
import com.app.inventorymgmt.service.filter.BrandBasedFilter;
import com.app.inventorymgmt.service.filter.CategoryBasedFilter;
import com.app.inventorymgmt.service.filter.IFilter;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    private final BrandBasedFilter brandBasedFilter;

    private final CategoryBasedFilter categoryBasedFilter;

    private final INotification notification;


    public ProductServiceImpl(ProductRepository productRepository,
                              BrandBasedFilter brandBasedFilter,
                              CategoryBasedFilter categoryBasedFilter,
                              INotification notification) {
        this.productRepository = productRepository;
        this.brandBasedFilter = brandBasedFilter;
        this.categoryBasedFilter = categoryBasedFilter;
        this.notification = notification;
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDTO.name());
        productEntity.setQuantity(productDTO.quantity());
        productEntity.setPrice(productDTO.price());
        productEntity.setCategory(productDTO.category());
        productEntity.setBrand(productDTO.brand());
        notification.sendNotification(productRepository.save(productEntity));
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void updateProduct(Long id, ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDTO.name());
        productEntity.setQuantity(productDTO.quantity());
        productEntity.setPrice(productDTO.price());
        productEntity.setCategory(productDTO.category());
        productEntity.setBrand(productDTO.brand());
        productRepository.save(productEntity);
    }

    @Override
    public List<ProductDTO> search(String keyword, List<String> filters) {
        List<IFilter> filterList = (filters != null && !filters.isEmpty()) ? getFilterList(filters) : Collections.emptyList();
        List<ProductDTO> products = productRepository.findProducts(keyword.toLowerCase())
                .stream()
                .map(productEntity -> ProductDTO.builder()
                        .id(productEntity.getId())
                        .name(productEntity.getName())
                        .price(productEntity.getPrice())
                        .category(productEntity.getCategory())
                        .brand(productEntity.getBrand())
                        .build()).toList();

        return products.stream()
                .flatMap(product -> { // FlatMap combines results from applying filter to each product
                    if (filterList.stream().allMatch(filter -> filter.filter(product))) {
                        // All filters must pass for product to be included
                        return Stream.of(product);
                    } else {
                        return Stream.empty();
                    }
                })
                .collect(Collectors.toList());
    }


    private List<IFilter> getFilterList(List<String> filters) {
        return filters.stream()
                .map(filterKey -> {
                    if (Brand.isBrand(filterKey)) {
                        brandBasedFilter.setBrand(filterKey);
                        return brandBasedFilter;
                    } else if (Category.isCategory(filterKey)) {
                        categoryBasedFilter.setCategory(filterKey);
                        return categoryBasedFilter;
                    }
                    return null;
                }).filter(Objects::nonNull).toList();
    }

}
