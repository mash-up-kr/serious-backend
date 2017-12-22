package com.sheennae.serious.model.user.command;

import com.sheennae.serious.model.user.BiasType;
import com.sheennae.serious.model.user.Gender;

public class UserJoinCommand {

    private String uuid;
    private String nickname;
    private BiasType biasType;
    private String introduce;
    private int ageRange;
    private Gender gender;


    public String getUuid() {
        return uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public BiasType getBiasType() {
        return biasType;
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
                ", biasType=" + biasType +
                ", introduce='" + introduce + '\'' +
                ", ageRange=" + ageRange +
                ", gender=" + gender +
                '}';
    }

}
