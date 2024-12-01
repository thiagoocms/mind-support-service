package com.nassau.mind_support_service.mapper;

import com.nassau.mind_support_service.domain.User;
import com.nassau.mind_support_service.domain.UserGame;
import com.nassau.mind_support_service.dto.user.UserGameDTO;


public class UserGameMapper {

    public static UserGame toEntity(UserGameDTO source) {
        UserGame target = new UserGame();
        target.setId(source.id());
        target.setNickname(source.nickname());
        target.setIcon(source.icon());
        target.setUser(new User(source.userId()));
        return target;
    }

    public static UserGameDTO toDTO(UserGame source) {
        return new UserGameDTO(
                source.getId(),
                source.getUser().getId(),
                source.getNickname(),
                source.getIcon()

        );
    }
}
