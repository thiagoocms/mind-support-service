package com.nassau.mind_support_service.repository;


import com.nassau.mind_support_service.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findFirstById(Long id);
}
