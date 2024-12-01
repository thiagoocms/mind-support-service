package com.nassau.mind_support_service.service;

import com.nassau.mind_support_service.dto.SessionDTO;

public interface SessionService {

    SessionDTO create(SessionDTO dto) throws Throwable;

}
