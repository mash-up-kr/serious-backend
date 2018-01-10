package com.sheennae.serious.model.user.command;

import com.google.gson.annotations.SerializedName;

public class UserPushTokenCommand {

    @SerializedName("pushToken")
    private String pushToken;


    public String getPushToken() {
        return pushToken;
    }
}
