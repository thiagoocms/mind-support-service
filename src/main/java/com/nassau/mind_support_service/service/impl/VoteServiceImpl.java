package com.nassau.mind_support_service.service.impl;

import com.nassau.mind_support_service.domain.Vote;
import com.nassau.mind_support_service.dto.vote.VoteDTO;
import com.nassau.mind_support_service.dto.vote.VoteFilterDTO;
import com.nassau.mind_support_service.mapper.VoteMapper;
import com.nassau.mind_support_service.repository.ResponseRepository;
import com.nassau.mind_support_service.repository.VoteRepository;
import com.nassau.mind_support_service.service.VoteService;
import com.nassau.mind_support_service.validation.VoteValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final ResponseRepository responseRepository;
    private final VoteValidation voteValidation;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, ResponseRepository responseRepository, VoteValidation voteValidation) {
        this.voteRepository = voteRepository;
        this.responseRepository = responseRepository;
        this.voteValidation = voteValidation;
    }

    @Override
    public VoteDTO create(VoteDTO dto) throws Throwable {
        Vote entity = VoteMapper.toEntity(dto);
        voteValidation.checkOwnerFieldsToCreate(entity);
        voteValidation.checkMandatoryFields(entity);
        voteValidation.checkRelations(entity);
        voteRepository.save(entity);
        createResponses(entity);
        return VoteMapper.toDTO(entity);
    }

    @Override
    public List<VoteDTO> findAllByFilters(VoteFilterDTO filter) {
        List<Vote> voteList = voteRepository.findByFilter(filter);
        return voteList.stream().map(VoteMapper::toDTO).toList();
    }

    private Vote createResponses(Vote entity) {
        entity.getResponses().forEach(item -> {
            item.setId(null);
            item.setVote(entity);
            responseRepository.save(item);
        });
        return entity;
    }
}
