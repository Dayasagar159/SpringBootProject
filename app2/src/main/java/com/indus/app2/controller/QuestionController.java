package com.indus.app2.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
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
import com.indus.app2.response.MessageResponse;
import com.indus.app2.response.QuestionRequestResponse;
import com.indus.app2.service.QuestionService;

import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Question controller")
@SecurityRequirement(name = "security_auth")
public class QuestionController 
{
	
	/*
	 * @Autowired private LogUtils logstashUtils;
	 */
	@Autowired
	private QuestionService questionService;

	
	private String xServiceName = "X-serviceId";
	private String xTotalCount = "xTotalCount";

	@PostMapping("/v1.0/Question")
	@Operation(summary = "Create a Question")
	@Timed(value = "service.configuration")
	public ResponseEntity<MessageResponse> createQuestion(@RequestBody QuestionRequestResponse questionRequestResponse,
			HttpServletRequest request) {
		//logstashUtils.addToLogContext(xServiceName, UUID.randomUUID().toString());
		//logstashUtils.addToLogContext("X-serviceMethodName", "createQuestion");
		MessageResponse saveRecord = questionService.save(questionRequestResponse);
		if (!saveRecord.getConflictStatus().equals("200")) {
			return new ResponseEntity<>(saveRecord, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(saveRecord, HttpStatus.CREATED);
	}
	
	@PatchMapping("/v1.0/Question/{id}")
	@Operation(summary = "update an Question")
	@Timed(value = "service.configuration")
	public ResponseEntity<MessageResponse> updateQuestion(@PathVariable int id,@RequestBody QuestionRequestResponse questionRequestResponse,
			HttpServletRequest request) {
		//logstashUtils.addToLogContext(xServiceName, UUID.randomUUID().toString());
		//logstashUtils.addToLogContext("X-serviceMethodName", "updateQuestion");
		MessageResponse messageResponse = questionService.updateQuestion(id,questionRequestResponse);
		return new ResponseEntity<>(messageResponse, HttpStatus.OK);
	}
	
	@GetMapping("/v1.0/Question/{id}")
	@Operation(summary = "Get Question By  ID")
	@Timed(value = "service.configuration") 
	public ResponseEntity<Question> getQuestion(@PathVariable int id, HttpServletRequest request,
			@RequestParam(value = "tenantName", required = false,defaultValue ="Autofix") String tenantName) {
		//logstashUtils.addToLogContext(xServiceName, Integer.toString(id));
		//logstashUtils.addToLogContext("X-serviceMethodName", "getQuestion");
		Question question = questionService.getQuestionById(id,tenantName);
		return new ResponseEntity<>(question, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/v1.0/Question/{id}")
	@Operation(summary = "Delete Question By  Id")
	@Timed(value = "service.configuration") 
	public ResponseEntity<MessageResponse> deleteQuestionById(@PathVariable int id, HttpServletRequest request) {
		//logstashUtils.addToLogContext(xServiceName, Integer.toString(id));
		//logstashUtils.addToLogContext("X-serviceMethodName", "deleteQuestionById");
		MessageResponse deleteQuestion  = questionService.deleteQuestionById(id);
		return new ResponseEntity<>(deleteQuestion, HttpStatus.OK);
	}
	
	@GetMapping("/v1.0/Questions")
	@Operation(summary = "Get All Questions")
	@Timed(value = "service.configuration") 
	public ResponseEntity<List<Question>> getAllQuestions(HttpServletRequest request) {
		//logstashUtils.addToLogContext(xServiceName, UUID.randomUUID().toString());
		//logstashUtils.addToLogContext("X-serviceMethodName", "getAllQuestions");
		List<Question> questions= questionService.getAllQuestions();
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/v1.0/Questions/Search")
	@Operation(summary = "Get By Questions using Search")
	@Timed(value = "service.configuration")
	public ResponseEntity<List<Question>> getListOfQuestionsUsingParameters(@RequestParam(value = "pageNumber", required = true, defaultValue = "0") int pageNumber,
	 @RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize,
	 @RequestParam(value = "sortAs", required = false,defaultValue ="DESC") String sortAs,
	 @RequestParam(value = "sortBy", required = false, defaultValue ="id") String sortBy,
	 @RequestParam(value = "questionName", required = false) String questionName,
	 @RequestParam(value = "description", required = false) String description,
	 @RequestParam(value = "type", required = false) String type,
	 @RequestParam(value = "mandatory", required = false) String mandatory,
	 @RequestParam(value = "enabled", required = false) Boolean enabled,
	 @RequestParam(value = "details", required = false) String details) throws IllegalAccessException, InvocationTargetException{
    	//logstashUtils.addToLogContext("X-serviceMethodName", "getListOfQuestionsUsingParameters");
    	List<Question> response = questionService.getListOfQuesions(pageNumber, pageSize, sortAs, sortBy, questionName, description,type,enabled,details,mandatory);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
	 @GetMapping("/v1.0/Questions/SearchParameters")
	    @Operation(summary = "Get Questions and Count using SearchParameters")
	    @Timed(value = "service.configuration")
	    public ResponseEntity<Map<String, Object>> getListOfQuestionsAndCountUsingParameters(
	            @RequestParam(value = "pageNumber", required = true, defaultValue = "1") int pageNumber,
	            @RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize,
	            @RequestParam(value = "sortAs", required = false, defaultValue ="DESC") String sortAs,
	            @RequestParam(value = "sortBy", required = false, defaultValue ="id") String sortBy,
	            @RequestParam(value = "questionName", required = false) String questionName,
	            @RequestParam(value = "mandatory", required = false) String mandatory,
	            @RequestParam(value = "description", required = false) String description) throws IllegalAccessException, InvocationTargetException{
		 		//logstashUtils.addToLogContext("X-serviceMethodName", "getListOfQuestionsAndCountUsingParameters");

	        try {
	            ResponseEntity<Map<String, Object>> response = questionService.getListOfQuestionsUsingParameters1(
	                    pageNumber, pageSize, sortAs, sortBy, questionName, description,mandatory);
	            return ResponseEntity.ok(response.getBody());
	        } catch (Exception e) {
	            // Handle exceptions appropriately
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 
	 @RequestMapping(value = "/v1.0/Questions/count", method = RequestMethod.HEAD)
		@Operation(summary = "Get Count of Quesions by Attributes")
		@Timed(value = "service.configuration")
		public ResponseEntity<List<Question>> getCountWithParameters(@RequestParam(value = "pageNumber", required = true, defaultValue = "0") int pageNumber,
		@RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize,
		 @RequestParam(value = "sortAs", required = false,defaultValue ="DESC") String sortAs,
		 @RequestParam(value = "sortBy", required = false, defaultValue ="id") String sortBy,
		 @RequestParam(value = "questionName", required = false) String questionName,
		 @RequestParam(value = "description", required = false) String description,
		 @RequestParam(value = "type", required = false) String type,
		 @RequestParam(value = "mandatory", required = false) String mandatory,
		 @RequestParam(value = "enabled", required = false) Boolean enabled,
		 @RequestParam(value = "details", required = false) String details) throws IllegalAccessException, InvocationTargetException{
		 // logstashUtils.addToLogContext("X-serviceMethodName", "getCountWithParameters");
		List<Question> response = questionService.getListOfQuesions(pageNumber, pageSize, sortAs, sortBy, questionName, description,type,enabled,details,mandatory);
		 HttpHeaders responseHeaders= new HttpHeaders();
		    responseHeaders.set(xTotalCount, String.valueOf(response.size()));
		    return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);
		}
	
	 
	 @RequestMapping(value = "/v1.0/Questions/searchByAttributes/count", method = RequestMethod.HEAD)
		@Operation(summary = "Count By Quesions using Attributes")
		@Timed(value = "service.configuration")
		public ResponseEntity<List<Question>> getByAttributesCount(
				 @RequestParam(value = "questionName", required = false) String questionName,
				 @RequestParam(value = "description", required = false) String description,
				 @RequestParam(value = "type", required = false) String type,
				 @RequestParam(value = "enabled", required = false) Boolean enabled,
				 @RequestParam(value = "mandatory", required = false) String mandatory,
				 @RequestParam(value = "details", required = false) String details) throws IllegalAccessException, InvocationTargetException{
		//logstashUtils.addToLogContext("X-serviceMethodName", "getByAttributesCount");
		List<Question> createResponse = questionService.getByAttributes(questionName, description,type,enabled,details,mandatory);
		HttpHeaders responseHeaders= new HttpHeaders();
			 responseHeaders.set(xTotalCount, String.valueOf(createResponse.size()));
			 return new ResponseEntity<>(createResponse, responseHeaders, HttpStatus.OK);
		}
	 
	 
	 @GetMapping("/v1.0/Questions/searchByAttributes")
		@Operation(summary = "Get  Questions using Attributes")
		@Timed(value = "service.configuration")
		public ResponseEntity<List<Question>> getQuesionsByUsingAttributes(
				 @RequestParam(value = "questionName", required = false) String questionName,
				 @RequestParam(value = "description", required = false) String description,
				 @RequestParam(value = "type", required = false) String type,
				 @RequestParam(value = "mandatory", required = false) String mandatory,
				 @RequestParam(value = "enabled", required = false) Boolean enabled,
				 @RequestParam(value = "details", required = false) String details) throws IllegalAccessException, InvocationTargetException{
	    	//logstashUtils.addToLogContext("X-serviceMethodName", "getQuesionsByUsingAttributes");
	    	List<Question> createResponse = questionService.getByAttributes(questionName, description,type,enabled,details,mandatory);
			return new ResponseEntity<>(createResponse, HttpStatus.OK);
		}
	 
	 @GetMapping("/v1.0/Questions/freeTextSearch")
		@Operation(summary = "Search Questions By FreeText")
		@Timed(value = "service.configuration")
		public ResponseEntity<List<Question>> findByFreeText(HttpServletRequest request,@RequestParam(value = "pageNumber", required = true, defaultValue = "0") int pageNumber,
				@RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize,
				 @RequestParam(value = "sortAs", required = false,defaultValue ="DESC") String sortAs,
				 @RequestParam(value = "sortBy", required = false, defaultValue ="id") String sortBy,
				 @RequestParam(value = "questionName", required = false) String questionName,
				 @RequestParam(value = "description", required = false) String description,
				 @RequestParam(value = "type", required = false) String type,
				 @RequestParam(value = "mandatory", required = false) String mandatory,
				 @RequestParam(value = "enabled", required = false) Boolean enabled,
				 @RequestParam(value = "details", required = false) String details,
				 @RequestParam(value = "searchValue", required = true) String searchValue)
				 throws IllegalAccessException, InvocationTargetException {
		   //logstashUtils.addToLogContext("X-serviceMethodName", "findByFreeText");
			List<Question> questions = questionService.findByFreeText(pageNumber,pageSize,sortAs,sortBy,questionName,description,type,enabled,details,searchValue,mandatory);
			return new ResponseEntity<>(questions, HttpStatus.OK);
		}
	 
	 
	 @RequestMapping(value = "/v1.0/Questions/getFreeTextSearchCount", method = RequestMethod.HEAD)
		@Operation(summary = "Get Free Text Search Count")
		@Timed(value = "service.configuration")
		public ResponseEntity<Integer> getFreeTextSearchCount(HttpServletRequest request,
				@RequestParam(value = "questionName", required = false) String questionName,
				 @RequestParam(value = "description", required = false) String description,
				 @RequestParam(value = "type", required = false) String type,
				 @RequestParam(value = "enabled", required = false) Boolean enabled,
				 @RequestParam(value = "details", required = false) String details,
				 @RequestParam(value = "mandatory", required = false) String mandatory,
				 @RequestParam(value = "searchValue", required = true)String searchValue)
	       {
	    	//logstashUtils.addToLogContext("X-serviceMethodName", "getFreeTextSearchCount");
			Integer questionCount = questionService.findByFreeTextCount(questionName,description,type,enabled,details,searchValue,mandatory);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set(xTotalCount, String.valueOf(questionCount));
			return ResponseEntity.ok().headers(responseHeaders).body(null);
		}
	 
	 
	 @DeleteMapping("/v1.0/Question")
		@Operation(summary = "Delete All the Question")
		@Timed(value = "service.configuration") 
		public ResponseEntity<MessageResponse> deleteAllTheQuestions(HttpServletRequest request) {
			//logstashUtils.addToLogContext("X-serviceMethodName", "deleteQuestionById");
			MessageResponse deleteQuestions  = questionService.deleteQuestions();
			return new ResponseEntity<>(deleteQuestions, HttpStatus.OK);
		}
		
	
	
}
