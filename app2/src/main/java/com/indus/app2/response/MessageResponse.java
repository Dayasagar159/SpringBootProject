package com.indus.app2.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(Include.NON_DEFAULT)
public class MessageResponse {

	private String conflictStatus;

	private String statusMessage;

	private String statusCode;
	
	private int id;
	
	private String tenantName;
}
