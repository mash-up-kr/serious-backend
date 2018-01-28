package com.sheennae.serious.dao;

import com.sheennae.serious.model.reaction.PostReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostReactionRespository extends JpaRepository<PostReaction, Integer> {

    @Query(value = "select * from post_reaction where reaction = :reaction", nativeQuery = true)
    Optional<PostReaction> findByReaction(@Param("reaction") String reaction);

}
