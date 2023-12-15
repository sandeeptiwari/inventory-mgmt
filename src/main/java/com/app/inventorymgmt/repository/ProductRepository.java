package com.app.inventorymgmt.repository;

import com.app.inventorymgmt.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import static com.app.inventorymgmt.utils.AppConstants.PARAM_NAME;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p "
            + " WHERE LOWER(p.name) LIKE %:keyword%"
            + " OR LOWER(p.brand) LIKE %:keyword%"
            + " OR LOWER(p.category) LIKE %:keyword%"
            + " OR CONCAT(p.price, '') LIKE %:keyword%")
    List<ProductEntity> findProducts(@Param(PARAM_NAME) String keyword);
}
