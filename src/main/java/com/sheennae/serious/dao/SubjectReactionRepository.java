package com.sheennae.serious.dao;

import com.sheennae.serious.model.reaction.Reaction;
import com.sheennae.serious.model.reaction.SubjectReactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectReactionRepository extends JpaRepository<SubjectReactionModel, Integer>{

//    @Query(value = "select * from subject_reaction where reaction_type = :type", nativeQuery = true)
//    Optional<SubjectReactionModel> findByReactionType(@Param("type") String type);
    Optional<SubjectReactionModel> findByReaction(Reaction reaction);

}
