package com.sheennae.serious.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sheennae.serious.controller.user.NicknameManager;
import com.sheennae.serious.exception.BadRequestException;
import com.sheennae.serious.model.user.UserBiasModel;
import com.sheennae.serious.model.user.UserModel;
import com.sheennae.serious.model.user.UserNicknameModel;
import com.sheennae.serious.model.user.command.UserJoinCommand;
import com.sheennae.serious.dao.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
@Api(value = "UserController", description = "User Controller")
public class UserController {

    private final UserRepository userRepository;
    private final BiasRepository biasRepository;
    private ObjectMapper mapper;

    private NicknameManager nicknameManager;

    @Autowired
    public UserController(UserRepository userRepository, BiasRepository biasRepository, ObjectMapper mapper) {
        this.userRepository = userRepository;
        this.biasRepository = biasRepository;
        this.mapper = mapper;

        try {
            this.nicknameManager = new NicknameManager(userRepository.findAll());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @ApiOperation(value = "Register a user for using serious application", response = UserModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
            JsonObject register(
            @RequestBody @Valid UserJoinCommand command,
            BindingResult bindingResult,
            HttpServletResponse response) throws JsonProcessingException {

        if (bindingResult.hasErrors()) {
            response.setStatus(400);
            throw new BadRequestException("invalid parameter form");
        }

        Optional<UserModel> user = userRepository.findByUuid(command.getUuid());
        Optional<UserBiasModel> bias = biasRepository.findByBias(command.getBias());
        String responseJson;

        if (user.isPresent()) {
            responseJson = mapper.writeValueAsString(user.get());
            return (JsonObject) new JsonParser().parse(responseJson);
        }
        else {
            UserModel userModel = new UserModel();
            userModel.setUuid(command.getUuid());
            userModel.setNickname(command.getNickname());
            userModel.setCreatedAt(LocalDateTime.now());
            userModel.setGender(command.getGender());
            userModel.setAgeRange(command.getAgeRange());
            userModel.setBias(bias.get());
            userModel.setIntroduce(command.getIntroduce());


            userRepository.save(userModel);
            nicknameManager.put(command.getNickname());

            // TODO put nickname to nickname manager

            responseJson = mapper.writeValueAsString(userModel);
            return (JsonObject) new JsonParser().parse(responseJson);
        }

    }

    @ApiOperation(value = "Watch the other user infomation", response = UserModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
            JsonObject getUserInfo(@PathVariable String userId) throws JsonProcessingException {

        UserModel user = userRepository.findOne(Integer.parseInt(userId));
        String responseJson = mapper.writeValueAsString(user);

        return (JsonObject) new JsonParser().parse(responseJson);

    }


    @RequestMapping(value = "/generate-nickname", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Generate nickname")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = UserNicknameModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR"),
    })
    public UserNicknameModel generateNickname() {
        return new UserNicknameModel(nicknameManager.generate());
    }

}
