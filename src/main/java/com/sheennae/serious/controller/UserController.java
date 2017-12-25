package com.sheennae.serious.controller;


import com.sheennae.serious.model.user.BiasModel;
import com.sheennae.serious.model.user.UserModel;
import com.sheennae.serious.model.user.command.UserJoinCommand;
import com.sheennae.serious.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserRepository userRepository;
    private final BiasRepository biasRepository;

    @Autowired
    public UserController(UserRepository userRepository, BiasRepository biasRepository) {
        this.userRepository = userRepository;
        this.biasRepository = biasRepository;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody UserModel join(@RequestBody UserJoinCommand command, HttpServletResponse response) {
        // TODO 1. command validate
        // TODO 2.     invalid data -> error
        // TODO 3.     valid data -> select -> insert
        // TODO                                update
        // TODO 4. response

        System.out.println(command);
        Optional<UserModel> user = userRepository.findByUuid(command.getUuid());
        Optional<BiasModel> bias = biasRepository.findByType(command.getBiasType());
        if (user.isPresent()) {
            return user.get();
        }
        UserModel userModel = new UserModel();
        userModel.setUuid(command.getUuid());
        userModel.setNickname(command.getNickname());
        userModel.setCreateAt(LocalDateTime.now());
        userModel.setGender(command.getGender());
        userModel.setAgeRange(command.getAgeRange());
        System.out.println(command.getBiasType());
        userModel.setBias(bias.get());
        userModel.setIntroduce(command.getIntroduce());

        return userRepository.save(userModel);
    }


    @PostMapping("/register/device/ios")
    public void registerDevice() {

    }

}
