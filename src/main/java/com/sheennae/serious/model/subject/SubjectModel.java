package com.sheennae.serious.model.subject;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class SubjectModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;


	@Column(name = "title", nullable = false)
	private String title;


	@Column(name = "created_at", insertable = true, updatable = false, nullable = false)
	private LocalDateTime createdAt;


	@Column(name="published_at", insertable = true, updatable = false, nullable = true)
	private LocalDateTime publishedAt;




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


	public LocalDateTime getcreatedAt() {
		return createdAt;
	}


	public void setcreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@PrePersist
	public void persist() {
		this.createdAt = LocalDateTime.now();
	}

	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(LocalDateTime publishedAt) {
		this.publishedAt = publishedAt;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SubjectModel that = (SubjectModel) o;
		return id == that.id &&
				Objects.equals(title, that.title) &&
				Objects.equals(createdAt, that.createdAt) &&
				Objects.equals(publishedAt, that.publishedAt);
	}


	@Override
	public int hashCode() {

		return Objects.hash(id, title, createdAt, publishedAt);
	}


	@Override
	public String toString() {
		return "SubjectModel{" +
				"id=" + id +
				", title='" + title + '\'' +
				", createdAt=" + createdAt +
				", publishedAt=" + publishedAt +
				'}';
	}


}
