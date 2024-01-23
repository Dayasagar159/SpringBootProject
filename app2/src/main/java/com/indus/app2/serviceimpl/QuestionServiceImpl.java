package com.indus.app2.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.indus.app2.exception.DataNotFoundException;
import com.indus.app2.exception.ValidationException;
import com.indus.app2.model.Question;
import com.indus.app2.repository.QuestionPagingRepository;
import com.indus.app2.repository.QuestionRepository;
import com.indus.app2.response.MessageResponse;
import com.indus.app2.response.QuestionRequestResponse;
import com.indus.app2.service.QuestionService;
import com.indus.app2.util.Constants;



import jakarta.persistence.criteria.Predicate;
@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private QuestionPagingRepository questionPagingRepository;
	
	
	String announcementNotAvailable = "Announcement is not available with the given id ";
	String announcementNotAvailableByEIN = "Announcement is not available with the given EIN ";
	String CREATEDBY="createdBy";
	String TENANTNAME="tenantName";
	String TYPE="type";
	String CREATEDAT="createdAt";
	String ISACTIVE="isActive";
	String questionNotAvailable = "Question is not available with the given id ";
	String questionsNotAvailable = "Questions are not available";

	
	
	
	@Override
	public MessageResponse save(QuestionRequestResponse questionRequestResponse) {

	    Question question = new Question();
	    Question questionDetails;
	    MessageResponse messageResponse = new MessageResponse();

	    try {
	        BeanUtils.copyProperties(questionRequestResponse, question);

	        if (question != null && question.getQuestionName() != null) {
	            questionDetails = questionRepository.findByQuestionName(question.getQuestionName());

	            if (questionDetails != null) {
	                // If the description is already present, throw an exception
	                throw new ValidationException("Question Name is already present with the following Id: " + questionDetails.getId());
	            }

	            questionRepository.save(question);
	            messageResponse.setConflictStatus("200");
	            messageResponse.setStatusMessage(Constants.CREATE_QUESTION_MESSAGE);
	            messageResponse.setId(question.getId());
	        }
	    } catch (ValidationException ve) {
	        // Re-throw the ValidationException to propagate the error
	        throw ve;
	    } catch (Exception e) {
	        // Catch any other exceptions and handle accordingly
	        throw new ValidationException("An error occurred while saving the Question");
	    }

	    return messageResponse;
	}
	
	@Override
	public MessageResponse updateQuestion(int id, QuestionRequestResponse questionRequestResponse) {
		Question question = questionRepository.findById(id);
          
		  if (question == null) 
		  {
		        throw new DataNotFoundException("Question  with ID " + id + " not found");
		   }	  
		    if(questionRequestResponse.getQuestionName() != null) 
		    {
		    	question.setQuestionName(questionRequestResponse.getQuestionName());
		    }
		   
		    question.setEnabled(questionRequestResponse.isEnabled());
		    if(questionRequestResponse.getDescription() != null) 
		    {
		    	question.setDescription(questionRequestResponse.getDescription());
		    }
		    
		    if(questionRequestResponse.getType() != null) 
		    {
		    	question.setType(questionRequestResponse.getType());
		    }
		    if(questionRequestResponse.getDetails() != null) 
		    {
		    	question.setDetails(questionRequestResponse.getDetails());
		    }
		    if(questionRequestResponse.getTenantName() != null) 
		    {
		    	question.setTenantName(questionRequestResponse.getTenantName());
		    }
		    if(questionRequestResponse.getMandatory() != null) 
		    {
		    	question.setMandatory(questionRequestResponse.getMandatory());
		    }
		    questionRepository.save(question);
		    
		    MessageResponse messageResponse= new MessageResponse();
		    messageResponse.setStatusCode("200");
		    messageResponse.setStatusMessage("updated Question successfully.");
		    
		    return messageResponse;
}

	@Override
	public Question getQuestionById(int id, String tenantName) {
		

		 if (id == 0) {
		        throw new DataNotFoundException(questionNotAvailable + id);
		    }
		
		 Question question2 = questionRepository.findById(id);
		if (question2 == null) {
			throw new DataNotFoundException(questionNotAvailable + id);
		}
		
		
		// Define the query criteria
       Specification<Question> specification = (root, query, criteriaBuilder) -> {
           List<Predicate> predicates = new ArrayList<>();


           if (tenantName != null) {
               predicates.add(criteriaBuilder.equal(root.get(TENANTNAME), tenantName));
           }
           if(id !=0)
           {
           predicates.add(criteriaBuilder.equal(root.get("id"), id));
           }
           // You can add more criteria as needed

           return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
       };
       
		
       Optional<Question> questionOptional = questionRepository.findOne(specification);

       if (questionOptional.isEmpty()) {
           throw new DataNotFoundException("Question not found for ID " + id);
       }

       return questionOptional.get();
	
	}

	@Override
	public MessageResponse deleteQuestionById(int id) {
	    try {
	    	 if (id == 0) {
			        throw new DataNotFoundException(questionNotAvailable + id);
			    }
			
	    	 Question question = questionRepository.findById(id);
			if (question == null) {
				throw new DataNotFoundException(questionNotAvailable + id);
			}
			questionRepository.deleteById(id);
			MessageResponse messageResponse = new MessageResponse();
	        messageResponse.setConflictStatus("200");
	        messageResponse.setStatusMessage(Constants.DELETE_QUESTION_MESSAGE);
	        messageResponse.setId(id);
	        return messageResponse;
	    } catch (Exception e) {
	        // Handle exceptions and errors
	        throw new ValidationException("An error occurred while deleting the Question for given  id:"+id);
	    }
}

	@Override
	public List<Question> getAllQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public List<Question> getListOfQuestionsUsingParameters(Integer pageNumber, Integer pageSize, String sortAs,
			String sortBy, String questionName, String description,String mandatory) {
		Pageable paging = null;
		Page<Question> pagedResult = null;

				if (pageNumber == null && pageSize == null) {
			
		}
				// Adjust the page number to be zero-based
				if (pageNumber != null) {
				    pageNumber = pageNumber - 1;
				}
				
				
		if(pageNumber != null && pageSize != null) {  
			if(sortAs.equals("DESC") && pageNumber!= null) {
				paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
				}else if (pageNumber!= null) {
					paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
				}
		
		}
		pagedResult =questionPagingRepository.findBySearchWithParameter(paging,questionName,description,mandatory);

		List<Question> MappingList = pagedResult.getContent();

		/*
		 * for (Feedback feedback2 : MappingList) { FeedbackRequestResponse sTResponse =
		 * feedback2.setFeedback(feedback2); listResponse.add(sTResponse); }
		 */
		return MappingList;
	}

	@Override
	public ResponseEntity<Map<String, Object>> getListOfQuestionsUsingParameters1(int pageNumber, int pageSize,
			String sortAs, String sortBy, String questionName, String description,String mandatory) {
		
		
		 try {
		        Pageable paging = PageRequest.of(pageNumber - 1, pageSize, Sort.by(sortBy).descending());

		        Slice<Question> slicedResult = questionPagingRepository.findBySearchWithParameter(paging, questionName, description,mandatory);

		        List<Question> questions = slicedResult.getContent();
		        long totalElements = questions.size();

		        Map<String, Object> response = new HashMap<>();
		        response.put("questions", questions);
		        response.put("totalElements", totalElements);

		        return new ResponseEntity<>(response, HttpStatus.OK);
		    } catch (Exception e) {
		        // Handle exceptions appropriately
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}

	@Override
	public List<Question> getListOfQuesions(Integer pageNumber, Integer pageSize, String sortAs, String sortBy,
			String questionName, String description, String type, Boolean enabled,
			String details,String mandatory) {
		Pageable paging = null;
		Page<Question> pagedResult = null;
		List<Question> listResponse = new ArrayList<>();

				if (pageNumber == null && pageSize == null) {
			
		}
		if(pageNumber != null && pageSize != null) {  
			if(sortAs.equals("DESC") && pageNumber!= null) {
				paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
				}else if (pageNumber!= null) {
					paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
				}
		
		}
		pagedResult =questionPagingRepository.findBySearchWithParameter(paging,questionName, description,type,enabled,details,mandatory);

		List<Question> MappingList = pagedResult.getContent();
		return MappingList;
	}

	@Override
	public List<Question> getByAttributes(String questionName, String description, String type,
			Boolean enabled, String details,String mandatory) {
		List<Question> questionsList = questionRepository.findPotentialMatch(questionName, description,type,enabled,details,mandatory);
		if (questionsList == null) {
			throw new DataNotFoundException("Data not found : " + questionsList);
	}
		
		return questionsList;
	}

	@Override
	public List<Question> findByFreeText(Integer pageNumber, Integer pageSize, String sortAs, String sortBy,
			String questionName, String description, String type, Boolean enabled,
			String details, String searchValue,String mandatory) {
			Pageable paging = null;
			Page<Question> pagedResult = null;
			List<Question> listResponse = new ArrayList<>();

					if (pageNumber == null && pageSize == null) {
				
			}
			if(pageNumber != null && pageSize != null) {  
				if(sortAs.equals("DESC") && pageNumber!= null) {
					paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
					}else if (pageNumber!= null) {
						paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
					}
			
			}
			pagedResult =questionPagingRepository.findByFreeText(paging,questionName, description,type,enabled,details,searchValue,mandatory);

			List<Question> MappingList = pagedResult.getContent();
			return MappingList;
			
		}

	@Override
	public Integer findByFreeTextCount(String questionName, String description, String type,
			Boolean enabled, String details, String searchValue,String mandatory) {
		return questionPagingRepository.findByFreeTextCount(searchValue,questionName, description,type,enabled,details,mandatory).size();
		}

	@Override
	public MessageResponse deleteQuestions() {
	    try {	
	    	 List<Question> questions = questionRepository.findAll();
			if (questions.isEmpty()) {
				throw new DataNotFoundException(questionsNotAvailable);
			}
			 for (Question question : questions) {
				 questionRepository.deleteById(question.getId());
		        }
			MessageResponse messageResponse = new MessageResponse();
	        messageResponse.setConflictStatus("200");
	        messageResponse.setStatusMessage(Constants.DELETE_QUESTIONS_MESSAGE);
	        return messageResponse;
	    } catch (Exception e) {
	        // Handle exceptions and errors
	        throw new ValidationException("An error occurred while deleting the Questions");
	    }
}
	}
