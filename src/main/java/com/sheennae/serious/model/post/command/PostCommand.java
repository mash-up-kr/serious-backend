package com.sheennae.serious.model.post.command;


import com.sheennae.serious.model.reaction.Reaction;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class PostCommand {

    @NotNull
    private String title;
    @NotNull
    private String contents;
    @NotNull
    private String subjectId;
    @NotNull
    private Reaction reaction;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostCommand that = (PostCommand) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(contents, that.contents) &&
                Objects.equals(subjectId, that.subjectId) &&
                reaction == that.reaction;

    }

    @Override
    public int hashCode() {

        return Objects.hash(title, contents, subjectId, reaction);

    }

    @Override
    public String toString() {

        return "PostCommand{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", reaction=" + reaction +
                '}';

    }
}
