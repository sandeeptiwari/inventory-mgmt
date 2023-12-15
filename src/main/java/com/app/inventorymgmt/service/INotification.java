package com.app.inventorymgmt.service;

import com.app.inventorymgmt.domain.entity.ProductEntity;

public interface INotification {

    void sendNotification(ProductEntity productEntity);
}
