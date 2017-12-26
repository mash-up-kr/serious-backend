package com.sheennae.serious.dao;

import com.sheennae.serious.model.reaction.SubjectPostReactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectPostReactionRepository extends JpaRepository<SubjectPostReactionModel, Integer> {

    Optional<SubjectPostReactionModel> findById(int id);

}
