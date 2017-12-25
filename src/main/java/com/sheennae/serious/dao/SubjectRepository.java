package com.sheennae.serious.dao;

import com.sheennae.serious.model.subject.SubjectModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Predicate;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectModel, Integer> {

    Optional<SubjectModel> findByTitle(String title);

    @Query(value = "select * from subject where date(subject.created_at) = :date", nativeQuery = true)
    Optional<SubjectModel> findByCreatedAt(@Param("date") String date);

}
