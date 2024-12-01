package com.nassau.mind_support_service.service;

import com.nassau.mind_support_service.dto.classes.ClassDTO;
import com.nassau.mind_support_service.dto.classes.ClassFilterDTO;

import java.util.List;

public interface ClassService {

    ClassDTO create(ClassDTO dto) throws Throwable;

    List<ClassDTO> findAll(ClassFilterDTO dto);
}
