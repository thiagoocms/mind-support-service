package com.nassau.mind_support_service.dto.user;

public record UserGameDTO(
        Long id,
        Long userId,
        String nickname,
        String icon
) {
}
