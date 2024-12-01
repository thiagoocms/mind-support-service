package com.nassau.mind_support_service.repository;

import com.nassau.mind_support_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    User findFirstByEmailAndPassword(String email, String password);

    User findFirstById(Long id);
}
