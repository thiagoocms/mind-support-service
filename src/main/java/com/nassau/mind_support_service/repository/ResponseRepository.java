package com.nassau.mind_support_service.repository;


import com.nassau.mind_support_service.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    Response findFirstById(Long id);
}
