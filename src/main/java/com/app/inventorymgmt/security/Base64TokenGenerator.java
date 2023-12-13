package com.app.inventorymgmt.security;

import com.app.inventorymgmt.domain.entity.Role;
import com.app.inventorymgmt.domain.entity.User;
import com.app.inventorymgmt.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Base64TokenGenerator implements ITokenGenerator {

    private final UserRepository userRepository;

    public Base64TokenGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String generateToken(String emailId) {
        Optional<User> user = userRepository.findByEmailId(emailId);
        String roles = user.map(u -> u.getRoles().stream().map(Role::getName).collect(Collectors.joining(",")))
                .orElse("");
        return Base64.getEncoder().encodeToString(roles.getBytes());
    }

    @Override
    public boolean verifyToken(String token) {
        return true;
    }

    @Override
    public boolean isAdmin(String token) {
        boolean isAdmin = false;
        if (token != null && !token.isEmpty()) {
            String roles = new String(Base64.getDecoder().decode(token));
            isAdmin = roles.contains("ADMIN");
        }
        return isAdmin;
    }
}
