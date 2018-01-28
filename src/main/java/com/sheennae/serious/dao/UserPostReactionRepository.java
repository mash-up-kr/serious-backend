package com.sheennae.serious.dao;

import com.sheennae.serious.model.reaction.UserPostReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserPostReactionRepository extends JpaRepository<UserPostReaction, Integer> {

    @Modifying
    @Query(value = "delete from user_post_reaction where post_id = :postId and user_id = :userId and post_reaction_id = 1", nativeQuery = true)
    int deleteAgreeByPostIdAndUserId(@Param("postId") int postId, @Param("userId") int userId);

    @Modifying
    @Query(value = "delete from user_post_reaction where post_id = :postId and user_id = :userId and post_reaction_id = 3", nativeQuery = true)
    int deleteNeutralByPostIdAndUserId(@Param("postId") int postId, @Param("userId") int userId);

    @Modifying
    @Query(value = "delete from user_post_reaction where post_id = :postId and user_id = :userId and post_reaction_id = 2", nativeQuery = true)
    int deleteDisagreeByPostIdAndUserId(@Param("postId") int postId, @Param("userId") int userId);

}
