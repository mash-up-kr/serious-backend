package com.sheennae.serious.model.reaction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subject_reaction")
public class SubjectReactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "reaction", nullable = false)
    private Reaction reaction;

    public SubjectReactionModel(Reaction reaction) {
        this.reaction = reaction;
    }

    public SubjectReactionModel() { }

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
        SubjectReactionModel that = (SubjectReactionModel) o;
        return id == that.id &&
                reaction == that.reaction;

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, reaction);

    }

    @Override
    public String toString() {

        return "SubjectReactionModel{" +
                "id=" + id +
                ", reaction=" + reaction +
                '}';

    }

}
