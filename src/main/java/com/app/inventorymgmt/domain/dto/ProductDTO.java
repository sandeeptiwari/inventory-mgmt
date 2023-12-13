package com.app.inventorymgmt.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        String name,
        String category,
        String brand,
        double price,
        int quantity
) {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProductDTO {
        // Empty constructor for Jackson
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String name;
        private String category;
        private String brand;
        private double price;
        private int quantity;

        public ProductDTO build() {
            return new ProductDTO(id, name, category, brand, price, quantity);
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }
    }
}
