package com.app.inventorymgmt.controller;

import com.app.inventorymgmt.domain.dto.LoginDTO;
import com.app.inventorymgmt.domain.dto.RegisterDTO;
import com.app.inventorymgmt.service.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.app.inventorymgmt.utils.AppConstants.AUTHORIZATION;

@RestController
@RequestMapping(value="/inv/api/v0")
public class UserMgmtRestController {

    private final IUserService userService;

    public UserMgmtRestController(IUserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerUser(registerDTO));
    }


    @RequestMapping("/user")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        String userToken = userService.login(loginDTO.getEmailId(), loginDTO.getPassword());
        response.setHeader(AUTHORIZATION, userToken);
        return ResponseEntity.status(HttpStatus.OK)
                .body("");
    }

}
