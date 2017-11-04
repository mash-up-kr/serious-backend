package com.sheennae.serious.model.user.command;

import com.sheennae.serious.model.user.Gender;
import com.sheennae.serious.model.user.UserColor;

public class UserJoinCommand {

    private String uuid;
    private String nickname;
    private UserColor color;
    private String introduce;
    private int age;
    private Gender gender;


    public String getUuid() {
        return uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public UserColor getColor() {
        return color;
    }

    public String getIntroduce() {
        return introduce;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "UserJoinCommand{" +
                "uuid='" + uuid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", color=" + color +
                ", introduce='" + introduce + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
