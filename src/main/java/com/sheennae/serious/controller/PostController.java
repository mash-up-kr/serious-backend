package com.sheennae.serious.controller;

import com.sheennae.serious.dao.*;
import com.sheennae.serious.model.post.PostModel;
import com.sheennae.serious.model.post.command.PostCommand;
import com.sheennae.serious.model.reaction.SubjectPostReactionModel;
import com.sheennae.serious.model.subject.SubjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/post")
public class PostController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final SubjectRepository subjectRepository;
    private final SubjectPostReactionRepository subjectPostReactionRepository;
    private final SubjectReactionRepository subjectReactionRepository;

    @Autowired
    public PostController(UserRepository userRepository,
                          PostRepository postRepository,
                          SubjectRepository subjectRepository,
                          SubjectPostReactionRepository subjectPostReactionRepository,
                          SubjectReactionRepository subjectReactionRepository) {

        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.subjectRepository = subjectRepository;
        this.subjectPostReactionRepository = subjectPostReactionRepository;
        this.subjectReactionRepository = subjectReactionRepository;

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
        post = postRepository.save(post);

        SubjectPostReactionModel subjectPostReaction = new SubjectPostReactionModel();

        subjectPostReaction.setPost(post);
        subjectPostReaction.setReactedTime(LocalDateTime.now());
        subjectPostReaction.setSubject(subjectModel);
        System.out.println("getType : " + command.getReaction());
        subjectPostReaction.setSubjectReaction(subjectReactionRepository.findByReaction(command.getReaction()).get());
        subjectPostReactionRepository.save(subjectPostReaction);

        return post;
    }

}
