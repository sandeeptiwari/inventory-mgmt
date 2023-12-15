package com.app.inventorymgmt.utils;

import java.util.Base64;

public interface AppConstants {
    String STATUS_201 = "201";
    String MESSAGE_201 = "Product is added successfully.";

    String USER_MESSAGE_201 = "User is registered successfully.";

    String AUTHORIZATION = "Authorization";
    String STATUS_200 = "200";

    String PRODUCT_DELETE_MESSAGE_200 = "Product is deleted successfully.";

    String STATUS_202 = "202";

    String PRODUCT_UPDATE_MESSAGE_200 = "Product is updated successfully.";

    String ADMIN_ROLE = "ADMIN";
    String COMMA_DELIMITER = ",";

    String PARAM_NAME = "keyword";

    static String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    static String decode(String value) {
        return new String(Base64.getDecoder().decode(value));
    }

}
