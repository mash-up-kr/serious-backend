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
    @Column(name = "reaction", nullable = false)
    private Reaction reaction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        PostReaction that = (PostReaction) o;
        return id == that.id &&
                reaction == that.reaction;

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, reaction);

    }

    @Override
    public String toString() {

        return "PostReaction{" +
                "id=" + id +
                ", reaction=" + reaction +
                '}';

    }

}
