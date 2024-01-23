package com.indus.app2.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class QestionswithGroupRequestResponse 
{
	 private Integer groupId;

     private String groupName;
	 
     private List<String> questionNames;
	 
     private String tenantName;
	 
     private boolean enabled;
     
     private String queueName;

}
