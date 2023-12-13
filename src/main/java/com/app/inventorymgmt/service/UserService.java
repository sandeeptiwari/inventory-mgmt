package com.app.inventorymgmt.service;

import com.app.inventorymgmt.domain.dto.RegisterDTO;
import com.app.inventorymgmt.domain.dto.UserDTO;
import com.app.inventorymgmt.domain.entity.Role;
import com.app.inventorymgmt.domain.entity.User;
import com.app.inventorymgmt.domain.entity.enums.UserType;
import com.app.inventorymgmt.repository.UserRepository;
import com.app.inventorymgmt.security.ITokenGenerator;
import com.app.inventorymgmt.utils.AppConstants;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ITokenGenerator tokenGenerator;

    public UserService(UserRepository userRepository, ITokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public UserDTO registerUser(RegisterDTO registerDTO) {
        User entity = User.builder()
                .name(registerDTO.getName())
                .emailId(registerDTO.getEmailId())
                .password(AppConstants.encode(registerDTO.getPassword()))
                .userType(UserType.valueOf(registerDTO.getUserType()))
                .phone(registerDTO.getPhone())
                .build();

        Role studentRole = new Role(UserType.ADMIN.name());
        Set<Role> roles = new HashSet<>();
        roles.add(studentRole);
        entity.setRoles(roles);


        var user =  userRepository.save(entity);

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .emailId(user.getEmailId())
                .build();
    }

    @Override
    public String login(String emailId, String password) {
        var user =  userRepository.findByEmailIdAndPassword(emailId, AppConstants.encode(password));
        return user.map(u -> tokenGenerator.generateToken(u.getEmailId())).orElse("");
    }
}
