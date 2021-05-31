/*
 * AuthenticatedTaskListService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.task.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedTaskListService implements AbstractListService<Authenticated, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedTaskRepository repository;

	// AbstractListService<Authenticated, Task> interface --------------


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;


		request.unbind(entity, model, "title", "executionPeriod.finalDate", "executionPeriod.initialDate","workload");
		


		
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		assert request != null;

		Collection<Task> result;

		final List<Task> result2 = new ArrayList<Task>();
		
		result = this.repository.findAllTaskNotPrivate();
		
		
		for( final Task t:result) {
			if(t.getExecutionPeriod().getFinalDate().isBefore(LocalDateTime.now())){
				result2.add(t);
			}
		}
		return result2;
	}

}
