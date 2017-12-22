package com.sheennae.serious.model.subject;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class SubjectModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;


	@Column(name = "title", nullable = false)
	private String title;


	@Column(name = "createAt", insertable=true, updatable=false, nullable=false)
	private LocalDateTime createAt;


	@PrePersist
	public void persist() {
		this.createAt = LocalDateTime.now();
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


	public LocalDateTime getCreateAt() {
		return createAt;
	}


	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createAt == null) ? 0 : createAt.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubjectModel other = (SubjectModel) obj;
		if (createAt == null) {
			if (other.createAt != null)
				return false;
		} else if (!createAt.equals(other.createAt))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "SubjectModel [id=" + id + ", title=" + title + ", createAt=" + createAt + "]";
	}


}
