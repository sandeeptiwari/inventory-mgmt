package com.app.inventorymgmt.repository;

import com.app.inventorymgmt.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailId(String emailId);

    Optional<User> findByEmailIdAndPassword(String emailId, String encodePassword);
}
