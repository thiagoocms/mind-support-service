package com.nassau.mind_support_service.service.impl;

import com.nassau.mind_support_service.domain.Session;
import com.nassau.mind_support_service.dto.SessionDTO;
import com.nassau.mind_support_service.mapper.SessionMapper;
import com.nassau.mind_support_service.repository.SessionRepository;
import com.nassau.mind_support_service.service.SessionService;
import com.nassau.mind_support_service.validation.SessionValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final SessionValidation sessionValidation;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository, SessionValidation sessionValidation) {
        this.sessionRepository = sessionRepository;
        this.sessionValidation = sessionValidation;
    }

    @Override
    public SessionDTO create(SessionDTO dto) throws Throwable {
        Session entity = SessionMapper.toEntity(dto);
        sessionValidation.checkOwnerFieldsToCreate(entity);
        sessionValidation.checkMandatoryFields(entity);
        sessionValidation.checkRelations(entity);
        entity.setHash(UUID.randomUUID().toString());
        sessionRepository.save(entity);
        return SessionMapper.toDTO(entity);
    }
}
