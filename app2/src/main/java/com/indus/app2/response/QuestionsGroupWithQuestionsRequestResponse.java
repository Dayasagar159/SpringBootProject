package com.indus.app2.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.indus.app2.model.Question;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class QuestionsGroupWithQuestionsRequestResponse 
{

     

     private String groupName;
	 private List<Question> questions;
	 private int questionGroupId;
	 private String queueName;
	 
	 public QuestionsGroupWithQuestionsRequestResponse() {
	        this.questions = new ArrayList<>();
	    }
    
}