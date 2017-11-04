package com.sheennae.serious.dao;

import com.sheennae.serious.model.post.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostModel, Integer> {

    List<PostModel> findPostByUserId(int userId);
}
