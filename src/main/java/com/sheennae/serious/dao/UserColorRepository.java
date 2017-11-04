package com.sheennae.serious.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheennae.serious.model.user.UserColor;
import com.sheennae.serious.model.user.UserColorModel;

public interface UserColorRepository extends JpaRepository<UserColorModel, Integer> {
	Optional<UserColorModel> findByType(UserColor type);
}
