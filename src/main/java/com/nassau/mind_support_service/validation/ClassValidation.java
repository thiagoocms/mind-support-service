package com.nassau.mind_support_service.validation;

import com.nassau.mind_support_service.domain.Class;
import com.nassau.mind_support_service.domain.User;
import com.nassau.mind_support_service.exception.BadRequestException;
import com.nassau.mind_support_service.exception.ResourceNotFoundException;
import com.nassau.mind_support_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ClassValidation {

    private final UserRepository userRepository;

    @Autowired
    public ClassValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkOwnerFieldsToCreate(Class entity) throws IOException {
        entity.setId(null);
    }

    public void checkMandatoryFields(Class entity) throws IOException {
        List<String> notInformedFieldsList = new ArrayList<>();

        if (Objects.isNull(entity.getName()) || entity.getName().isEmpty()) {
            notInformedFieldsList.add("Nome");
        }

        if (Objects.isNull(entity.getUser()) || Objects.isNull(entity.getUser().getId())) {
            notInformedFieldsList.add("Id do usuario");
        }

        if (!notInformedFieldsList.isEmpty()) {
            throw new BadRequestException("Campos obrigatórios não informados: " + String.join(", ", notInformedFieldsList));
        }
    }

    public Class checkRelations(Class toPersistEntity) throws Throwable {
        this.checkUser(toPersistEntity);
        return toPersistEntity;
    }

    private Class checkUser(Class entity) {
        if (Objects.isNull(entity.getUser())) {
            return entity;
        }

        if (Objects.isNull(entity.getUser().getId())) {
            entity.setUser(null);
            return entity;
        }

        User foreKey = this.userRepository.findFirstById(entity.getUser().getId());

        if (foreKey == null) {
            throw new ResourceNotFoundException("Usuario nao encontrada");
        }

        entity.setUser(foreKey);

        return entity;
    }
}