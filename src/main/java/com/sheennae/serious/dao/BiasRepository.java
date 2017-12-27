package com.sheennae.serious.dao;

import com.sheennae.serious.model.user.UserBiasModel;
import com.sheennae.serious.model.user.UserBias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BiasRepository extends JpaRepository<UserBiasModel, Integer> {

    Optional<UserBiasModel> findByBias(UserBias bias);

}
