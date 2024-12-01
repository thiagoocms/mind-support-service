package com.nassau.mind_support_service.resource;

import com.nassau.mind_support_service.constants.AppConstants;
import com.nassau.mind_support_service.dto.vote.VoteDTO;
import com.nassau.mind_support_service.dto.vote.VoteFilterDTO;
import com.nassau.mind_support_service.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AppConstants.PATH + AppConstants.API + AppConstants.V1 + "/votes")
public class VoteResource {

    private final VoteService voteService;

    @Autowired
    public VoteResource(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<VoteDTO> create(@RequestBody VoteDTO dto) throws Throwable {
        dto = voteService.create(dto);
        return ResponseEntity
                .created(URI.create("/votes"))
                .body(dto);
    }

    @GetMapping
    public ResponseEntity<List<VoteDTO>> findAllByFilters(VoteFilterDTO dto) throws Throwable {
        List<VoteDTO> list = voteService.findAllByFilters(dto);
        return ResponseEntity
                .ok()
                .body(list);
    }
}
