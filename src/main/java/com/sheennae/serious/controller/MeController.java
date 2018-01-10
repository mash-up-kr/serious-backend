package com.sheennae.serious.controller;

import com.google.gson.*;
import com.sheennae.serious.dao.UserRepository;
import com.sheennae.serious.exception.NotFoundException;
import com.sheennae.serious.exception.UnauthorizedException;
import com.sheennae.serious.model.ErrorModel;
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
@Api(value = "MeController", description = "Me Controller")
public class MeController {

    private final UserRepository userRepository;

    @Autowired
    MeController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get my information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = UserModel.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    public @ResponseBody UserModel getMeInfo(@RequestHeader(defaultValue = "") String uuid) {
        if (uuid == null || uuid.trim().length() == 0) {
            throw new UnauthorizedException();
        }

        Optional<UserModel> optionalMe = userRepository.findByUuid(uuid);
        UserModel me = optionalMe.orElseThrow(() -> new NotFoundException("Doesn't find user by uuid. Please register first."));

        return me;

    }

    @ApiOperation(value = "Edit my information", response = JsonObject.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody UserModel editMeInfo(@RequestHeader String uuid) {

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
