package com.sheennae.serious.controller;


import com.sheennae.serious.model.user.UserColorModel;
import com.sheennae.serious.model.user.UserModel;
import com.sheennae.serious.model.user.command.UserJoinCommand;
import com.sheennae.serious.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

	private final UserRepository userRepository;
	private final UserColorRepository userColorRepository;
	@Autowired
    public UserController(UserRepository userRepository, UserColorRepository userColorRepository) {
		this.userRepository = userRepository;
		this.userColorRepository = userColorRepository;
	}


	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public @ResponseBody UserModel join(@RequestBody UserJoinCommand command, HttpServletResponse response) {
        // TODO 1. command validate
        // TODO 2.     invalid data -> error
        // TODO 3.     valid data -> select -> insert
        // TODO                                update
        // TODO 4. response
    	
        System.out.println(command);
        Optional<UserModel> user = userRepository.findByUuid(command.getUuid());
        Optional<UserColorModel> color = userColorRepository.findByType(command.getColor());
        if (user.isPresent()) {
        	return user.get();
        }
        UserModel userModel = new UserModel();
        userModel.setUuid(command.getUuid());
        userModel.setNickname(command.getNickname());
        userModel.setCreateAt(LocalDateTime.now());
        userModel.setGender(command.getGender());
        userModel.setAge(command.getAge());
        
        userModel.setColor(color.get());
        userModel.setIntroduce(command.getIntroduce());
        
        return userRepository.save(userModel);
    }	

    //test
    @PostMapping("/user/register/device/ios")
    public void registerDevice() {

    }

}
