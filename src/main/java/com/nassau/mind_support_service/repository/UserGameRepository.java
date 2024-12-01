package com.nassau.mind_support_service.repository;


import com.nassau.mind_support_service.domain.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, Long> {

    UserGame findFirstById(Long id);
    UserGame findFirstByUserId(Long userId);
}
