package com.sheennae.serious.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheennae.serious.model.user.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{

	 Optional<UserModel> findByUuid(String uuid);

}
