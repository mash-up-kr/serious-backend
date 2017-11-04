package com.sheennae.serious.controller;


import com.sheennae.serious.model.user.Gender;
import com.sheennae.serious.model.user.UserColor;
import com.sheennae.serious.model.user.UserColorModel;
import com.sheennae.serious.model.user.UserModel;
import com.sheennae.serious.model.user.command.UserJoinCommand;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class UserController {


    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public @ResponseBody UserModel join(@RequestBody UserJoinCommand command) {
        // TODO 1. command validate
        // TODO 2.     invalid data -> error
        // TODO 3.     valid data -> select -> insert
        // TODO                                update
        // TODO 4. response

        System.out.println(command);


        UserModel user = new UserModel();
        user.setId(30);
        user.setUuid("asdfasdf-asdfasdfasdf-asdfasdfasdf-sdfsdf");
        user.setNickname("sdfsdf");
        user.setCreateAt(LocalDateTime.now());
        user.setGender(Gender.MALE);
        user.setAge(30);

        UserColorModel color = new UserColorModel();
        color.setId(1);
        color.setCode("#EFEFEF");
        color.setType(UserColor.EXTREME_RIGHT);
        user.setColor(color);
        user.setIntroduce("sdfsdfsdfsd");
        return user;
    }


    @PostMapping("/user/register/device/ios")
    public void registerDevice() {

    }

}
