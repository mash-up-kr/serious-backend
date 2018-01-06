package com.sheennae.serious.model.subject.command;

import com.google.gson.annotations.SerializedName;

public class SubjectCommand {

    @SerializedName("title")
    private String title;

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "SubjectCommand{" +
                "title='" + title + '\'' +
                '}';
    }
}
