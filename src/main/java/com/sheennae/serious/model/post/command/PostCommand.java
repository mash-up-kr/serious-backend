package com.sheennae.serious.model.post.command;


import com.sheennae.serious.model.reaction.Reaction;

import java.util.Objects;

public class PostCommand {

    private String title;
    private String contents;
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostCommand that = (PostCommand) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(contents, that.contents) &&
                reaction == that.reaction;

    }

    @Override
    public int hashCode() {

        return Objects.hash(title, contents, reaction);

    }

    @Override
    public String toString() {

        return "PostCommand{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", reaction=" + reaction +
                '}';

    }

}
