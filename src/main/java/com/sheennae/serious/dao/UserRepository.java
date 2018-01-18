package com.sheennae.serious.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheennae.serious.model.user.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{


	@Query(value = "select * from user where uuid = :uuid", nativeQuery = true)
	Optional<UserModel> findByUuid(@Param("uuid") String uuid);

}
