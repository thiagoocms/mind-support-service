package com.nassau.mind_support_service.mapper;

import com.nassau.mind_support_service.domain.User;
import com.nassau.mind_support_service.dto.user.UserDTO;
import com.nassau.mind_support_service.dto.user.UserLoginDTO;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class UserMapper {

    public static User toEntity(UserDTO source) {
        User target = new User();
        target.setId(source.id());
        target.setFirstName(source.firstName());
        target.setLastName(source.lastName());
        target.setBirthday(toLocalDate(source.birthday()));
        target.setEmail(source.email());
        target.setPassword(source.password());
        target.setGender(source.gender());
        target.setProfile(source.profile());
        return target;
    }

    public static User toEntity(UserLoginDTO source) {
        User target = new User();
        target.setEmail(source.email());
        target.setPassword(source.password());
        return target;
    }

    public static UserDTO toDTO(User source) {
        return new UserDTO(
                source.getId(),
                source.getFirstName(),
                source.getLastName(),
                toEpochMilli(source.getBirthday()),
                source.getEmail(),
                source.getPassword(),
                source.getGender(),
                source.getProfile()
        );
    }

    private static LocalDate toLocalDate(Long source) {
        try {
            return Instant.ofEpochMilli(source).atZone(ZoneOffset.UTC).toLocalDate();
        } catch (Exception e) {
            return null;
        }
    }

    private static Long toEpochMilli(LocalDate source) {
        try {
            return source.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
        } catch (Exception e) {
            return null;
        }
    }
}
