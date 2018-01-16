package com.sheennae.serious.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class SubjectDTO {

    @SerializedName("id")
    private int id;


    @SerializedName("title")
    private String title;


    @SerializedName("createdAt")
    private LocalDateTime createdAt;


    @SerializedName("publishedAt")
    private LocalDateTime publishedAt;

}
