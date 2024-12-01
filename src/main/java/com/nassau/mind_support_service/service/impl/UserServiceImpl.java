package com.nassau.mind_support_service.service.impl;

import com.nassau.mind_support_service.domain.User;
import com.nassau.mind_support_service.dto.user.UserDTO;
import com.nassau.mind_support_service.dto.user.UserLoginDTO;
import com.nassau.mind_support_service.exception.ResourceNotFoundException;
import com.nassau.mind_support_service.mapper.UserMapper;
import com.nassau.mind_support_service.repository.UserRepository;
import com.nassau.mind_support_service.service.UserService;
import com.nassau.mind_support_service.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidation userValidation;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserValidation userValidation) {
        this.userRepository = userRepository;
        this.userValidation = userValidation;
    }

    @Override
    public UserDTO create(UserDTO dto) throws Throwable {
        User user = UserMapper.toEntity(dto);
        userValidation.checkOwnerFieldsToCreate(user);
        userValidation.checkMandatoryFields(user);
        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO login(UserLoginDTO dto) throws Throwable {
        User user = UserMapper.toEntity(dto);
        userValidation.checkLoginFields(user);
        user = userRepository.findFirstByEmailAndPassword(user.getEmail(), user.getPassword());
        if (Objects.isNull(user)) {
            throw new ResourceNotFoundException("Login ou senha invalidos.");
        }
        return UserMapper.toDTO(user);
    }
}
