package com.nassau.mind_support_service.dto.vote;

import com.nassau.mind_support_service.enumerated.VoteTypeEnum;

import java.util.Set;

public record VoteDTO(
        Long id,
        Long sessionId,
        Long userId,
        VoteTypeEnum type,
        Set<ResponseDTO> responses
) {
}
