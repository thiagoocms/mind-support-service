package com.nassau.mind_support_service.service;

import com.nassau.mind_support_service.dto.user.UserGameDTO;

public interface UserGameService {

    UserGameDTO create(UserGameDTO dto) throws Throwable;

    UserGameDTO findByUserId(Long userId);
}
