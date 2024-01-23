package com.indus.app2.service;

import java.util.List;

import com.indus.app2.model.Question;
import com.indus.app2.model.QuestionsGroup;
import com.indus.app2.response.MessageResponse;
import com.indus.app2.response.QestionswithGroupRequest;
import com.indus.app2.response.QestionswithGroupRequestResponse;
import com.indus.app2.response.QuestionsGroupRequestResponse;
import com.indus.app2.response.QuestionsGroupWithQuestionsRequestResponse;

public interface QuestionsGroupService {

	MessageResponse save(QuestionsGroupRequestResponse questionsGroupRequestResponse);

	QuestionsGroup getQuestionById(int id, String tenantName);

	MessageResponse updateQuestionsGroupById(int id, QuestionsGroupRequestResponse questionsGroupRequestResponse);

	MessageResponse deleteQuestionGroupById(int id);

	List<QuestionsGroup> getAllQuestionsGroup();

	List<QuestionsGroup> getListOfQuestionsGroupUsingParameters(Integer pageNumber, Integer pageSize, String sortAs,
			String sortBy, String groupName, String queueName);

	List<QuestionsGroup> getByAttributes(String groupName, String questionName, String queueName,
			String tenantName, Boolean enabled,Integer groupId);

	List<QuestionsGroup> findByFreeText(Integer pageNumber, Integer pageSize, String sortAs, String sortBy, String groupName,
			String questionName, String queueName, String tenantName, Boolean enabled, Integer groupId,
			String searchValue);

	Integer findByFreeTextCount(String groupName, String questionName, String queueName, String tenantName,
			Boolean enabled, Integer groupId, String searchValue);

	List<Question> getByQuestionsUsingQUestionGroupAttributes(String groupName, String questionName, String queueName,
			String tenantName, Boolean enabled, Integer groupId);

	List<QuestionsGroupWithQuestionsRequestResponse> getAllQuestionsGroupAlongWithQuestionsList();

	List<QuestionsGroupWithQuestionsRequestResponse> getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueName(
			String queueName);

	List<QuestionsGroupWithQuestionsRequestResponse> getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueName(
			String queueName, Boolean enabled);

	MessageResponse saveQuestions(QestionswithGroupRequest questionswithGroupRequest);

	MessageResponse saveMultipleQuestions(QestionswithGroupRequestResponse questionswithGroupRequest);

	MessageResponse deleteQuestionGroupByGroupName(String name);

	MessageResponse enableQuestionGroupByGroupName(String groupName, boolean enable);
	
	Object getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueNameAndEIN(String queueName, Boolean enabled,
			String ein);

	Object getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueNameAndEIN1(String queueName,
			Boolean enabled, String ein);

	List<QuestionsGroupWithQuestionsRequestResponse> getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueNameAndEINDetails(
			String queueName, Boolean enabled, String ein);

	List<QuestionsGroupWithQuestionsRequestResponse> getAllQuestionsGroupAlongWithQuestionsListBasedOnEIN(String ein,String tenantName);
}
