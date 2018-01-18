package com.sheennae.serious.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    @SerializedName("id")
    private int id;


    @SerializedName("nickname")
    private String nickname;


    @SerializedName("createdAt")
    private LocalDateTime createdAt;


    @SerializedName("bias")
    private UserBias biasBias;


    @SerializedName("introduce")
    private String introduce;
}
