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
	private SubjectModel subject;
	
	
	@ManyToOne
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_Post_User"))
	private UserModel user;
	
	
	@ManyToOne
	@JoinColumn(name = "voteId", foreignKey = @ForeignKey(name = "FK_Post_PostVote"))
	private PostVoteModel vote;
	
	
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

	public PostVoteModel getVote() {
		return vote;
	}

	public void setVote(PostVoteModel vote) {
		this.vote = vote;
	}

	public boolean isEnableChat() {
		return enableChat;
	}

	public void setEnableChat(boolean enableChat) {
		this.enableChat = enableChat;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PostModel postModel = (PostModel) o;

		if (id != postModel.id) return false;
		if (enableChat != postModel.enableChat) return false;
		if (title != null ? !title.equals(postModel.title) : postModel.title != null) return false;
		if (contents != null ? !contents.equals(postModel.contents) : postModel.contents != null) return false;
		if (subject != null ? !subject.equals(postModel.subject) : postModel.subject != null) return false;
		if (user != null ? !user.equals(postModel.user) : postModel.user != null) return false;
		return vote != null ? vote.equals(postModel.vote) : postModel.vote == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (contents != null ? contents.hashCode() : 0);
		result = 31 * result + (subject != null ? subject.hashCode() : 0);
		result = 31 * result + (user != null ? user.hashCode() : 0);
		result = 31 * result + (vote != null ? vote.hashCode() : 0);
		result = 31 * result + (enableChat ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return "PostModel{" +
				"id=" + id +
				", title='" + title + '\'' +
				", contents='" + contents + '\'' +
				", subject=" + subject +
				", user=" + user +
				", vote=" + vote +
				", enableChat=" + enableChat +
				'}';
	}
}
