package com.indus.app2.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indus.app2.exception.ValidationException;
import com.indus.app2.model.Question;
import com.indus.app2.model.QuestionsGroup;
import com.indus.app2.repository.QuestionRepository;
import com.indus.app2.repository.QuestionsGroupPagingRepository;
import com.indus.app2.repository.QuestionsGroupRepository;
import com.indus.app2.repository.QuestionsGroupSequenceRepository;
import com.indus.app2.response.MessageResponse;
import com.indus.app2.response.QestionswithGroupRequest;
import com.indus.app2.response.QestionswithGroupRequestResponse;
import com.indus.app2.response.QuestionsGroupRequestResponse;
import com.indus.app2.response.QuestionsGroupWithQuestionsRequestResponse;
import com.indus.app2.service.QuestionsGroupService;
import com.indus.app2.util.Constants;

@Service
public class QuestionsGroupServiceImpl implements QuestionsGroupService
{
	
	@Autowired
	private QuestionsGroupRepository questionsGroupRepository;
	
	@Autowired
	private QuestionRepository QuestionRepository;
	
	@Autowired
	private QuestionsGroupSequenceRepository questionsGroupSequenceRepository;
	
	@Autowired
	private QuestionsGroupPagingRepository questionsGroupPagingRepository;
	
	
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer";
	private static final String USER = "User";

	
	String announcementNotAvailable = "Announcement is not available with the given id ";
	String announcementNotAvailableByEIN = "Announcement is not available with the given EIN ";
	String CREATEDBY="createdBy";
	String TENANTNAME="tenantName";
	String TYPE="type";
	String CREATEDAT="createdAt";
	String ISACTIVE="isActive";
	String questionNotAvailable = "Question is not available with the given id ";
	String questionGroupNotAvailable = "Question Group is not available with the given id ";
	String questionGroupNameNotAvailable = "Question Group Name is not available with the given group name ";


	@Override
	public MessageResponse save(QuestionsGroupRequestResponse questionsGroupRequestResponse) {
		
		List<QuestionsGroup> questionGroupDetails;
		QuestionsGroup questionGroup = new QuestionsGroup();
		MessageResponse messageResponse = new MessageResponse();
		BeanUtils.copyProperties(questionsGroupRequestResponse, questionGroup);
		
		try {
		 String groupName = questionsGroupRequestResponse.getGroupName();
		 String questionName=questionsGroupRequestResponse.getQuestionName();
		 String queueName = questionsGroupRequestResponse.getQueueName();
		 if (groupName != null && questionName != null && queueName != null) {
	            questionGroupDetails = questionsGroupRepository.findByGroupNameAndQuestionNameAndQueueName(groupName,questionName,queueName);

	            if (questionGroupDetails != null && !questionGroupDetails.isEmpty()) {
	                // If the description is already present, throw an exception
	                throw new ValidationException("Question Name  and Group Name is already present  for this queueName  with this  following Id: " + questionGroupDetails.get(0).getId());
	            }
		 }
	        // Check if the group already exists in the database
		 List<QuestionsGroup> existingGroup = questionsGroupRepository.findByGroupName(groupName);
	        
	        int groupId;
	        if (!existingGroup.isEmpty()) {
	        	 // Group already exists, use the groupId from the first matching record
	            groupId = existingGroup.get(0).getGroupId();
	        } else {
	            // Group doesn't exist, generate a new groupId
	           groupId=questionsGroupSequenceRepository.getSeqeuenceNumber();
	        }
	        questionGroup.setGroupId(groupId);
		questionsGroupRepository.save(questionGroup);
		messageResponse.setConflictStatus("200");
		if(!existingGroup.isEmpty())
		{
		messageResponse.setStatusMessage(Constants.UPDATE_QUESTION_GROUP_MESSAGE);
		}
		else
		{
			messageResponse.setStatusMessage(Constants.CREATE_QUESTION_MESSAGE);
		}
		messageResponse.setId(questionGroup.getId());
		
	 }catch (ValidationException ve) 
		{
		        // Re-throw the ValidationException to propagate the error
		        throw ve;
		 }
		catch (Exception e) {
         // Handle exceptions (e.g., DataIntegrityViolationException)
         messageResponse.setConflictStatus("500");
         messageResponse.setStatusMessage("Error saving QuestionsGroup");
     }
		
		return messageResponse;
	}


	@Override
	public QuestionsGroup getQuestionById(int id, String tenantName) {
		// TODO Auto-generated method stub
		return questionsGroupRepository.findById(id);
	}

	@Override
	public MessageResponse updateQuestionsGroupById(int id,
			QuestionsGroupRequestResponse questionsGroupRequestResponse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageResponse deleteQuestionGroupById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionsGroup> getAllQuestionsGroup() {
		// TODO Auto-generated method stub
		return questionsGroupRepository.findAll();
	}

	@Override
	public List<QuestionsGroup> getListOfQuestionsGroupUsingParameters(Integer pageNumber, Integer pageSize,
			String sortAs, String sortBy, String groupName, String queueName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionsGroup> getByAttributes(String groupName, String questionName, String queueName,
			String tenantName, Boolean enabled, Integer groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionsGroup> findByFreeText(Integer pageNumber, Integer pageSize, String sortAs, String sortBy,
			String groupName, String questionName, String queueName, String tenantName, Boolean enabled,
			Integer groupId, String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findByFreeTextCount(String groupName, String questionName, String queueName, String tenantName,
			Boolean enabled, Integer groupId, String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getByQuestionsUsingQUestionGroupAttributes(String groupName, String questionName,
			String queueName, String tenantName, Boolean enabled, Integer groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionsGroupWithQuestionsRequestResponse> getAllQuestionsGroupAlongWithQuestionsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionsGroupWithQuestionsRequestResponse> getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueName(
			String queueName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionsGroupWithQuestionsRequestResponse> getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueName(
			String queueName, Boolean enabled) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageResponse saveQuestions(QestionswithGroupRequest questionswithGroupRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageResponse saveMultipleQuestions(QestionswithGroupRequestResponse questionswithGroupRequest) {


	    List<MessageResponse> responses = new ArrayList<>();

	    try {
	        String groupName = questionswithGroupRequest.getGroupName();
	        String queueName = questionswithGroupRequest.getQueueName();
	        List<String> questionNames = questionswithGroupRequest.getQuestionNames();

	        if (groupName != null && questionNames != null && !questionNames.isEmpty()) {
	            for (String questionName : questionNames) {
	                QuestionsGroup questionGroup = new QuestionsGroup();
	                BeanUtils.copyProperties(questionswithGroupRequest, questionGroup);

	                // Check if the group and question combination already exists
	                List<QuestionsGroup> questionGroupDetails = questionsGroupRepository
	                        .findByGroupNameAndQuestionNameAndQueueName(groupName, questionName,queueName);

	                if (questionGroupDetails == null || questionGroupDetails.isEmpty()) {
	                    // The combination doesn't exist, proceed with saving
	                    List<QuestionsGroup> existingGroup = questionsGroupRepository.findByGroupName(groupName);

	                    int groupId;
	                    if (!existingGroup.isEmpty()) {
	                        groupId = existingGroup.get(0).getGroupId();
	                    } else {
	                        groupId = questionsGroupSequenceRepository.getSeqeuenceNumber();
	                    }

	                    questionGroup.setGroupId(groupId);
	                    questionGroup.setQuestionName(questionName); // Corrected setter method
	                    questionsGroupRepository.save(questionGroup);

	                    MessageResponse messageResponse = new MessageResponse();
	                    messageResponse.setConflictStatus("200");
	                    messageResponse.setStatusMessage(existingGroup.isEmpty() ?
	                            Constants.CREATE_QUESTION_MESSAGE : Constants.UPDATE_QUESTION_GROUP_MESSAGE);
	                    messageResponse.setId(questionGroup.getId());

	                    responses.add(messageResponse);
	                }
	                // If the combination already exists, skip without throwing an exception
	            }
	        }
	    } catch (Exception e) {
	        MessageResponse messageResponse = new MessageResponse();
	        messageResponse.setConflictStatus("500");
	        messageResponse.setStatusMessage("Error saving QuestionsGroup");
	        responses.add(messageResponse);
	    }

	    return responses.isEmpty() ? getDefaultResponse() : responses.get(0);
	}


	private MessageResponse getDefaultResponse() {
	    MessageResponse defaultResponse = new MessageResponse();
	    defaultResponse.setConflictStatus("200"); // or "404" or any appropriate status code
	    defaultResponse.setStatusMessage("Default Response - No questions saved");
	    return defaultResponse;
	}


	@Override
	public MessageResponse deleteQuestionGroupByGroupName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageResponse enableQuestionGroupByGroupName(String groupName, boolean enable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueNameAndEIN(String queueName, Boolean enabled,
			String ein) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueNameAndEIN1(String queueName, Boolean enabled,
			String ein) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionsGroupWithQuestionsRequestResponse> getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueNameAndEINDetails(
			String queueName, Boolean enabled, String ein) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionsGroupWithQuestionsRequestResponse> getAllQuestionsGroupAlongWithQuestionsListBasedOnEIN(
			String ein, String tenantName) {
		// TODO Auto-generated method stub
		return null;
	}

}
