package com.sheennae.serious.model.user;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class UserNicknameModel {

    @SerializedName("nickname")
    private String nickname;

    public UserNicknameModel(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNicknameModel that = (UserNicknameModel) o;
        return Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }

    @Override
    public String toString() {
        return "UserNicknameModel{" +
                "nickname='" + nickname + '\'' +
                '}';
    }
}
