package com.sheennae.serious.model.post;

import javax.persistence.*;

import com.sheennae.serious.model.reaction.SubjectPostReactionModel;
import com.sheennae.serious.model.subject.SubjectModel;
import com.sheennae.serious.model.user.UserModel;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Post")
public class PostModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "contents", nullable = false)
	private String contents;

	@ManyToOne
	@JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "FK_Post_Subject"), nullable = false)
	private SubjectModel subject;

	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_Post_User"), nullable = false)
	private UserModel user;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

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

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
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
				Objects.equals(user, postModel.user) &&
				Objects.equals(createdAt, postModel.createdAt);

	}

	@Override
	public int hashCode() {

		return Objects.hash(id, title, contents, subject, user, createdAt);

	}

	@Override
	public String toString() {

		return "PostModel{" +
				"id=" + id +
				", title='" + title + '\'' +
				", contents='" + contents + '\'' +
				", subject=" + subject +
				", user=" + user +
				", createdAt=" + createdAt +
				'}';

	}

}
