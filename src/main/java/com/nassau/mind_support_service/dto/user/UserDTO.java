package com.nassau.mind_support_service.dto.user;

import com.nassau.mind_support_service.enumerated.UserGenderEnum;
import com.nassau.mind_support_service.enumerated.UserProfileEnum;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        Long birthday,
        String email,
        String password,
        UserGenderEnum gender,
        UserProfileEnum profile
) {
}
