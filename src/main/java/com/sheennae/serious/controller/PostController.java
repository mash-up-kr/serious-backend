package com.sheennae.serious.controller;

import com.sheennae.serious.dao.PostRepository;
import com.sheennae.serious.dao.PostVoteRepository;
import com.sheennae.serious.dao.SubjectRepository;
import com.sheennae.serious.dao.UserRepository;
import com.sheennae.serious.model.post.PostModel;
import com.sheennae.serious.model.post.PostVoteModel;
import com.sheennae.serious.model.post.command.PostCommand;
import com.sheennae.serious.model.subject.SubjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/post")
public class PostController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostVoteRepository postVoteRepository;
    private final SubjectRepository subjectRepository;


    @Autowired
    public PostController(UserRepository userRepository, PostRepository postRepository, PostVoteRepository postVoteRepository, SubjectRepository subjectRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postVoteRepository = postVoteRepository;
        this.subjectRepository = subjectRepository;
    }


    @PostMapping("/{subjectId}")
    public @ResponseBody PostModel create(@RequestHeader(value = "userId") String userId,
                                          @PathVariable String subjectId,
                                          @RequestBody PostCommand command) {

        System.out.println(subjectId);

        PostModel post = new PostModel();
        post.setTitle(command.getTitle());
        post.setContents(command.getContents());

        SubjectModel subjectModel = subjectRepository.findOne(Integer.parseInt(subjectId));
        if (subjectModel != null) {
            post.setSubject(subjectModel);
        }

        post.setUser(userRepository.findOne(Integer.parseInt(userId)));
        post.setEnableChat(command.isEnableChat());
        Optional<PostVoteModel> postVoteModel = postVoteRepository.findPostVoteByType(command.getVote());
        if (postVoteModel.isPresent()) {
            post.setVote(postVoteModel.get());
        }

        postRepository.save(post);

        return post;
    }

}
