package com.indus.app2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.indus.app2.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>, JpaSpecificationExecutor<Question> {

	Question findById(int id);
	
	Question findByType(String name);

	@Query(value="SELECT wha FROM Question  wha WHERE ((:questionName) is null or wha.questionName in "
			+ "(:questionName)) and ((:description) is null or wha.description in(:description)) and ((:mandatory) is null or wha.mandatory in(:mandatory)) and ((:type) is "
			+ "null or wha.type in (:type)) and ((:enabled) is null or wha.enabled in (:enabled)) and ((:details) is null or wha.details in (:details))")
	List<Question> findPotentialMatch(String questionName, String description, String type,
			Boolean enabled, String details,String mandatory);
	
	Question findByDescription(String name);
	
	Question findByQuestionName(String name);
}