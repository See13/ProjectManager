package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.entity.ParentTask;

/**
 * This is an interface which extends JpaRepository and
 * performs CRUD operation.
 * 
 * @author SeemaYadav
 * @version 1.0
 * @since 09-16-2019
 */
@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTask, Long>{
	 public ParentTask findById(long parentTaskId);
}
