package com.sheennae.serious.controller;

import com.sheennae.serious.dao.PostRepository;
import com.sheennae.serious.model.BaseListModel;
import com.sheennae.serious.model.post.PostModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/{subjectId}", method = RequestMethod.GET)
    public @ResponseBody BaseListModel<PostModel> getFeed(@PathVariable String subjectId,
                                                          @RequestParam(name = "count", required = false, defaultValue = "0") String count,
                                                          @RequestParam(name = "cursor", required = false, defaultValue = "0") String cursor) {
        BaseListModel<PostModel> responseBody = new BaseListModel<>();

        List<PostModel> datas = postRepository.findPostBySubject(Integer.parseInt(subjectId));
        responseBody.setCursor(Integer.parseInt(cursor));
        responseBody.setDatas(datas);
        responseBody.setCount(datas.size());

        return responseBody;
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
