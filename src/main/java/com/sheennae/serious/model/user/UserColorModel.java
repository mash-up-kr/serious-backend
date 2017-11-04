package com.sheennae.serious.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "UserColor")
@Table(name="user_color")
public class UserColorModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private UserColor type;
	

	@Column(name = "code")
	private String code;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public UserColor getType() {
		return type;
	}


	public void setType(UserColor type) {
		this.type = type;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		UserColorModel other = (UserColorModel) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		if (type != other.type)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "UserColorModel [id=" + id + ", type=" + type + ", code=" + code + "]";
	}
	
	
}
