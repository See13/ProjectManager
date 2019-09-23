package com.cognizant.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.entity.ParentTask;

/**
 * Prerequisite data is inserted ProjectManagerApplication.run()
 * 
 * @author seema.yadav
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class ParentTaskRepositoryIntegrationTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private ParentTaskRepository parentTaskRepository;

	@Test
	public void whenFindById_thenReturnParentTask() {
		// given
		ParentTask p1 = new ParentTask(1, "ParentTask1");

		// when
		ParentTask found = parentTaskRepository.findById(p1.getParentTaskId());

		// then
		assertThat(found.getParentTask()).isEqualTo(p1.getParentTask());
	}
}
