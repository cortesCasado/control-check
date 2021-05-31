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
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.task.Task;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {

	@Query("select t from Task t")
	Collection<Task> findAllTask();
	
	@Query("select t from Task t where t.id = ?1 and t.userAccount.id = ?2")
	Task findOneTaskByIdAndUA(int idTask,int idUA);
	
	@Query("select t from Task t where t.id = ?1")
	Task findTaskById(int id);
	
	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);
	
	@Query("select t from Task t where t.userAccount.id = ?1")
	List<Task> findAllMyTask(int id);
	
	@Query("select t from Task t where t.userAccount.id = ?1 and t.isPrivate = false")
	List<Task> findAllMyTaskOnlyPublic(int id);
	
//	@Query("SELECT t.workplan FROM Task t AND t.uaId = ?2")
//	List<Task> findAlreadySelectedByWorkplanId(Workplan w, int uaId);
//	
//	@Query("SELECT t.workplan FROM Task t AND t.uaId = ?2")
//	List<Task> findNotSelectedByWorkplanId(Workplan w, int uaId);
}
