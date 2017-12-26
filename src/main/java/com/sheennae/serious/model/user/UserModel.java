package com.sheennae.serious.model.user;



import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class UserModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;


	@Column(name = "nickname", unique = true, nullable = false)
	private String nickname;


	@Column(name = "create_at", insertable=true, updatable=false, nullable=false)
	private LocalDateTime createAt;

	@ManyToOne
	@JoinColumn(name = "bias_id", foreignKey = @ForeignKey(name = "FK_User_Bias"), nullable = false)
	private BiasModel bias;


	@Column(name = "introduce")
	private String introduce;


	@Column(name = "age_range")
	private int ageRange;


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

	public BiasModel getBias() {
		return bias;
	}

	public void setBias(BiasModel bias) {
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
				Objects.equals(createAt, userModel.createAt) &&
				Objects.equals(bias, userModel.bias) &&
				Objects.equals(introduce, userModel.introduce) &&
				gender == userModel.gender;
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, uuid, nickname, createAt, bias, introduce, ageRange, gender);
	}

	@Override
	public String toString() {
		return "UserModel{" +
				"id=" + id +
				", uuid='" + uuid + '\'' +
				", nickname='" + nickname + '\'' +
				", createAt=" + createAt +
				", bias=" + bias +
				", introduce='" + introduce + '\'' +
				", ageRange=" + ageRange +
				", gender=" + gender +
				'}';
	}
}
