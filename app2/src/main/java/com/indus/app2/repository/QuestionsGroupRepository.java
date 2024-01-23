package com.indus.app2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.indus.app2.model.QuestionsGroup;

public interface QuestionsGroupRepository extends JpaRepository<QuestionsGroup, Integer>, JpaSpecificationExecutor<QuestionsGroup> {

	QuestionsGroup findById(int id);

	List<QuestionsGroup> findByGroupName(String groupName);

	@Query(value="SELECT wha FROM QuestionsGroup  wha WHERE ((:groupName) is null or wha.groupName in "
			+ "(:groupName)) and ((:questionName) is null or wha.questionName in(:questionName)) and ((:queueName) is "
			+ "null or wha.queueName in (:queueName)) and (:tenantName is null or wha.tenantName = :tenantName) and ((:enabled) is null or wha.enabled in (:enabled))and ((:groupId) is null or wha.groupId in (:groupId))")
	List<QuestionsGroup> findPotentialMatch(String groupName, String questionName, String queueName,
			String tenantName, Boolean enabled,Integer groupId);

	List<QuestionsGroup> findByGroupNameAndQuestionName(String groupName, String questionName);
	
	List<QuestionsGroup> findByQueueName(String queueName);
	
	List<QuestionsGroup> findByQueueNameAndEnabled(String queueName,Boolean enabled);
	
	List<QuestionsGroup> findByEnabled(Boolean enabled);

	List<QuestionsGroup> findByGroupNameAndQuestionNameAndQueueName(String groupName, String questionName,
			String queueName);
	
	
	
}