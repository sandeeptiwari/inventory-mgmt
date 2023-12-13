package com.app.inventorymgmt.service;

import com.app.inventorymgmt.domain.dto.RegisterDTO;
import com.app.inventorymgmt.domain.dto.UserDTO;
import com.app.inventorymgmt.repository.UserRepository;

public interface IUserService {

    UserDTO registerUser(RegisterDTO registerDTO);

    String login(String emailId, String password);
}
