package com.indus.app2.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.indus.app2.model.Question;

@Repository
public interface QuestionPagingRepository extends PagingAndSortingRepository<Question, Long> {
    
      @Query(value = "SELECT f FROM Question f WHERE " +
            "((:questionName) is null or f.questionName in (:questionName)) and " +
            "((:mandatory) is null or f.mandatory in (:mandatory)) and " +
            "((:description) is null or f.description in (:description))",
            countQuery = "SELECT count(*) FROM Question f WHERE " +
            "((:questionName) is null or f.questionName in (:questionName)) and " +
            "((:mandatory) is null or f.mandatory in (:mandatory)) and " +
            "((:description) is null or f.description in (:description))")
	Page<Question> findBySearchWithParameter(Pageable paging, String questionName, String description,String mandatory);
      
      
      @Query(value="SELECT wha FROM Question  wha WHERE ((:questionName) is null or wha.questionName in "
    			+ "(:questionName)) and ((:description) is null or wha.description in(:description)) "
    			+ "and ((:type) is "
    			+ "null or wha.type in (:type)) "
    			+"and ((:mandatory) is "
    			+ "null or wha.mandatory in (:mandatory)) "
    			+"and ((:mandatory) is "
    			+ "null or wha.mandatory in (:mandatory)) "
    			+"and ((:details) is "
    			+ "null or wha.details in (:details)) "
    			+ "and (:enabled is null or wha.enabled = :enabled)",
    			countQuery="SELECT count(*) FROM Question  wha WHERE ((:questionName) is null or wha.questionName in "
    			+ "(:questionName)) and ((:description) is null or wha.description in(:description)) and ((:type) is "
    			+ "null or wha.type in (:type)) and (:enabled is null or wha.enabled = :enabled)"
    			+ "and ((:details) is null or wha.details in (:details))")

	Page<Question> findBySearchWithParameter(Pageable paging, String questionName, String description, String type,Boolean enabled,String details,String mandatory);

      
      @Query("select wha from Question wha "
  			+ " WHERE (:questionName IS NULL OR wha.questionName = :questionName) AND "
  			+ "(:description IS NULL OR wha.description = :description) AND "
  			+ "(:type IS NULL OR wha.type = :type) AND "
  			+ "(:mandatory IS NULL OR wha.mandatory = :mandatory) AND "
  			+ "(:enabled IS NULL OR wha.enabled = :enabled) AND "
  			+ "(:details IS NULL OR wha.details = :details) AND "
  			+ "(:searchValue IS NULL OR LOWER(wha.questionName) LIKE  LOWER(CONCAT('%', :searchValue ,'%')) OR "
  			+ "LOWER(wha.description) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
  			+ "LOWER(wha.type) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
  			+ "LOWER(wha.mandatory) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
  			+ "LOWER(CAST(wha.enabled AS string)) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
  			+ "LOWER(wha.details) LIKE LOWER(CONCAT('%', :searchValue,'%')))")

	Page<Question> findByFreeText(Pageable paging, String questionName, String description, String type,
			Boolean enabled, String details, String searchValue,String mandatory);

      
      @Query("select wha from Question wha "
    			+ " WHERE (:questionName IS NULL OR wha.questionName = :questionName) AND "
    			+ "(:description IS NULL OR wha.description = :description) AND "
    			+ "(:type IS NULL OR wha.type = :type) AND "
    			+ "(:mandatory IS NULL OR wha.mandatory = :mandatory) AND "
    			+ "(:enabled IS NULL OR wha.enabled = :enabled) AND "
    			+ "(:details IS NULL OR wha.details = :details) AND "
    			+ "(:searchValue IS NULL OR LOWER(wha.questionName) LIKE  LOWER(CONCAT('%', :searchValue ,'%')) OR "
    			+ "LOWER(wha.description) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
    			+ "LOWER(wha.type) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
    			+ "LOWER(wha.mandatory) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
    			+ "LOWER(CAST(wha.enabled AS string)) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
    			+ "LOWER(wha.details) LIKE LOWER(CONCAT('%', :searchValue,'%')))")
	List<Question> findByFreeTextCount(String searchValue, String questionName, String description, String type,
			Boolean enabled, String details,String mandatory);
    
}
