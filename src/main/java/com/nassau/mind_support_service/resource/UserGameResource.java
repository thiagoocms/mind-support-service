package com.nassau.mind_support_service.resource;

import com.nassau.mind_support_service.constants.AppConstants;
import com.nassau.mind_support_service.dto.user.UserGameDTO;
import com.nassau.mind_support_service.service.UserGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = AppConstants.PATH + AppConstants.API + AppConstants.V1 + "/users-game")
public class UserGameResource {

    private final UserGameService userGameService;

    @Autowired
    public UserGameResource(UserGameService userGameService) {
        this.userGameService = userGameService;
    }

    @PostMapping
    public ResponseEntity<UserGameDTO> create(@RequestBody UserGameDTO dto) throws Throwable {
        dto = userGameService.create(dto);
        return ResponseEntity
                .created(URI.create("/users-game"))
                .body(dto);
    }

    @GetMapping
    public ResponseEntity<UserGameDTO> findByUserId(@RequestParam(name = "userId", required = true) Long userId) throws Throwable {
        UserGameDTO dto = userGameService.findByUserId(userId);
        return ResponseEntity
                .ok()
                .body(dto);
    }

}
