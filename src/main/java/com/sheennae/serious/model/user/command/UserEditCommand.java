package com.sheennae.serious.model.user.command;

import com.google.gson.annotations.SerializedName;
import com.sheennae.serious.model.user.Gender;
import com.sheennae.serious.model.user.UserBias;

public class UserEditCommand {

    @SerializedName("bias")
    private UserBias bias;

    @SerializedName("introduce")
    private String introduce;

    @SerializedName("ageRange")
    private Integer ageRange;

    @SerializedName("gender")
    private Gender gender;

    public UserBias getBias() {
        return bias;
    }

    public String getIntroduce() {
        return introduce;
    }

    public Integer getAgeRange() {
        return ageRange;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "UserEditCommand{" +
                "bias=" + bias +
                ", introduce='" + introduce + '\'' +
                ", ageRange=" + ageRange +
                ", gender=" + gender +
                '}';
    }
}
