package com.indus.app2.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.indus.app2.model.QuestionsGroup;

@Repository
public interface QuestionsGroupPagingRepository extends PagingAndSortingRepository<QuestionsGroup, Long> {
    
      @Query(value = "SELECT f FROM QuestionsGroup f WHERE " +
            "((:groupName) is null or f.groupName in (:groupName)) and " +
            "((:queueName) is null or f.queueName in (:queueName))",
            countQuery = "SELECT count(*) FROM QuestionsGroup f WHERE " +
            "((:groupName) is null or f.groupName in (:groupName)) and " +
            "((:queueName) is null or f.queueName in (:queueName))")
	Page<QuestionsGroup> findBySearchWithParameter(Pageable paging, String groupName, String queueName);

      
      
      @Query("select wha from QuestionsGroup wha "
    			+ " WHERE (:groupName IS NULL OR wha.groupName = :groupName) AND "
    			+ "(:questionName IS NULL OR wha.questionName = :questionName) AND "
    			+ "(:queueName IS NULL OR wha.queueName = :queueName) AND "
    			+ "(:tenantName IS NULL OR wha.tenantName = :tenantName) AND "
    			+ "(:enabled IS NULL OR wha.enabled = :enabled) AND "
    			+ "(:groupId IS NULL OR wha.groupId = :groupId) AND "
    			+ "(:searchValue IS NULL OR LOWER(wha.questionName) LIKE  LOWER(CONCAT('%', :searchValue ,'%')) OR "
    			+ "LOWER(wha.groupName) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
    			+ "LOWER(wha.questionName) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
    			+ "LOWER(wha.queueName) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
    			+ "LOWER(wha.tenantName) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
    			+ "LOWER(CAST(wha.enabled AS string)) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
    			+ "LOWER(CAST(wha.groupId AS string)) LIKE LOWER(CONCAT('%', :searchValue,'%')))")

	Page<QuestionsGroup> findByFreeText(Pageable paging, String groupName, String questionName, String queueName,
			String tenantName, Boolean enabled, Integer groupId, String searchValue);


      
      @Query("select wha from QuestionsGroup wha "
  			+ " WHERE (:groupName IS NULL OR wha.groupName = :groupName) AND "
  			+ "(:questionName IS NULL OR wha.questionName = :questionName) AND "
  			+ "(:queueName IS NULL OR wha.queueName = :queueName) AND "
  			+ "(:tenantName IS NULL OR wha.tenantName = :tenantName) AND "
  			+ "(:enabled IS NULL OR wha.enabled = :enabled) AND "
  			+ "(:groupId IS NULL OR wha.groupId = :groupId) AND "
  			+ "(:searchValue IS NULL OR LOWER(wha.questionName) LIKE  LOWER(CONCAT('%', :searchValue ,'%')) OR "
  			+ "LOWER(wha.groupName) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
  			+ "LOWER(wha.questionName) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
  			+ "LOWER(wha.queueName) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
  			+ "LOWER(wha.tenantName) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
  			+ "LOWER(CAST(wha.enabled AS string)) LIKE LOWER(CONCAT('%', :searchValue ,'%')) OR "
  			+ "LOWER(CAST(wha.groupId AS string)) LIKE LOWER(CONCAT('%', :searchValue,'%')))")
	List<QuestionsGroup> findByFreeTextCount(String searchValue, String groupName, String questionName,
			String queueName, String tenantName, Boolean enabled, Integer groupId);

    
}