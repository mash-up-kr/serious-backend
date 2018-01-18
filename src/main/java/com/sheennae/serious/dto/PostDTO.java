package com.sheennae.serious.dto;

import com.google.gson.annotations.SerializedName;
import com.sheennae.serious.model.user.UserModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {

    @SerializedName("id")
    private int id;


    @SerializedName("author")
    private UserDTO author;


    @SerializedName("subject")
    private SubjectDTO subject;


    @SerializedName("title")
    private String title;


    @SerializedName("contents")
    private String contents;


    @SerializedName("subjectReaction")
    private SubjectReaction subjectReaction;


    @SerializedName("agreeCount")
    private int agreeCount;


    @SerializedName("neutralCount")
    private int neutralCount;


    @SerializedName("disagreeCount")
    private int disagreeCount;


    @SerializedName("myReaction")
    private PostReaction myReaction;


    @SerializedName("createdAt")
    private LocalDateTime createdAt;

}
