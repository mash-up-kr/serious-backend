package com.sheennae.serious.model.subject.command;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class SubjectCommand {

    @SerializedName("title")
    private String title;


    @SerializedName("publishedAt")
    private LocalDateTime publishedAt;

}
