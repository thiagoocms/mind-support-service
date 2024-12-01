package com.nassau.mind_support_service.repository;


import com.nassau.mind_support_service.domain.Vote;
import com.nassau.mind_support_service.dto.vote.VoteFilterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findFirstById(Long id);

    @Query("SELECT v FROM Vote v WHERE (:#{#filter.userId} IS NULL OR v.user.id = :#{#filter.userId}) AND " +
            "(:#{#filter.sessionId} IS NULL OR v.session.id = :#{#filter.sessionId})")
    List<Vote> findByFilter(@Param("filter") VoteFilterDTO filter);
}
