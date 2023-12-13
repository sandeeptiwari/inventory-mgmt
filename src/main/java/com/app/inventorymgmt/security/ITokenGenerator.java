package com.app.inventorymgmt.security;

public interface ITokenGenerator {

    String generateToken(String emailId);

    boolean verifyToken(String token);

    boolean isAdmin(String token);
}
