package com.sheennae.serious.dao;

import com.sheennae.serious.model.post.PostVote;
import com.sheennae.serious.model.post.PostVoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PostVoteRepository extends JpaRepository<PostVoteModel, Integer> {

    Optional<PostVoteModel> findPostVoteByType(PostVote type);
}
