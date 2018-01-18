package com.sheennae.serious.controller;

import com.sheennae.serious.dao.PostRepository;
import com.sheennae.serious.dao.SubjectRepository;
import com.sheennae.serious.dao.UserRepository;
import com.sheennae.serious.dto.*;
import com.sheennae.serious.exception.BadRequestException;
import com.sheennae.serious.exception.UnauthorizedException;
import com.sheennae.serious.model.BaseListModel;
import com.sheennae.serious.model.post.PostModel;
import com.sheennae.serious.model.subject.SubjectModel;
import com.sheennae.serious.model.user.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/feed")
@Api(value = "FeedController", description = "Feed Controller")
public class FeedController {

    private final ModelMapper modelMapper = new ModelMapper();

    private final PostRepository postRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;


    @Autowired
    public FeedController(PostRepository postRepository, SubjectRepository subjectRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/{subjectId}", method = RequestMethod.GET)
    public @ResponseBody BaseListModel<PostDTO> getFeed(
            @RequestHeader String uuid,
            @PathVariable int subjectId,
            @RequestParam(required = false, defaultValue = "15") int count,
            @RequestParam(required = false) Optional<Integer> cursor) {

        if (uuid == null || uuid.trim().length() == 0) {
            throw new UnauthorizedException();
        }

        SubjectModel subject = subjectRepository.findOne(subjectId);
        if (subject == null) {
            throw new BadRequestException("Please check the subjectId");
        }

        System.out.println("uuid : " + uuid);

        UserModel userModel = userRepository
                .findByUuid(uuid)
                .orElseThrow(() -> new UnauthorizedException());

        UserDTO user = modelMapper.map(userModel, UserDTO.class);

        count = count <= 0 ? 15 : count;
        List<PostDTO> feed = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            PostDTO post = new PostDTO();
            post.setId(1);
            post.setAuthor(user);
            post.setSubject(modelMapper.map(subject, SubjectDTO.class));
            post.setTitle("나는 반대한다아!!!!");
            post.setContents("리플 떡상 가즈아!!!!!!!!!!!!!");
            post.setSubjectReaction(SubjectReaction.AGREE);
            post.setMyReaction(PostReaction.DISAGREE);
            post.setAgreeCount(10);
            post.setNeutralCount(11);
            post.setDisagreeCount(12);
            post.setCreatedAt(LocalDateTime.now());

            feed.add(post);
        }

        BaseListModel<PostDTO> feedModel = new BaseListModel<>();
        feedModel.setDatas(feed);
        feedModel.setCount(400);
        feedModel.setCursor(feed.get(feed.size() - 1).getId());
        return feedModel;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/mine", method = RequestMethod.GET)
    public @ResponseBody BaseListModel<PostModel> getFeedMine(@RequestHeader(name = "userId") String userId,
                                                              @RequestParam(name = "count", required = false, defaultValue = "0") String count,
                                                              @RequestParam(name = "cursor", required = false, defaultValue = "0") String cursor) {

        BaseListModel<PostModel> responseBody = new BaseListModel<>();

        List<PostModel> datas = postRepository.findPostByAuthor(Integer.parseInt(userId));
        responseBody.setCursor(0);
        responseBody.setDatas(datas);
        responseBody.setCount(datas.size());

        return responseBody;
    }

}
