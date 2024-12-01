package com.nassau.mind_support_service.service;

import com.nassau.mind_support_service.dto.vote.VoteDTO;
import com.nassau.mind_support_service.dto.vote.VoteFilterDTO;

import java.util.List;

public interface VoteService {

    VoteDTO create(VoteDTO dto) throws Throwable;

    List<VoteDTO> findAllByFilters(VoteFilterDTO filter);

}
