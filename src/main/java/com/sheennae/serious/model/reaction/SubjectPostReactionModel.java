package com.sheennae.serious.model.reaction;

import com.sheennae.serious.model.post.PostModel;
import com.sheennae.serious.model.subject.SubjectModel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "subject_post_reaction")
public class SubjectPostReactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @ManyToOne
    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "FK_SubjectPostReaction_Subect"), nullable = false)
    private SubjectModel subject;


    @ManyToOne
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "FK_SubjectPostReaction_Post"), nullable = false)
    private PostModel post;

    @ManyToOne
    @JoinColumn(name = "subject_reaction_id", foreignKey = @ForeignKey(name = "FK_SubjectPostReaction_SubjectReaction"), nullable = false)
    private SubjectReactionModel subjectReaction;


    @Column(name = "reacted_time", nullable = false)
    private LocalDateTime reactedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
        this.subject = subject;
    }

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

    public SubjectReactionModel getSubjectReaction() {
        return subjectReaction;
    }

    public void setSubjectReaction(SubjectReactionModel subjectReaction) {
        this.subjectReaction = subjectReaction;
    }

    public LocalDateTime getReactedTime() {
        return reactedTime;
    }

    public void setReactedTime(LocalDateTime reactedTime) {
        this.reactedTime = reactedTime;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectPostReactionModel that = (SubjectPostReactionModel) o;
        return id == that.id &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(post, that.post) &&
                Objects.equals(subjectReaction, that.subjectReaction) &&
                Objects.equals(reactedTime, that.reactedTime);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, subject, post, subjectReaction, reactedTime);

    }

    @Override
    public String toString() {

        return "SubjectPostReaction{" +
                "id=" + id +
                ", subject=" + subject +
                ", post=" + post +
                ", subjectReaction=" + subjectReaction +
                ", reactedTime=" + reactedTime +
                '}';

    }

}
