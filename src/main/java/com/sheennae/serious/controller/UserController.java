package com.sheennae.serious.controller;


import com.sheennae.serious.model.user.UserBiasModel;
import com.sheennae.serious.model.user.UserModel;
import com.sheennae.serious.model.user.command.UserJoinCommand;
import com.sheennae.serious.dao.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/user")
@Api(value = "UserController", description = "User Controller")
public class UserController {

    private final UserRepository userRepository;
    private final BiasRepository biasRepository;

    @Autowired
    public UserController(UserRepository userRepository, BiasRepository biasRepository) {
        this.userRepository = userRepository;
        this.biasRepository = biasRepository;
    }

    @ApiOperation(value = "Register a user for using serious application", response = UserModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody UserModel register(@RequestBody UserJoinCommand command, HttpServletResponse response) {
        // TODO 1. command validate
        // TODO 2.     invalid data -> error
        // TODO 3.     valid data -> select -> insert
        // TODO                                update
        // TODO 4. response

        System.out.println(command);
        Optional<UserModel> user = userRepository.findByUuid(command.getUuid());
        Optional<UserBiasModel> bias = biasRepository.findByBias(command.getBias());

        if (user.isPresent()) {
            return user.get();
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

            return userRepository.save(userModel);
        }

    }

    @ApiOperation(value = "Watch the other user infomation", response = UserModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody UserModel getUserInfo(@PathVariable String userId, HttpServletResponse response) {

        UserModel user = userRepository.findOne(Integer.parseInt(userId));
        user.setCreatedAt(null);
        user.setUuid(null);

        return user;

    }

    @PostMapping("/register/device/ios")
    public void registerDevice() {

    }

}
