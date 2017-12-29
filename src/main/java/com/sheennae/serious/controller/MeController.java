package com.sheennae.serious.controller;

import com.google.gson.*;
import com.sheennae.serious.dao.UserRepository;
import com.sheennae.serious.model.user.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping(value = "/me")
@Api(value = "MeController", description = "Operations pertaining to me(themselves by user) in Serious application")
public class MeController {

    private final UserRepository userRepository;

    @Autowired
    MeController(UserRepository userRepository) {

        this.userRepository = userRepository;

    }

    @ApiOperation(value = "Get my information", response = JsonObject.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody JsonObject getMeInfo(@RequestHeader String uuid, HttpServletResponse response) {

        //todo
        //check posting boolean
        //check uuid

        Optional<UserModel> me = userRepository.findByUuid(uuid);
        JsonElement element = new Gson().toJsonTree(me.get());

        JsonObject jsonObject = element.getAsJsonObject();

        jsonObject.remove("createdAt");
        jsonObject.addProperty("postToday", "false");

        return jsonObject;

    }

    @ApiOperation(value = "Edit my information", response = JsonObject.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody UserModel editMeInfo(@RequestHeader String uuid, HttpServletResponse response) {

        //todo

        return null;

    }

    @ApiOperation(value = "Register push token")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "SUCCESS : empty body"),
            @ApiResponse(code = 400, message = "BAD_REQUEST : doesn't compatible parameter"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/device", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody JsonObject registerPushToken(@RequestHeader String uuid, @RequestBody String pushToken) {

        //todo

        JsonObject jsonObject = new JsonObject();

        return jsonObject;
    }

}
