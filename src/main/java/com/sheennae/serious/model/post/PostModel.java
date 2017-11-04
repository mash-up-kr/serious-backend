package com.sheennae.serious.model.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sheennae.serious.model.subject.SubjectModel;
import com.sheennae.serious.model.user.UserModel;

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
	@JoinColumn(name = "subjectId", foreignKey = @ForeignKey(name = "FK_Post_Subject"))
	private SubjectModel subjectId;
	
	
	@ManyToOne
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_Post_User"))
	private UserModel userId;
	
	
	@ManyToOne
	@JoinColumn(name = "voteId", foreignKey = @ForeignKey(name = "FK_Post_PostVote"))
	private PostVoteModel voteId;
	
	
	@Column(name = "enableChat")
	private boolean enableChat;


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


	public SubjectModel getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(SubjectModel subjectId) {
		this.subjectId = subjectId;
	}


	public UserModel getUserId() {
		return userId;
	}


	public void setUserId(UserModel userId) {
		this.userId = userId;
	}


	public PostVoteModel getVoteId() {
		return voteId;
	}


	public void setVoteId(PostVoteModel voteId) {
		this.voteId = voteId;
	}


	public boolean isEnableChat() {
		return enableChat;
	}


	public void setEnableChat(boolean enableChat) {
		this.enableChat = enableChat;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + (enableChat ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((voteId == null) ? 0 : voteId.hashCode());
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
		PostModel other = (PostModel) obj;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (enableChat != other.enableChat)
			return false;
		if (id != other.id)
			return false;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (voteId == null) {
			if (other.voteId != null)
				return false;
		} else if (!voteId.equals(other.voteId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "PostModel [id=" + id + ", title=" + title + ", contents=" + contents + ", subjectId=" + subjectId
				+ ", userId=" + userId + ", voteId=" + voteId + ", enableChat=" + enableChat + "]";
	}
	
}
