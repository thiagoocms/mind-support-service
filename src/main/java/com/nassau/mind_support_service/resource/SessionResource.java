package com.nassau.mind_support_service.resource;


import com.nassau.mind_support_service.constants.AppConstants;
import com.nassau.mind_support_service.dto.SessionDTO;
import com.nassau.mind_support_service.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = AppConstants.PATH + AppConstants.API + AppConstants.V1 + "/sessions")
public class SessionResource {

    private final SessionService sessionService;

    @Autowired
    public SessionResource(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<SessionDTO> create(@RequestBody SessionDTO dto) throws Throwable {
        dto = sessionService.create(dto);
        return ResponseEntity
                .created(URI.create("/sessions"))
                .body(dto);
    }

}