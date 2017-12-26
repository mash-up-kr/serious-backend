package com.sheennae.serious.model.reaction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "post_reaction")
public class PostReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "reaction_type", nullable = false)
    private ReactionType reactionType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostReaction that = (PostReaction) o;
        return id == that.id &&
                reactionType == that.reactionType;

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, reactionType);

    }

    @Override
    public String toString() {

        return "PostReaction{" +
                "id=" + id +
                ", reactionType=" + reactionType +
                '}';

    }

}
