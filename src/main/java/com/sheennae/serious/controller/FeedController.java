package com.sheennae.serious.controller;

import com.sheennae.serious.dao.PostRepository;
import com.sheennae.serious.model.BaseListModel;
import com.sheennae.serious.model.post.PostModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/feed")
@Api(value = "FeedController", description = "Operations pertaining to feed for post about subject in Serious application")
public class FeedController {


    private final PostRepository postRepository;

    @Autowired
    public FeedController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping(value = "/{subjectId}", method = RequestMethod.GET)
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


    @RequestMapping(value = "/mine", method = RequestMethod.GET)
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
