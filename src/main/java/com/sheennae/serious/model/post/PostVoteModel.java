package com.sheennae.serious.model.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "PostVote")
public class PostVoteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private PostVote type;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public PostVote getType() {
		return type;
	}


	public void setType(PostVote type) {
		this.type = type;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		PostVoteModel other = (PostVoteModel) obj;
		if (id != other.id)
			return false;
		if (type != other.type)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "PostVoteModel [id=" + id + ", type=" + type + "]";
	}

	
}
