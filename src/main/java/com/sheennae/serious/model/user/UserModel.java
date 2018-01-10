package com.sheennae.serious.model.user;



import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@ApiModelProperty(notes = "The database generated product ID")
	private int id;

	@Column(name = "uuid", unique = true, nullable = false)
	@ApiModelProperty(notes = "The device unique value", required = true)
	private String uuid;


	@Column(name = "nickname", unique = true, nullable = false)
	@ApiModelProperty(notes = "The user nickname for showing other users", required = true)
	private String nickname;


	@Column(name = "created_at", insertable = true, updatable = false, nullable = false)
	@ApiModelProperty(notes = "The time when user register on Serious application")
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "user_bias_id", foreignKey = @ForeignKey(name = "FK_User_UserBias"), nullable = false)
	@ApiModelProperty(notes = "The user's opinion about politics = ['EXTREME_LEFT', 'LEFT', 'MID', 'RIGHT', 'EXTREME_RIGHT']", required = true)
	private UserBiasModel bias;


	@Column(name = "introduce")
	@ApiModelProperty(notes = "The user's introduction about themselves")
	private String introduce;


	@Column(name = "age_range", nullable = false)
	@ApiModelProperty(notes = "The user's age range 10 to 90", required = true)
	private int ageRange;


	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(notes = "The user's sex", required = true)
	private Gender gender;


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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public UserBiasModel getBias() {
		return bias;
	}

	public void setBias(UserBiasModel bias) {
		this.bias = bias;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(int age) {
		this.ageRange = ageRange;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserModel userModel = (UserModel) o;
		return id == userModel.id &&
				ageRange == userModel.ageRange &&
				Objects.equals(uuid, userModel.uuid) &&
				Objects.equals(nickname, userModel.nickname) &&
				Objects.equals(createdAt, userModel.createdAt) &&
				Objects.equals(bias, userModel.bias) &&
				Objects.equals(introduce, userModel.introduce) &&
				gender == userModel.gender;
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, uuid, nickname, createdAt, bias, introduce, ageRange, gender);
	}

	@Override
	public String toString() {
		return "UserModel{" +
				"id=" + id +
				", uuid='" + uuid + '\'' +
				", nickname='" + nickname + '\'' +
				", createdAt=" + createdAt +
				", userBias=" + bias +
				", introduce='" + introduce + '\'' +
				", ageRange=" + ageRange +
				", gender=" + gender +
				'}';
	}
}
