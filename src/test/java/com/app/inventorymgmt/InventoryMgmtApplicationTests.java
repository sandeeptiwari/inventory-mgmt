package com.app.inventorymgmt;

import com.app.inventorymgmt.domain.entity.enums.Category;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class InventoryMgmtApplicationTests {

    public static void main(String[] args) {
        String a = "ele";
        Category category = Category.getByName(a);
        System.out.println(category);
    }

}
