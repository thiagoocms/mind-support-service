package com.nassau.mind_support_service.resource;


import com.nassau.mind_support_service.constants.AppConstants;
import com.nassau.mind_support_service.dto.user.UserDTO;
import com.nassau.mind_support_service.dto.user.UserLoginDTO;
import com.nassau.mind_support_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = AppConstants.PATH + AppConstants.API + AppConstants.V1 + "/users")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) throws Throwable {
        dto = userService.create(dto);
        return ResponseEntity
                .created(URI.create("/users"))
                .body(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserLoginDTO dto) throws Throwable {
        UserDTO userDTO = userService.login(dto);
        return ResponseEntity
                .ok()
                .body(userDTO);
    }
}