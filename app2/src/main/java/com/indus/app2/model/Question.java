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
@Table(name = "QUESTION")
@Getter
@Setter
@ToString
public class Question 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	 private int id;
	
	 @Column(name = "question_Name")
     private String questionName;
	 
	 @Column(name = "description")
     private String description;
	 
	 @Column(name = "type")
     private String type;
	 
	 @Column(name = "details")
     private String details;
	 
	 @Column(name = "tenant_Name")
     private String tenantName;
	 
	 @Column(name = "enabled")
     private boolean enabled;
	 
	 @Column(name = "mandatory")
     private String mandatory;

}
