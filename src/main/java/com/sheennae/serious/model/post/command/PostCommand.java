package com.sheennae.serious.model.post.command;


import com.sheennae.serious.model.reaction.ReactionType;

import java.util.Objects;

public class PostCommand {

    private String title;
    private String contents;
    private ReactionType type;

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

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostCommand that = (PostCommand) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(contents, that.contents) &&
                type == that.type;

    }

    @Override
    public int hashCode() {

        return Objects.hash(title, contents, type);

    }

    @Override
    public String toString() {

        return "PostCommand{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", type=" + type +
                '}';

    }

}
