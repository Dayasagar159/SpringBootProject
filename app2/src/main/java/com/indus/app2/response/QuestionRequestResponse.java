package com.indus.app2.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class QuestionRequestResponse 
{

     private String questionName;

     private String description;
	 
     private String type;
	 
     private String details;
	 
     private String tenantName;
	 
     private boolean enabled;
     
     private String mandatory; 
}
