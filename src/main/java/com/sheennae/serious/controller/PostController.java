package com.sheennae.serious.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sheennae.serious.dao.*;
import com.sheennae.serious.dto.PostDTO;
import com.sheennae.serious.dto.SubjectDTO;
import com.sheennae.serious.exception.BadRequestException;
import com.sheennae.serious.exception.DuplicatedException;
import com.sheennae.serious.exception.NotFoundException;
import com.sheennae.serious.exception.UnauthorizedException;
import com.sheennae.serious.model.post.PostModel;
import com.sheennae.serious.model.post.command.PostCommand;
import com.sheennae.serious.model.reaction.PostReaction;
import com.sheennae.serious.model.reaction.Reaction;
import com.sheennae.serious.model.reaction.SubjectPostReactionModel;
import com.sheennae.serious.model.reaction.UserPostReaction;
import com.sheennae.serious.model.subject.SubjectModel;
import com.sheennae.serious.model.user.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/post")
@Api(value = "PostController", description = "Post Controller")
public class PostController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final SubjectRepository subjectRepository;
    private final SubjectPostReactionRepository subjectPostReactionRepository;
    private final SubjectReactionRepository subjectReactionRepository;
    private final UserPostReactionRepository userPostReactionRepository;
    private final PostReactionRespository postReactionRespository;
    private final PostReactionProcedure postReactionProcedure;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public PostController(UserRepository userRepository,
                          PostRepository postRepository,
                          SubjectRepository subjectRepository,
                          SubjectPostReactionRepository subjectPostReactionRepository,
                          SubjectReactionRepository subjectReactionRepository,
                          UserPostReactionRepository userPostReactionRepository,
                          PostReactionRespository postReactionRespository,
                          PostReactionProcedure postReactionProcedure) {

        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.subjectRepository = subjectRepository;
        this.subjectPostReactionRepository = subjectPostReactionRepository;
        this.subjectReactionRepository = subjectReactionRepository;
        this.userPostReactionRepository = userPostReactionRepository;
        this.postReactionRespository = postReactionRespository;
        this.postReactionProcedure = postReactionProcedure;

    }

    @ApiOperation(value = "The post is written by user", response = PostDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "BAD_REQUEST : none Essential paramter or doesn't suitable parameter"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 409, message = "DUPLICATED : if user write post more than one once a day"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody PostDTO create(@RequestHeader(value = "uuid") String uuid,
                                        @RequestBody @Valid PostCommand command,
                                        BindingResult bindingResult) {

        Optional<UserModel> user = userRepository.findByUuid(uuid);

        //check valid uuid
        if (!user.isPresent()) {
            throw new UnauthorizedException();
        }

        //check posting opportunity once a day
        if (postRepository.findPostByAuthorAndSubject(user.get().getId(), Integer.parseInt(command.getSubjectId())).isPresent()) {
            throw new DuplicatedException("user write post more than one once a day");
        }

        //check validate post command
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("none Essential paramter or doesn't suitable parameter");
        }

        PostDTO postDTO = new PostDTO();

        PostModel postModel = new PostModel();
        SubjectModel subjectModel;
        SubjectPostReactionModel subjectPostReaction = new SubjectPostReactionModel();

        postModel.setTitle(command.getTitle());
        postModel.setContents(command.getContents());

        postDTO.setTitle(command.getTitle());
        postDTO.setContents(command.getContents());

        SubjectDTO subjectDTO = subjectRepository
                .findById(Integer.parseInt(command.getSubjectId()))
                .map(item -> modelMapper.map(item, SubjectDTO.class))
                .orElseThrow(() -> new NotFoundException(String.format("There's no subject correspond to subject ID : %s", command.getSubjectId())));

        subjectModel = subjectRepository.findOne(Integer.parseInt(command.getSubjectId()));

        postModel.setSubject(subjectModel);
        postDTO.setSubject(subjectDTO);

        postModel.setAuthor(user.get());
        postModel = postRepository.save(postModel);

        /*
         * subjectPostReactionModel is also created with post
         */

        subjectPostReaction.setPost(postModel);
        subjectPostReaction.setReactedTime(LocalDateTime.now());
        subjectPostReaction.setSubject(subjectModel);

        System.out.println("getReaction : " + command.getReaction());

        subjectPostReaction.setSubjectReaction(subjectReactionRepository.findByReaction(command.getReaction()).get());
        subjectPostReactionRepository.save(subjectPostReaction);

        postDTO.setAgreeCount(0);
        postDTO.setDisagreeCount(0);
        postDTO.setNeutralCount(0);
        postDTO.setMyReaction(null);

        return postDTO;
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

        // check valid uuid
        Optional<UserModel> user = userRepository.findByUuid(uuid);
        if (!user.isPresent()) {
            throw new UnauthorizedException();
        }

        postReactionProcedure.reactPost(
                Date.valueOf(LocalDate.now()),
                Integer.parseInt(postId),
                postReactionRespository.findByReaction(Reaction.AGREE.toString()).get().getId(),
                user.get().getId());

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

        // check valid uuid
        Optional<UserModel> user = userRepository.findByUuid(uuid);
        if (!user.isPresent()) {
            throw new UnauthorizedException();
        }
        System.out.println("postId : " + Integer.parseInt(postId) + ", userId : " + user.get().getId());

        userPostReactionRepository.deleteAgreeByPostIdAndUserId(Integer.parseInt(postId), user.get().getId());

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

        // check valid uuid
        Optional<UserModel> user = userRepository.findByUuid(uuid);
        if (!user.isPresent()) {
            throw new UnauthorizedException();
        }
        postReactionProcedure.reactPost(
                Date.valueOf(LocalDate.now()),
                Integer.parseInt(postId),
                postReactionRespository.findByReaction(Reaction.NEUTRAL.toString()).get().getId(),
                user.get().getId());

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
                                   @RequestHeader String uuid,
                                   HttpServletResponse response) {

        // check valid uuid
        Optional<UserModel> user = userRepository.findByUuid(uuid);
        if (!user.isPresent()) {
            throw new UnauthorizedException();
        }

        userPostReactionRepository.deleteNeutralByPostIdAndUserId(Integer.parseInt(postId), user.get().getId());

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
        // check valid uuid
        Optional<UserModel> user = userRepository.findByUuid(uuid);
        if (!user.isPresent()) {
            throw new UnauthorizedException();
        }
        postReactionProcedure.reactPost(
                Date.valueOf(LocalDate.now()),
                Integer.parseInt(postId),
                postReactionRespository.findByReaction(Reaction.DISAGREE.toString()).get().getId(),
                user.get().getId());

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

        // check valid uuid
        Optional<UserModel> user = userRepository.findByUuid(uuid);
        if (!user.isPresent()) {
            throw new UnauthorizedException();
        }

        userPostReactionRepository.deleteDisagreeByPostIdAndUserId(Integer.parseInt(postId), user.get().getId());

        response.setStatus(204);

    }

}
