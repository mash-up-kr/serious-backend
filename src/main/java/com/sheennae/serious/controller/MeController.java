package com.sheennae.serious.controller;

import com.google.gson.*;
import com.sheennae.serious.dao.UserRepository;
import com.sheennae.serious.exception.BadRequestException;
import com.sheennae.serious.exception.NotFoundException;
import com.sheennae.serious.exception.UnauthorizedException;
import com.sheennae.serious.model.ErrorModel;
import com.sheennae.serious.model.user.UserModel;
import com.sheennae.serious.model.user.command.UserEditCommand;
import com.sheennae.serious.model.user.command.UserPushTokenCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        return userRepository
                .findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Doesn't find user by uuid. Please register first."));

    }

    @ApiOperation(value = "Edit my information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody UserModel editMeInfo(@RequestHeader(defaultValue = "") String uuid, @RequestBody UserEditCommand command) {
        UserModel me = getMeInfo(uuid);
        if (command == null) {
            throw new BadRequestException("Body should not be null for editing my information.");
        }

        if (command.getAgeRange() != null) {
            me.setAgeRange(command.getAgeRange());
        }

        if (command.getGender() != null) {
            me.setGender(command.getGender());
        }

        if (command.getBias() != null) {
//            me.setBias(command.getBias());
        }

        if (command.getIntroduce() != null) {
            me.setIntroduce(command.getIntroduce());
        }

        userRepository.save(me);
        return me;

    }

    @ApiOperation(value = "Register push token")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorModel.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/device", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody void registerPushToken(@RequestHeader String uuid, @RequestBody UserPushTokenCommand command, HttpServletResponse response) {
        if (command == null || command.getPushToken() == null || command.getPushToken().trim().length() == 0) {
            throw new BadRequestException("Body should not be null for save my push token.");
        }
        UserModel me = getMeInfo(uuid);
        String pushToken = command.getPushToken();
        // TODO save push token
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }


}
