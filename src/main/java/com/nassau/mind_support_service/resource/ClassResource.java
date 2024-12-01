package com.nassau.mind_support_service.resource;


import com.nassau.mind_support_service.constants.AppConstants;
import com.nassau.mind_support_service.dto.classes.ClassDTO;
import com.nassau.mind_support_service.dto.classes.ClassFilterDTO;
import com.nassau.mind_support_service.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AppConstants.PATH + AppConstants.API + AppConstants.V1 + "/classes")
public class ClassResource {

    private final ClassService classService;

    @Autowired
    public ClassResource(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping
    public ResponseEntity<ClassDTO> create(@RequestBody ClassDTO dto) throws Throwable {
        dto = classService.create(dto);
        return ResponseEntity
                .created(URI.create("/classes"))
                .body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ClassDTO>> findAll(ClassFilterDTO dto) throws Throwable {
        List<ClassDTO> classDTOList = classService.findAll(dto);
        return ResponseEntity
                .ok()
                .body(classDTOList);
    }
}