package com.nassau.mind_support_service.repository;

import com.nassau.mind_support_service.domain.Class;
import com.nassau.mind_support_service.dto.classes.ClassFilterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

    @Query("SELECT c FROM Class c WHERE :#{#filter.userId} IS NULL OR c.user.id = :#{#filter.userId}")
    List<Class> findByFilter(@Param("filter") ClassFilterDTO filter);

    Class findFirstById(Long id);
}
