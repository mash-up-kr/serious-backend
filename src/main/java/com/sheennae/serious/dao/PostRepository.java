package com.sheennae.serious.dao;

import com.sheennae.serious.model.post.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Integer> {

    List<PostModel> findPostByUserId(int userId);
    List<PostModel> findPostBySubjectId(int subjectId);

}
