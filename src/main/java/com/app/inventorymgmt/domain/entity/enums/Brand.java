package com.app.inventorymgmt.domain.entity.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Brand {

    PHILIPS("ph"),
    SAMSUNG("smsn"),
    PUMA("puma"),
    NIKE("nike"),
    TECH("book");

    private final String brand;
    private static final Map<String, Brand> BRAND_MAP;

    static {
        BRAND_MAP = new HashMap<>();
        for (Brand brand : values()) {
            BRAND_MAP.put(brand.getBrand(), brand);
        }
    }

    Brand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public static boolean isBrand(String name) {
        return getByName(name) != null;
    }

    public static Brand getByName(String name) {
        return BRAND_MAP.get(name);
    }
}
