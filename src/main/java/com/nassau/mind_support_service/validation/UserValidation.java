package com.nassau.mind_support_service.validation;

import com.nassau.mind_support_service.domain.User;
import com.nassau.mind_support_service.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserValidation {

    public void checkOwnerFieldsToCreate(User entity) throws IOException {
        entity.setId(null);
    }

    public void checkMandatoryFields(User entity) throws IOException {
        List<String> notInformedFieldsList = new ArrayList<>();

        if (Objects.isNull(entity.getFirstName()) || entity.getFirstName().isEmpty()) {
            notInformedFieldsList.add("Nome");
        }
        if (Objects.isNull(entity.getLastName()) || entity.getLastName().isEmpty()) {
            notInformedFieldsList.add("Sobrenome");
        }
        if (Objects.isNull(entity.getBirthday())) {
            notInformedFieldsList.add("Data de aniversario");
        }
        if (Objects.isNull(entity.getPassword()) || entity.getPassword().isEmpty()) {
            notInformedFieldsList.add("Senha");
        }
        if (Objects.isNull(entity.getEmail()) || entity.getEmail().isEmpty()) {
            notInformedFieldsList.add("E-mail");
        }
        if (Objects.isNull(entity.getProfile())) {
            notInformedFieldsList.add("Perfil");
        }

        if (!notInformedFieldsList.isEmpty()) {
            throw new BadRequestException("Campos obrigatórios não informados: " + String.join(", ", notInformedFieldsList));
        }
    }

    public void checkLoginFields(User entity) {
        if (Objects.isNull(entity.getEmail()) || entity.getEmail().isEmpty()) {
            throw new BadRequestException("O campo E-mail e obrigatório.");
        }

        if ((Objects.isNull(entity.getPassword()) || entity.getPassword().isEmpty())) {
            throw new BadRequestException("O campo Senha e obrigatório.");
        }
    }
}