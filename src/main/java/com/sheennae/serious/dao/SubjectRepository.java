package com.sheennae.serious.dao;

import com.sheennae.serious.model.subject.SubjectModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectModel, Integer> {
}
