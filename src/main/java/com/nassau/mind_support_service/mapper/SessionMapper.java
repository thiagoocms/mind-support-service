package com.nassau.mind_support_service.mapper;

import com.nassau.mind_support_service.domain.Class;
import com.nassau.mind_support_service.domain.Session;
import com.nassau.mind_support_service.dto.SessionDTO;

public class SessionMapper {

    public static Session toEntity(SessionDTO source) {
        Session target = new Session();
        target.setId(source.id());
        target.setHash(source.hash());
        target.setClazz(new Class(source.classId()));
        return target;
    }

    public static SessionDTO toDTO(Session source) {
        return new SessionDTO(
                source.getId(),
                source.getHash(),
                source.getClazz().getId()
        );
    }
}
