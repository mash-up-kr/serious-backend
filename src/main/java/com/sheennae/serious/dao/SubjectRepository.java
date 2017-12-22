package com.sheennae.serious.dao;

import com.sheennae.serious.model.subject.SubjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectModel, Integer> {
}
