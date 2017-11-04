package com.sheennae.serious.dao;

import com.sheennae.serious.model.post.PostVote;
import com.sheennae.serious.model.post.PostVoteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PostVoteRepository extends JpaRepository<PostVoteModel, Integer> {

    Optional<PostVoteModel> findPostVoteByType(PostVote type);
}
