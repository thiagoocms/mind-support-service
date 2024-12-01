package com.nassau.mind_support_service.service.impl;

import com.nassau.mind_support_service.domain.UserGame;
import com.nassau.mind_support_service.dto.user.UserGameDTO;
import com.nassau.mind_support_service.exception.ResourceNotFoundException;
import com.nassau.mind_support_service.mapper.UserGameMapper;
import com.nassau.mind_support_service.repository.UserGameRepository;
import com.nassau.mind_support_service.service.UserGameService;
import com.nassau.mind_support_service.validation.UserGameValidation;
import org.springframework.beans.factory.annotation.Autowired;

public class UserGameServiceImpl implements UserGameService {

    private final UserGameRepository userGameRepository;
    private final UserGameValidation userGameValidation;

    @Autowired
    public UserGameServiceImpl(UserGameRepository userGameRepository, UserGameValidation userGameValidation) {
        this.userGameRepository = userGameRepository;
        this.userGameValidation = userGameValidation;
    }

    @Override
    public UserGameDTO create(UserGameDTO dto) throws Throwable {
        UserGame entity = UserGameMapper.toEntity(dto);
        userGameValidation.checkOwnerFieldsToCreate(entity);
        userGameValidation.checkMandatoryFields(entity);
        userGameValidation.checkRelations(entity);
        userGameRepository.save(entity);
        return UserGameMapper.toDTO(entity);
    }

    @Override
    public UserGameDTO findByUserId(Long userId) {
        UserGame userGame = userGameRepository.findFirstByUserId(userId);
        if (userGame == null) {
            throw new ResourceNotFoundException("Usuario game nao encontrado");
        }
        return UserGameMapper.toDTO(userGame);
    }
}
