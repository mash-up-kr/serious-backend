package com.sheennae.serious.dao;

import com.sheennae.serious.model.post.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Integer> {

    List<PostModel> findPostByAuthor(int authorId);
    List<PostModel> findPostBySubject(int subjectId);

    @Query(value = "select * from post where user_id = :authorId and subject_id = :subjectId", nativeQuery = true)
    Optional<PostModel> findPostByAuthorAndSubject(@Param("authorId") int authorId, @Param("subjectId") int subjectId);

}
