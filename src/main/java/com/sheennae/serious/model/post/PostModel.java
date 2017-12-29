package com.sheennae.serious.model.post;

import javax.persistence.*;

import com.sheennae.serious.model.reaction.SubjectPostReactionModel;
import com.sheennae.serious.model.subject.SubjectModel;
import com.sheennae.serious.model.user.UserModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Post")
public class PostModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "The database generated product ID")
	private int id;

	@Column(name = "title", nullable = false)
	@ApiModelProperty(notes = "The post title", required = true)
	private String title;

	@Column(name = "contents", nullable = false)
	@ApiModelProperty(notes = "The post contents", required = true)
	private String contents;

	@ManyToOne
	@JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "FK_Post_Subject"), nullable = false)
	@ApiModelProperty(notes = "The post is corresponded by subject", required = true)
	private SubjectModel subject;

	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_Post_User"), nullable = false)
	@ApiModelProperty(notes = "The post was written by user", required = true)
	private UserModel author;

	@Column(name = "created_at", nullable = false)
	@ApiModelProperty(notes = "The post created time", required = true)
	private LocalDateTime createdAt;

	@PrePersist
	public void persist() {
		this.createdAt = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public SubjectModel getSubject() {
		return subject;
	}

	public void setSubject(SubjectModel subject) {
		this.subject = subject;
	}

	public UserModel getAuthor() {
		return author;
	}

	public void setAuthor(UserModel author) {
		this.author = author;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PostModel postModel = (PostModel) o;
		return id == postModel.id &&
				Objects.equals(title, postModel.title) &&
				Objects.equals(contents, postModel.contents) &&
				Objects.equals(subject, postModel.subject) &&
				Objects.equals(author, postModel.author) &&
				Objects.equals(createdAt, postModel.createdAt);

	}

	@Override
	public int hashCode() {

		return Objects.hash(id, title, contents, subject, author, createdAt);

	}

	@Override
	public String toString() {

		return "PostModel{" +
				"id=" + id +
				", title='" + title + '\'' +
				", contents='" + contents + '\'' +
				", subject=" + subject +
				", author=" + author +
				", createdAt=" + createdAt +
				'}';

	}

}
