package com.nassau.mind_support_service.service;

import com.nassau.mind_support_service.dto.user.UserDTO;
import com.nassau.mind_support_service.dto.user.UserLoginDTO;

public interface UserService {

    UserDTO create(UserDTO dto) throws Throwable;

    UserDTO login(UserLoginDTO dto) throws Throwable;
}
