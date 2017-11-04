package com.sheennae.serious.controller;

import com.sheennae.serious.dao.PostRepository;
import com.sheennae.serious.model.BaseListModel;
import com.sheennae.serious.model.post.PostModel;
import com.sheennae.serious.model.post.PostVote;
import com.sheennae.serious.model.post.PostVoteModel;
import com.sheennae.serious.model.subject.SubjectModel;
import com.sheennae.serious.model.user.Gender;
import com.sheennae.serious.model.user.UserColor;
import com.sheennae.serious.model.user.UserColorModel;
import com.sheennae.serious.model.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {


    private final PostRepository postRepository;

    @Autowired
    public FeedController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping("/{subjectId}")
    public @ResponseBody BaseListModel<PostModel> getFeed(@PathVariable String subjectId,
                                                          @RequestParam(name = "cursor", required = false, defaultValue = "0")
                                                                      String cursor) {
        BaseListModel<PostModel> responseBody = new BaseListModel<>();
        List<PostModel> datas = postRepository.findAll();
        responseBody.setCursor(0);
        responseBody.setDatas(datas);
        responseBody.setCount(datas.size());
        return responseBody;
    }


    @RequestMapping("/mine")
    public @ResponseBody BaseListModel<PostModel> getFeedMine(@RequestHeader(name = "userId") String userId,
                                                          @RequestParam(name = "cursor", required = false, defaultValue = "0")
                                                                  String cursor) {

        BaseListModel<PostModel> responseBody = new BaseListModel<>();
        List<PostModel> datas = postRepository.findAll();
        responseBody.setCursor(0);
        responseBody.setDatas(datas);
        responseBody.setCount(datas.size());
        return responseBody;
    }

}
