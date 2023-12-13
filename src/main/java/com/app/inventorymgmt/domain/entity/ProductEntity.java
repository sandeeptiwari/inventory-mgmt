package com.app.inventorymgmt.domain.entity;

import com.app.inventorymgmt.domain.entity.enums.Brand;
import com.app.inventorymgmt.domain.entity.enums.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends BaseEntity {

    private String name;

    private String category;

    private String brand;


    private double price;

    private int quantity;
}
