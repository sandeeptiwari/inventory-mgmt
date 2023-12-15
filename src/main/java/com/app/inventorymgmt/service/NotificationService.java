package com.app.inventorymgmt.service;

import com.app.inventorymgmt.domain.entity.ProductEntity;
import com.app.inventorymgmt.domain.entity.Role;
import com.app.inventorymgmt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.app.inventorymgmt.utils.AppConstants.ADMIN_ROLE;

@Service
public class NotificationService implements INotification {
    private final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    private final UserRepository userRepository;

    public NotificationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void sendNotification(ProductEntity productEntity) {
        userRepository.findAll()
                .stream()
                .filter(user -> !user.getRoles().stream().map(Role::getName).toList().contains(ADMIN_ROLE))
                .forEach(user -> {
                    LOGGER.info("Send Notification to user " + user.getName());
                });
    }
}
