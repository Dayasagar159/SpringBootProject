package com.indus.app2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "QUESTIONS_GROUP")
@Getter
@Setter
@ToString
public class QuestionsGroup 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	 private int id;
	
	@Column(name = "group_Id")
	 private Integer groupId;
	
	 @Column(name = "group_Name")
     private String groupName;
	 
		/*
		 * @Column(name = "question_Id") private int questionId;
		 */
	 @Column(name = "question_name")
	 private String questionName;
	 
	 @Column(name = "queue_name")
     private String queueName;
	 
	 @Column(name = "tenant_Name")
     private String tenantName;
	 
	 @Column(name = "enabled")
     private boolean enabled;

}
