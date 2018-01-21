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
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectModel, Integer> {

    Optional<SubjectModel> findByTitle(String title);

    @Query(value = "select * from subject where date(subject.published_at) = date(:date) limit 1", nativeQuery = true)
    Optional<SubjectModel> findByPublishedAt(@Param("date") String date);


    @Query(value = "select * from subject where year(subject.published_at) = :year and month(subject.published_at) = :month order by subject.published_at", nativeQuery = true)
    List<SubjectModel> findByMonth(@Param("year") int year, @Param("month") int month);


    @Query(value = "select * from subject where date(subject.published_at) = date(:publishedAt) limit 1", nativeQuery = true)
    Optional<SubjectModel> findToday(@Param("publishedAt") String publishedAt);

    Optional<SubjectModel> findById(int id);
}
