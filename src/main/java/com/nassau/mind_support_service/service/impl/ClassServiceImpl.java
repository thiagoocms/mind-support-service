package com.nassau.mind_support_service.service.impl;

import com.nassau.mind_support_service.domain.Class;
import com.nassau.mind_support_service.dto.classes.ClassDTO;
import com.nassau.mind_support_service.dto.classes.ClassFilterDTO;
import com.nassau.mind_support_service.mapper.ClassMapper;
import com.nassau.mind_support_service.repository.ClassRepository;
import com.nassau.mind_support_service.service.ClassService;
import com.nassau.mind_support_service.validation.ClassValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final ClassValidation classValidation;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository, ClassValidation classValidation) {
        this.classRepository = classRepository;
        this.classValidation = classValidation;
    }

    @Override
    public ClassDTO create(ClassDTO dto) throws Throwable {
        Class entity = ClassMapper.toEntity(dto);
        classValidation.checkOwnerFieldsToCreate(entity);
        classValidation.checkMandatoryFields(entity);
        classValidation.checkRelations(entity);
        classRepository.save(entity);
        return ClassMapper.toDTO(entity);
    }

    @Override
    public List<ClassDTO> findAll(ClassFilterDTO dto) {
        List<Class> classList = classRepository.findByFilter(dto);
        return classList.stream().map(ClassMapper::toDTO).toList();
    }
}
