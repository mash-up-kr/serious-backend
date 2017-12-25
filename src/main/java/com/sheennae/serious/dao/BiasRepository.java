package com.sheennae.serious.dao;

import com.sheennae.serious.model.user.BiasModel;
import com.sheennae.serious.model.user.BiasType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface BiasRepository extends JpaRepository<BiasModel, Integer> {

    Optional<BiasModel> findByType(BiasType type);

}
