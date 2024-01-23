package com.indus.app2.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indus.app2.model.Question;
import com.indus.app2.model.QuestionsGroup;
import com.indus.app2.response.MessageResponse;
import com.indus.app2.response.QestionswithGroupRequestResponse;
import com.indus.app2.response.QuestionsGroupRequestResponse;
import com.indus.app2.response.QuestionsGroupWithQuestionsRequestResponse;
import com.indus.app2.service.QuestionsGroupService;

import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Question Group controller")
@SecurityRequirement(name = "security_auth")
public class QuestionsGroupController 
{
	
	/*
	 * @Autowired private LogUtils logstashUtils;
	 */
	@Autowired
	private QuestionsGroupService questionsGroupService;

	
	private String xServiceName = "X-serviceId";
	private String xTotalCount = "xTotalCount";

	@PostMapping("/v1.0/QuestionGroup")
	@Operation(summary = "Create a Question Group")
	@Timed(value = "service.configuration")
	public ResponseEntity<MessageResponse> createQuestionGroup(@RequestBody QuestionsGroupRequestResponse questionsGroupRequestResponse,
			HttpServletRequest request) {
		//logstashUtils.addToLogContext(xServiceName, UUID.randomUUID().toString());
		//logstashUtils.addToLogContext("X-serviceMethodName", "createQuestionGroup");
		MessageResponse saveRecord = questionsGroupService.save(questionsGroupRequestResponse);
		if (!saveRecord.getConflictStatus().equals("200")) {
			return new ResponseEntity<>(saveRecord, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(saveRecord, HttpStatus.CREATED);
	}
	
	
	
	/*
	 * @PostMapping("/v1.0/QuestionGroups1")
	 * 
	 * @Operation(summary = "Create a Question Group with multiple questions")
	 * 
	 * @Timed(value = "service.configuration") public
	 * ResponseEntity<MessageResponse> createQuestionGroups1(@RequestBody
	 * QestionswithGroupRequest questionswithGroupRequest, HttpServletRequest
	 * request) { logstashUtils.addToLogContext(xServiceName,
	 * UUID.randomUUID().toString());
	 * logstashUtils.addToLogContext("X-serviceMethodName", "createQuestionGroup");
	 * MessageResponse saveRecord =
	 * questionsGroupService.saveQuestions(questionswithGroupRequest); if
	 * (!saveRecord.getConflictStatus().equals("200")) { return new
	 * ResponseEntity<>(saveRecord, HttpStatus.BAD_REQUEST); } return new
	 * ResponseEntity<>(saveRecord, HttpStatus.CREATED); }
	 */
	
	
	@PostMapping("/v1.0/QuestionGroups")
	@Operation(summary = "Create a Question Group with multiple questions")
	@Timed(value = "service.configuration")
	public ResponseEntity<MessageResponse> createQuestionGroups(@RequestBody QestionswithGroupRequestResponse questionswithGroupRequest,
			HttpServletRequest request) {
		//logstashUtils.addToLogContext(xServiceName, UUID.randomUUID().toString());
		//logstashUtils.addToLogContext("X-serviceMethodName", "createQuestionGroup");
		MessageResponse saveRecord = questionsGroupService.saveMultipleQuestions(questionswithGroupRequest);
		if (!saveRecord.getConflictStatus().equals("200")) {
			return new ResponseEntity<>(saveRecord, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(saveRecord, HttpStatus.CREATED);
	}
	
	
	
	
	@GetMapping("/v1.0/QuestionGroup/{id}")
	@Operation(summary = "Get Questions By  ID")
	@Timed(value = "service.configuration") 
	public ResponseEntity<QuestionsGroup> getQuestionsById(@PathVariable int id, HttpServletRequest request,
			@RequestParam(value = "tenantName", required = false,defaultValue ="Autofix") String tenantName) {
		//logstashUtils.addToLogContext(xServiceName, Integer.toString(id));
		//logstashUtils.addToLogContext("X-serviceMethodName", "getQuestionsById");
		QuestionsGroup questionGroup = questionsGroupService.getQuestionById(id,tenantName);
		return new ResponseEntity<>(questionGroup, HttpStatus.OK);
	}
	
	@PatchMapping("/v1.0/QuestionGroup/{id}")
	@Operation(summary = "update an QuestionGroup")
	@Timed(value = "service.configuration")
	public ResponseEntity<MessageResponse> updateQuestionsGroupById(@PathVariable int id,@RequestBody QuestionsGroupRequestResponse questionsGroupRequestResponse,
			HttpServletRequest request) {
		//logstashUtils.addToLogContext(xServiceName, UUID.randomUUID().toString());
		//logstashUtils.addToLogContext("X-serviceMethodName", "updateQuestionsGroupById");
		MessageResponse messageResponse = questionsGroupService.updateQuestionsGroupById(id,questionsGroupRequestResponse);
		return new ResponseEntity<>(messageResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/v1.0/QuestionGroup/{id}")
	@Operation(summary = "Delete QuestionGroup By  Id")
	@Timed(value = "service.configuration") 
	public ResponseEntity<MessageResponse> deleteQuestionGroupById(@PathVariable int id, HttpServletRequest request) {
		//logstashUtils.addToLogContext(xServiceName, Integer.toString(id));
		//logstashUtils.addToLogContext("X-serviceMethodName", "deleteQuestionGroupById");
		MessageResponse deleteQuestion  = questionsGroupService.deleteQuestionGroupById(id);
		return new ResponseEntity<>(deleteQuestion, HttpStatus.OK);
	}
	
	@DeleteMapping("/v1.0/QuestionGroup/groupName")
	@Operation(summary = "Delete QuestionGroup By  groupName")
	@Timed(value = "service.configuration") 
	public ResponseEntity<MessageResponse> deleteQuestionGroupByGroupName( @RequestParam(value = "groupName", required = true) String groupName, HttpServletRequest request) {
		//logstashUtils.addToLogContext(xServiceName, groupName);
		//logstashUtils.addToLogContext("X-serviceMethodName", "deleteQuestionGroupByGroupName");
		MessageResponse deleteQuestion  = questionsGroupService.deleteQuestionGroupByGroupName(groupName);
		return new ResponseEntity<>(deleteQuestion, HttpStatus.OK);
	}
	
	
	@GetMapping("/v1.0/QuestionGroup")
	@Operation(summary = "Get All QuestionsGroup")
	@Timed(value = "service.configuration") 
	public ResponseEntity<List<QuestionsGroup>> getAllQuestionsGroup(HttpServletRequest request) {
		//logstashUtils.addToLogContext(xServiceName, UUID.randomUUID().toString());
		//logstashUtils.addToLogContext("X-serviceMethodName", "getAllQuestions");
		List<QuestionsGroup> questions= questionsGroupService.getAllQuestionsGroup();
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}
	
	
	@GetMapping("/v1.0/QuestionGroup/Search")
	@Operation(summary = "Get By Questions using Search")
	@Timed(value = "service.configuration")
	public ResponseEntity<List<QuestionsGroup>> getListOfQuestionsGroupUsingParameters(@RequestParam(value = "pageNumber", required = true, defaultValue = "1") int pageNumber,
	 @RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize,
	 @RequestParam(value = "sortAs", required = false,defaultValue ="DESC") String sortAs,
	 @RequestParam(value = "sortBy", required = false, defaultValue ="id") String sortBy,
	 @RequestParam(value = "groupName", required = false) String groupName,
	 @RequestParam(value = "queueName", required = false) String queueName) throws IllegalAccessException, InvocationTargetException{
    	//logstashUtils.addToLogContext("X-serviceMethodName", "getListOfQuestionsGroupUsingParameters");
    	List<QuestionsGroup> getResponse = questionsGroupService.getListOfQuestionsGroupUsingParameters(pageNumber, pageSize, sortAs, sortBy, groupName,queueName);
		return new ResponseEntity<>(getResponse, HttpStatus.OK);
	}
	
	
	 @GetMapping("/v1.0/QuestionGroup/searchByAttributes")
		@Operation(summary = "Get  Questions using Attributes")
		@Timed(value = "service.configuration")
		public ResponseEntity<List<QuestionsGroup>> getQuesionsByUsingAttributes(
				 @RequestParam(value = "groupName", required = false) String groupName,
				 @RequestParam(value = "questionName", required = false) String questionName,
				 @RequestParam(value = "queueName", required = false) String queueName,
				 @RequestParam(value = "tenantName", required = false) String tenantName,
				 @RequestParam(value = "enabled", required = false) Boolean enabled,
				 @RequestParam(value = "groupId", required = false) Integer groupId
				 ) throws IllegalAccessException, InvocationTargetException{
	    	//logstashUtils.addToLogContext("X-serviceMethodName", "getQuesionsByUsingAttributes");
	    	List<QuestionsGroup> createResponse = questionsGroupService.getByAttributes(groupName, questionName,queueName,tenantName,enabled,groupId);
			return new ResponseEntity<>(createResponse, HttpStatus.OK);
		}
	
	
	 @RequestMapping(value = "/v1.0/QuestionGroup/searchByAttributes/count", method = RequestMethod.HEAD)
		@Operation(summary = "Count By QuestionGroup using Attributes")
		@Timed(value = "service.configuration")
		public ResponseEntity<List<QuestionsGroup>> getByAttributesCount(
				 @RequestParam(value = "groupName", required = false) String groupName,
				 @RequestParam(value = "questionName", required = false) String questionName,
				 @RequestParam(value = "queueName", required = false) String queueName,
				 @RequestParam(value = "tenantName", required = false) String tenantName,
				 @RequestParam(value = "enabled", required = false) Boolean enabled,
				 @RequestParam(value = "groupId", required = false) Integer groupId) throws IllegalAccessException, InvocationTargetException{
		//logstashUtils.addToLogContext("X-serviceMethodName", "getByAttributesCount");
		List<QuestionsGroup> createResponse = questionsGroupService.getByAttributes(groupName, questionName,queueName,tenantName,enabled,groupId);
		HttpHeaders responseHeaders= new HttpHeaders();
			 responseHeaders.set(xTotalCount, String.valueOf(createResponse.size()));
			 return new ResponseEntity<>(createResponse, responseHeaders, HttpStatus.OK);
		}
	 
	 @GetMapping("/v1.0/QuestionGroup/freeTextSearch")
		@Operation(summary = "Search Questions By FreeText")
		@Timed(value = "service.configuration")
		public ResponseEntity<List<QuestionsGroup>> findByFreeText(HttpServletRequest request,@RequestParam(value = "pageNumber", required = true, defaultValue = "0") int pageNumber,
				@RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize,
				 @RequestParam(value = "sortAs", required = false,defaultValue ="DESC") String sortAs,
				 @RequestParam(value = "sortBy", required = false, defaultValue ="id") String sortBy,
				 @RequestParam(value = "groupName", required = false) String groupName,
				 @RequestParam(value = "questionName", required = false) String questionName,
				 @RequestParam(value = "queueName", required = false) String queueName,
				 @RequestParam(value = "tenantName", required = false) String tenantName,
				 @RequestParam(value = "enabled", required = false) Boolean enabled,
				 @RequestParam(value = "groupId", required = false) Integer groupId,
				 @RequestParam(value = "searchValue", required = true) String searchValue)
				 throws IllegalAccessException, InvocationTargetException {
		   //logstashUtils.addToLogContext("X-serviceMethodName", "findByFreeText");
			List<QuestionsGroup> questions = questionsGroupService.findByFreeText(pageNumber,pageSize,sortAs,sortBy,groupName,questionName,queueName,tenantName,enabled,groupId,searchValue);
			return new ResponseEntity<>(questions, HttpStatus.OK);
		}
	 
	 @RequestMapping(value = "/v1.0/QuestionGroup/getFreeTextSearchCount", method = RequestMethod.HEAD)
		@Operation(summary = "Get Free Text Search Count")
		@Timed(value = "service.configuration")
		public ResponseEntity<Integer> getFreeTextSearchCount(HttpServletRequest request,
				 @RequestParam(value = "groupName", required = false) String groupName,
				 @RequestParam(value = "questionName", required = false) String questionName,
				 @RequestParam(value = "queueName", required = false) String queueName,
				 @RequestParam(value = "tenantName", required = false) String tenantName,
				 @RequestParam(value = "enabled", required = false) Boolean enabled,
				 @RequestParam(value = "groupId", required = false) Integer groupId,
				 @RequestParam(value = "searchValue", required = true) String searchValue)
	       {
	    	//logstashUtils.addToLogContext("X-serviceMethodName", "getFreeTextSearchCount");
			Integer questionGroupCount = questionsGroupService.findByFreeTextCount(groupName,questionName,queueName,tenantName,enabled,groupId,searchValue);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set(xTotalCount, String.valueOf(questionGroupCount));
			return ResponseEntity.ok().headers(responseHeaders).body(null);
		}
	 
	 @GetMapping("/v1.0/QuestionGroup/searchByQuestionGroupAttributes")
		@Operation(summary = "Get  Questions using QuestionGroup Attributes")
		@Timed(value = "service.configuration")
		public ResponseEntity<List<Question>> getQuesionsByUsingAttributes2(
				 @RequestParam(value = "groupName", required = false) String groupName,
				 @RequestParam(value = "questionName", required = false) String questionName,
				 @RequestParam(value = "queueName", required = false) String queueName,
				 @RequestParam(value = "tenantName", required = false) String tenantName,
				 @RequestParam(value = "enabled", required = false) Boolean enabled,
				 @RequestParam(value = "groupId", required = false) Integer groupId
				 ) throws IllegalAccessException, InvocationTargetException{
	    	//logstashUtils.addToLogContext("X-serviceMethodName", "getQuesionsByUsingAttributes");
	    	List<Question> createResponse = questionsGroupService.getByQuestionsUsingQUestionGroupAttributes(groupName, questionName,queueName,tenantName,enabled,groupId);
			return new ResponseEntity<>(createResponse, HttpStatus.OK);
		}
	 
	 
	 @GetMapping("/v1.0/QuestionGroup/QuestionsList")
		@Operation(summary = "Get All QuestionsGroup along with QuestionsList")
		@Timed(value = "service.configuration") 
		public ResponseEntity<List<QuestionsGroupWithQuestionsRequestResponse>> getAllQuestionsGroupWithQUestionsList(HttpServletRequest request) {
			//logstashUtils.addToLogContext(xServiceName, UUID.randomUUID().toString());
			//logstashUtils.addToLogContext("X-serviceMethodName", "getAllQuestions");
			List<QuestionsGroupWithQuestionsRequestResponse> questions= questionsGroupService.getAllQuestionsGroupAlongWithQuestionsList();
			return new ResponseEntity<>(questions, HttpStatus.OK);
		}
	 
	 @GetMapping("/v1.0/QuestionGroup/QueueName/QuestionsList")
		@Operation(summary = "Get All QuestionsGroup along with QuestionsList based on queueName")
		@Timed(value = "service.configuration") 
		public ResponseEntity<List<QuestionsGroupWithQuestionsRequestResponse>> getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueName(HttpServletRequest request,
				@RequestParam(value = "queueName", required = true) String queueName,
				@RequestParam(value = "enabled", required = true) Boolean enabled) {
			//logstashUtils.addToLogContext(xServiceName, UUID.randomUUID().toString());
			//logstashUtils.addToLogContext("X-serviceMethodName", "getAllQuestions");
			List<QuestionsGroupWithQuestionsRequestResponse> questions= questionsGroupService.getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueName(queueName,enabled);
			return new ResponseEntity<>(questions, HttpStatus.OK);
		}
	 
	 @PatchMapping("/v1.0/QuestionGroup/{groupName}/enable")
	 @Operation(summary = "Enable or Disable QuestionGroup By groupName")
	 @Timed(value = "service.configuration")
	 public ResponseEntity<MessageResponse> enableQuestionGroupByGroupName(
	         @PathVariable String groupName,
	         @RequestParam boolean enable,
	         HttpServletRequest request) {
	     //logstashUtils.addToLogContext(xServiceName, groupName);
	     //logstashUtils.addToLogContext("X-serviceMethodName", "enableQuestionGroupByGroupName");

	     MessageResponse enableResponse = questionsGroupService.enableQuestionGroupByGroupName(groupName, enable);

	     return new ResponseEntity<>(enableResponse, HttpStatus.OK);
	 }
		
	 
	 @GetMapping("/v1.0/QuestionGroup/QueueName/Ein/QuestionsList")
		@Operation(summary = "Get All QuestionsGroup along with QuestionsList based on queue name")
		@Timed(value = "service.configuration") 
		public ResponseEntity<List<QuestionsGroupWithQuestionsRequestResponse>> getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueNameAndEIN(HttpServletRequest request,
				@RequestParam(value = "queueName", required = true) String queueName,
				 @RequestParam(value = "EIN", required = true) String ein,
				@RequestParam(value = "enabled", required = true) Boolean enabled) {
			//logstashUtils.addToLogContext(xServiceName, UUID.randomUUID().toString());
			//logstashUtils.addToLogContext("X-serviceMethodName", "getAllQuestions");
			List<QuestionsGroupWithQuestionsRequestResponse> questions= questionsGroupService.getAllQuestionsGroupAlongWithQuestionsListBasedOnQueueNameAndEINDetails(queueName,enabled,ein);
			return new ResponseEntity<>(questions, HttpStatus.OK);
		}
	 
	 
	 @GetMapping("/v1.0/QuestionGroup/Ein/QuestionsListAndQueueNames")
		@Operation(summary = "Get All QuestionsGroup along with QuestionsList based on EIN")
		@Timed(value = "service.configuration") 
		public ResponseEntity<List<QuestionsGroupWithQuestionsRequestResponse>> getAllQuestionsGroupAlongWithQuestionsListBasedOnEIN(HttpServletRequest request,
				 @RequestParam(value = "EIN", required = true) String ein,
				 @RequestParam(value = "tenantName", required = false,defaultValue = "Autofix") String tenantName) {
			//logstashUtils.addToLogContext(xServiceName, UUID.randomUUID().toString());
			//logstashUtils.addToLogContext("X-serviceMethodName", "getAllQuestions");
			List<QuestionsGroupWithQuestionsRequestResponse> questions= questionsGroupService.getAllQuestionsGroupAlongWithQuestionsListBasedOnEIN(ein,tenantName);
			return new ResponseEntity<>(questions, HttpStatus.OK);
		}


}


