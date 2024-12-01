package com.nassau.mind_support_service.validation;

import com.nassau.mind_support_service.domain.User;
import com.nassau.mind_support_service.domain.UserGame;
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
public class UserGameValidation {

    private final UserRepository userRepository;

    @Autowired
    public UserGameValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkOwnerFieldsToCreate(UserGame entity) {
        entity.setId(null);
    }

    public void checkMandatoryFields(UserGame entity) throws IOException {
        List<String> notInformedFieldsList = new ArrayList<>();

        if (Objects.isNull(entity.getNickname()) || entity.getNickname().isEmpty()) {
            notInformedFieldsList.add("Nickname");
        }

        if (Objects.isNull(entity.getIcon()) || entity.getIcon().isEmpty()) {
            notInformedFieldsList.add("Icon");
        }

        if (Objects.isNull(entity.getUser()) || Objects.isNull(entity.getUser().getId())) {
            notInformedFieldsList.add("Id do usuario");
        }

        if (!notInformedFieldsList.isEmpty()) {
            throw new BadRequestException("Campos obrigatórios não informados: " + String.join(", ", notInformedFieldsList));
        }
    }

    public UserGame checkRelations(UserGame toPersistEntity) throws Throwable {
        this.checkUser(toPersistEntity);
        return toPersistEntity;
    }

    private UserGame checkUser(UserGame entity) {
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