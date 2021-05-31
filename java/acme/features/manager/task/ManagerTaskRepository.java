/*
 * AdministratorUserAccountRepository.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.manager.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.task.Task;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {



	@Query("select ua from Task ua")
	Collection<Task> findAllTask();
	
	
	@Query("select ua from Task ua where ua.id = ?1 and ua.userAccount.id = ?2")
	Task findOneTaskByIdAndUA(int idTask,int idUA);
	
	@Query("select t from Task t where t.id = ?1")
	Task findTaskById(int id);
	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);
	}
