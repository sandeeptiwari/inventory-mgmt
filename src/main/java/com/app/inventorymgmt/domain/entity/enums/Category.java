package com.app.inventorymgmt.domain.entity.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Category {

    ELECTRONICS("ele"),
    CLOTHING("cloth"),
    BOOKS("book");

    private final String category;

    private static final Map<String, Category> CATEGORY_MAP;

    static {
        CATEGORY_MAP = new HashMap<>();
        for (Category category : values()) {
            CATEGORY_MAP.put(category.getCategory(), category);
        }
    }

    Category(String category) {
        this.category = category;
    }

    public static boolean isCategory(String filterKey) {
        return getByName(filterKey) != null;
    }

    public String getCategory() {
        return category;
    }

    public static Category getByName(String name) {
        return CATEGORY_MAP.get(name);
    }
}
