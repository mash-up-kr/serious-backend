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

        List<PostDTO> feed = new ArrayList<>();

        PostDTO post = new PostDTO();
        post.setId(1);
        post.setAuthor(user);
        post.setSubject(modelMapper.map(subject, SubjectDTO.class));
        post.setTitle("나는 포괄임금제를 찬성한다.");
        post.setContents("포괄임금제는 정말 좋은 제도이다.\n왜냐하면 그 포괄임금제 때문에 나의 퇴직금을 올려주기 때문이다.");
        post.setSubjectReaction(SubjectReaction.AGREE);
        post.setMyReaction(PostReaction.DISAGREE);
        post.setAgreeCount(10);
        post.setNeutralCount(11);
        post.setDisagreeCount(12);
        post.setCreatedAt(LocalDateTime.now());
        feed.add(post);

        PostDTO post2 = new PostDTO();
        post2.setId(2);
        post2.setAuthor(user);
        post2.setSubject(modelMapper.map(subject, SubjectDTO.class));
        post2.setTitle("포괄임금제는 없어져야 할 제도이다.");
        post2.setContents("포괄임금제는 그냥 야근수당을 제대로 주지 않으려는 기업의 속임수일 뿐이다. 노동자의 노동을 업신 여기는 아주 나쁜 제도 중 하나라고 생각한다.");
        post2.setSubjectReaction(SubjectReaction.DISAGREE);
        post2.setMyReaction(PostReaction.AGREE);
        post2.setAgreeCount(12);
        post2.setNeutralCount(11);
        post2.setDisagreeCount(10);
        post2.setCreatedAt(LocalDateTime.now());
        feed.add(post2);

        PostDTO post3 = new PostDTO();
        post3.setId(3);
        post3.setAuthor(user);
        post3.setSubject(modelMapper.map(subject, SubjectDTO.class));
        post3.setTitle("포괄임금제가 중요한 것이 아니다.");
        post3.setContents("수당은 지금 현제 중요한 사안이 아니다. 제대로된 근무 환경을 조성하는 것이 중요하다. 그것을 조성하지 않고 수당을 얘기하는 것은 부적절한 집행인 것 같다.");
        post3.setSubjectReaction(SubjectReaction.NEUTRAL);
        post3.setMyReaction(PostReaction.AGREE);
        post3.setAgreeCount(12);
        post3.setNeutralCount(11);
        post3.setDisagreeCount(10);
        post3.setCreatedAt(LocalDateTime.now());
        feed.add(post3);

        PostDTO post4 = new PostDTO();
        post4.setId(4);
        post4.setAuthor(user);
        post4.setSubject(modelMapper.map(subject, SubjectDTO.class));
        post4.setTitle("포괄임금제가 뭐죠?");
        post4.setContents("아몰랑 걍 짜증남");
        post4.setSubjectReaction(SubjectReaction.NEUTRAL);
        post4.setMyReaction(PostReaction.NEUTRAL);
        post4.setAgreeCount(12);
        post4.setNeutralCount(11);
        post4.setDisagreeCount(10);
        post4.setCreatedAt(LocalDateTime.now());
        feed.add(post4);


        PostDTO post5 = new PostDTO();
        post5.setId(5);
        post5.setAuthor(user);
        post5.setSubject(modelMapper.map(subject, SubjectDTO.class));
        post5.setTitle("포괄임금제 그래요 괜찮다고 칩시다.");
        post5.setContents("포괄임금제 괜찮습니다 그래도 최소한 6000천만은 줍시다 ㅇㅋ?");
        post5.setSubjectReaction(SubjectReaction.AGREE);
        post5.setMyReaction(PostReaction.AGREE);
        post5.setAgreeCount(12);
        post5.setNeutralCount(11);
        post5.setDisagreeCount(10);
        post5.setCreatedAt(LocalDateTime.now());
        feed.add(post5);


        BaseListModel<PostDTO> feedModel = new BaseListModel<>();
        feedModel.setDatas(feed);
        feedModel.setCount(5);
        feedModel.setCursor(feed.size() < 15 ? 0 : feed.get(feed.size() - 1).getId());
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
