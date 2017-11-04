package com.sheennae.serious.model.user;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

@Entity(name = "User")
public class UserModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	
	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;
	
	
	@Column(name = "nickname", unique = true, nullable = false)
	private String nickname;
	
	
	@Column(name = "createAt", insertable=true, updatable=false, nullable=false)
	private LocalDateTime createAt;
	
	@ManyToOne
	@JoinColumn(name = "colorId", foreignKey = @ForeignKey(name = "FK_User_UserColor"))
	private UserColorModel color;
	
	
	@Column(name = "introduce")
	private String introduce;
	
	
	@Column(name = "age")
	private int age;
	
	
	@Column(name = "gender")
	private Gender gender;

	
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


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public LocalDateTime getCreateAt() {
		return createAt;
	}


	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}


	public UserColorModel getColor() {
		return color;
	}


	public void setColor(UserColorModel color) {
		this.color = color;
	}


	public String getIntroduce() {
		return introduce;
	}


	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((createAt == null) ? 0 : createAt.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + ((introduce == null) ? 0 : introduce.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		UserModel other = (UserModel) obj;
		if (age != other.age)
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (createAt == null) {
			if (other.createAt != null)
				return false;
		} else if (!createAt.equals(other.createAt))
			return false;
		if (gender != other.gender)
			return false;
		if (id != other.id)
			return false;
		if (introduce == null) {
			if (other.introduce != null)
				return false;
		} else if (!introduce.equals(other.introduce))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "UserModel [id=" + id + ", uuid=" + uuid + ", nickname=" + nickname + ", createAt=" + createAt
				+ ", color=" + color + ", introduce=" + introduce + ", age=" + age + ", gender=" + gender + "]";
	}
	
	
	
}
