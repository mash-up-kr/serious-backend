package com.sheennae.serious.model.reaction;

import com.sheennae.serious.model.post.PostModel;
import com.sheennae.serious.model.user.UserModel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_post_reaction")
public class UserPostReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_UserPostReaction_User"))
    private UserModel userId;

    @ManyToOne
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "FK_UserPostReaction_Post"))
    private PostModel postId;

    @Column(name = "reacted_time")
    private LocalDateTime reactedTime;

    @ManyToOne
    @JoinColumn(name = "post_reaction_id", foreignKey = @ForeignKey(name = "FK_UserPostReaction_PostReaction"))
    private PostReaction postReaction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUserId() {
        return userId;
    }

    public void setUserId(UserModel userId) {
        this.userId = userId;
    }

    public PostModel getPostId() {
        return postId;
    }

    public void setPostId(PostModel postId) {
        this.postId = postId;
    }

    public LocalDateTime getReactedTime() {
        return reactedTime;
    }

    public void setReactedTime(LocalDateTime reactedTime) {
        this.reactedTime = reactedTime;
    }

    public PostReaction getPostReaction() {
        return postReaction;
    }

    public void setPostReaction(PostReaction postReaction) {
        this.postReaction = postReaction;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPostReaction that = (UserPostReaction) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(postId, that.postId) &&
                Objects.equals(reactedTime, that.reactedTime) &&
                Objects.equals(postReaction, that.postReaction);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, postId, reactedTime, postReaction);

    }

    @Override
    public String toString() {

        return "UserPostReaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", postId=" + postId +
                ", reactedTime=" + reactedTime +
                ", postReaction=" + postReaction +
                '}';

    }
}
