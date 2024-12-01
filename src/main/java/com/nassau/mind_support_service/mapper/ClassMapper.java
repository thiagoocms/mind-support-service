package com.nassau.mind_support_service.mapper;

import com.nassau.mind_support_service.domain.Class;
import com.nassau.mind_support_service.domain.User;
import com.nassau.mind_support_service.dto.classes.ClassDTO;

public class ClassMapper {

    public static Class toEntity(ClassDTO source) {
        Class target = new Class();
        target.setId(source.id());
        target.setName(source.name());
        target.setUser(new User(source.userId()));
        return target;
    }

    public static ClassDTO toDTO(Class source) {
        return new ClassDTO(
                source.getId(),
                source.getName(),
                source.getUser().getId()
        );
    }
}
