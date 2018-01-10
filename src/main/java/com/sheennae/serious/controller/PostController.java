package com.sheennae.serious.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sheennae.serious.dao.*;
import com.sheennae.serious.model.post.PostModel;
import com.sheennae.serious.model.post.command.PostCommand;
import com.sheennae.serious.model.reaction.Reaction;
import com.sheennae.serious.model.reaction.SubjectPostReactionModel;
import com.sheennae.serious.model.subject.SubjectModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/post")
@Api(value = "PostController", description = "Post Controller")
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

    @ApiOperation(value = "The post is written by user", response = JsonObject.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "BAD_REQUEST : none Essential paramter or doesn't suitable parameter"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 409, message = "DUPLICATED : if user write post more than one once a day"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody JsonObject create(@RequestHeader(value = "userId") String userId,
                                           @RequestBody PostCommand command,
                                           HttpServletResponse response) {

        System.out.println(command.getSubjectId());

        PostModel post = new PostModel();
        SubjectPostReactionModel subjectPostReaction = new SubjectPostReactionModel();

        post.setTitle(command.getTitle());
        post.setContents(command.getContents());

        SubjectModel subjectModel = subjectRepository.findOne(Integer.parseInt(command.getSubjectId()));

        if (subjectModel != null) {
            post.setSubject(subjectModel);
        }

        post.setAuthor(userRepository.findOne(Integer.parseInt(userId)));
        post = postRepository.save(post);

        /*
         * subjectPostReactionModel is also created with post
         */

        subjectPostReaction.setPost(post);
        subjectPostReaction.setReactedTime(LocalDateTime.now());
        subjectPostReaction.setSubject(subjectModel);

        System.out.println("getReaction : " + command.getReaction());

        subjectPostReaction.setSubjectReaction(subjectReactionRepository.findByReaction(command.getReaction()).get());
        subjectPostReactionRepository.save(subjectPostReaction);

        /*
         * insert field for return json
         */

        JsonElement element = new Gson().toJsonTree(post);
        JsonObject jsonObject = element.getAsJsonObject();

        jsonObject.addProperty("subjectReaction", subjectPostReaction.getSubjectReaction().getReaction().toString());
        jsonObject.addProperty("agreeCount", "30");
        jsonObject.addProperty("neutralCount", "30");
        jsonObject.addProperty("disagreeCount", "30");
        jsonObject.addProperty("myReaction", Reaction.AGREE.toString());

        return jsonObject;
    }

    @ApiOperation(value = "React about the post : [AGREE]")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/{postId}/agree", method = RequestMethod.POST)
    public void reactAgree(@PathVariable String postId,
                           @RequestHeader String uuid ,
                           HttpServletResponse response) {

        //todo

        response.setStatus(204);

    }

    @ApiOperation(value = "Cancel the reaction about the post : [AGREE]")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "{postId}/agree", method = RequestMethod.DELETE)
    public void reactAgreeCancel(@PathVariable String postId,
                                 @RequestHeader String uuid ,
                                 HttpServletResponse response) {

        //todo

        response.setStatus(204);

    }

    @ApiOperation(value = "React about the post : [NEUTRAL]")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/{postId}/neutral", method = RequestMethod.POST)
    public void reactNeutral(@PathVariable String postId,
                             @RequestHeader String uuid ,
                             HttpServletResponse response) {

        //todo

        response.setStatus(204);

    }

    @ApiOperation(value = "Cancel the reaction about the post : [NEUTRAL]")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "{postId}/neutral", method = RequestMethod.DELETE)
    public void reactNeutralCancel(@PathVariable String postId,
                                   @RequestHeader String uuid ,
                                   HttpServletResponse response) {

        //todo

        response.setStatus(204);

    }

    @ApiOperation(value = "React about the post : [DISAGREE]")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/{postId}/disagree", method = RequestMethod.POST)
    public void reactDisagree(@PathVariable String postId,
                              @RequestHeader String uuid ,
                              HttpServletResponse response) {

        //todo

        response.setStatus(204);

    }

    @ApiOperation(value = "Cancel the reaction about the post : [DISAGREE]")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "{postId}/disagree", method = RequestMethod.DELETE)
    public void reactDisagreeCancel(@PathVariable String postId,
                                    @RequestHeader String uuid,
                                    HttpServletResponse response) {

        //todo

        response.setStatus(204);

    }

}
