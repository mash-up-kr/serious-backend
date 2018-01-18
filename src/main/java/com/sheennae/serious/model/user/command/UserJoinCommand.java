package com.sheennae.serious.model.user.command;

import com.sheennae.serious.dto.UserBias;
import com.sheennae.serious.model.user.Gender;

import javax.validation.constraints.NotNull;

public class UserJoinCommand {

    @NotNull
    private String uuid;
    @NotNull
    private String nickname;
    @NotNull
    private UserBias bias;
    private String introduce;
    private int ageRange;
    private Gender gender;

    public String getUuid() {
        return uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public UserBias getBias() {
        return bias;
    }

    public String getIntroduce() {
        return introduce;
    }

    public int getAgeRange() {
        return ageRange;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "UserJoinCommand{" +
                "uuid='" + uuid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", bias=" + bias +
                ", introduce='" + introduce + '\'' +
                ", ageRange=" + ageRange +
                ", gender=" + gender +
                '}';
    }

}
