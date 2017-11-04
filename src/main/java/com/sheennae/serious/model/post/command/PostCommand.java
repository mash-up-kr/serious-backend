package com.sheennae.serious.model.post.command;

import com.sheennae.serious.model.post.PostVote;

public class PostCommand {

    private String title;
    private String contents;
    private PostVote vote;
    private boolean enableChat;

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public PostVote getVote() {
        return vote;
    }

    public boolean isEnableChat() {
        return enableChat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostCommand that = (PostCommand) o;

        if (enableChat != that.enableChat) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (contents != null ? !contents.equals(that.contents) : that.contents != null) return false;
        return vote == that.vote;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (contents != null ? contents.hashCode() : 0);
        result = 31 * result + (vote != null ? vote.hashCode() : 0);
        result = 31 * result + (enableChat ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostCommand{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", vote=" + vote +
                ", enableChat=" + enableChat +
                '}';
    }
}
