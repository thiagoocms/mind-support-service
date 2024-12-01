package com.nassau.mind_support_service.validation;

import com.nassau.mind_support_service.domain.Session;
import com.nassau.mind_support_service.domain.User;
import com.nassau.mind_support_service.domain.Vote;
import com.nassau.mind_support_service.exception.BadRequestException;
import com.nassau.mind_support_service.exception.ResourceNotFoundException;
import com.nassau.mind_support_service.repository.SessionRepository;
import com.nassau.mind_support_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class VoteValidation {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public VoteValidation(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public void checkOwnerFieldsToCreate(Vote entity) throws IOException {
        entity.setId(null);
    }

    public void checkMandatoryFields(Vote entity) throws IOException {
        List<String> notInformedFieldsList = new ArrayList<>();

        if (Objects.isNull(entity.getType())) {
            notInformedFieldsList.add("Tipo");
        }

        if (Objects.isNull(entity.getUser()) || Objects.isNull(entity.getUser().getId())) {
            notInformedFieldsList.add("Id do usuario");
        }

        if (Objects.isNull(entity.getSession()) || Objects.isNull(entity.getSession().getId())) {
            notInformedFieldsList.add("Id da sessao");
        }

        if (Objects.isNull(entity.getResponses()) || entity.getResponses().isEmpty()) {
            notInformedFieldsList.add("Respostas");
        }

        if (!notInformedFieldsList.isEmpty()) {
            throw new BadRequestException("Campos obrigatórios não informados: " + String.join(", ", notInformedFieldsList));
        }
    }

    public Vote checkRelations(Vote toPersistEntity) throws Throwable {
        this.checkUser(toPersistEntity);
        this.checkSession(toPersistEntity);
        return toPersistEntity;
    }

    private Vote checkUser(Vote entity) {
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

    private Vote checkSession(Vote entity) {
        if (Objects.isNull(entity.getSession())) {
            return entity;
        }

        if (Objects.isNull(entity.getSession().getId())) {
            entity.setSession(null);
            return entity;
        }

        Session foreKey = this.sessionRepository.findFirstById(entity.getUser().getId());

        if (foreKey == null) {
            throw new ResourceNotFoundException("Sessao nao encontrada");
        }

        entity.setSession(foreKey);

        return entity;
    }
}