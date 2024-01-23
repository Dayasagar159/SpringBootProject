package com.indus.app2.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.indus.app2.model.Question;
import com.indus.app2.response.MessageResponse;
import com.indus.app2.response.QuestionRequestResponse;

public interface QuestionService {

	MessageResponse save(QuestionRequestResponse questionRequestResponse);
	MessageResponse updateQuestion(int id, QuestionRequestResponse questionRequestResponse);
	Question getQuestionById(int id, String tenantName);
	MessageResponse deleteQuestionById(int id);
	List<Question> getAllQuestions();
	List<Question> getListOfQuestionsUsingParameters(Integer pageNumber, Integer pageSize, String sortAs, String sortBy,
			String questionName, String description,String mandatory);
	ResponseEntity<Map<String, Object>> getListOfQuestionsUsingParameters1(int pageNumber, int pageSize, String sortAs,
			String sortBy, String questionName, String description,String mandatory);
	List<Question> getListOfQuesions(Integer pageNumber, Integer pageSize, String sortAs, String sortBy, String questionName,
			String description, String type, Boolean enabled, String details,String mandatory);
	List<Question> getByAttributes(String questionName, String description, String type,
			Boolean enabled, String details,String mandatory);
	List<Question> findByFreeText(Integer pageNumber, Integer pageSize, String sortAs, String sortBy, String questionName,
			String description, String type, Boolean enabled, String details,
			String searchValue,String mandatory);
	Integer findByFreeTextCount(String questionName, String description, String type,
			Boolean enabled, String details, String searchValue,String mandatory);
	MessageResponse deleteQuestions();

}