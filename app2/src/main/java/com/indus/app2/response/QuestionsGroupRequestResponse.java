package com.indus.app2.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class QuestionsGroupRequestResponse 
{

     private Integer groupId;

     private String groupName;
	 
     private String questionName;
	 
     private String tenantName;
	 
     private boolean enabled;
     
     private String queueName;
    
}
