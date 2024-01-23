package com.indus.app2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.indus.app2.model.QuestionsGroup;

public interface QuestionsGroupSequenceRepository extends JpaRepository<QuestionsGroup, Long>, JpaSpecificationExecutor<QuestionsGroup> {
	
	@Query(value = " SELECT nextval('configuration.questions_Group_id_seq') ",nativeQuery = true)
    int getSeqeuenceNumber();

	
	
	
}
