package com.nassau.mind_support_service.mapper;

import com.nassau.mind_support_service.domain.Response;
import com.nassau.mind_support_service.domain.Session;
import com.nassau.mind_support_service.domain.User;
import com.nassau.mind_support_service.domain.Vote;
import com.nassau.mind_support_service.dto.vote.ResponseDTO;
import com.nassau.mind_support_service.dto.vote.VoteDTO;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class VoteMapper {

    public static Vote toEntity(VoteDTO source) {
        Vote target = new Vote();
        target.setId(source.id());
        target.setType(source.type());
        target.setSession(new Session(source.sessionId()));
        target.setUser(new User(source.userId()));
        if (Objects.nonNull(source.responses()) && !source.responses().isEmpty()) {
            source.responses().forEach(item -> {
                target.getResponses().add(new Response(item.quest(), item.response()));
            });
        }
        return target;
    }

    public static VoteDTO toDTO(Vote source) {
        Set<ResponseDTO> responseDTOS = new HashSet<>();
        if (Objects.nonNull(source.getResponses()) && !source.getResponses().isEmpty()) {
            source.getResponses().forEach(item -> {
                responseDTOS.add(new ResponseDTO(item.getQuest(), item.getResponse()));
            });
        }
        return new VoteDTO(
                source.getId(),
                source.getUser().getId(),
                source.getSession().getId(),
                source.getType(),
                responseDTOS
        );
    }
}
