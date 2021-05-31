/*
 * AuthenticatedTaskUpdateService.java
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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.Spam.Spam1;
import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;
	
	@Autowired
	private AdministratorSpamRepository	spamRepository;

	// AbstractUpdateService<Authenticated, Task> interface -------------


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		final String rol =request.getPrincipal().getActiveRole().getSimpleName();
		final int userAcountId = request.getPrincipal().getAccountId();
		final int taskId = request.getModel().getInteger("id");
		
		final Task task = this.repository.findOneTaskByIdAndUA(taskId, userAcountId);
		
		return (rol.equals("Manager") && task != null);
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;


		request.unbind(entity, model, "title", "executionPeriod.finalDate", "executionPeriod.initialDate","workload","description","url");
	
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;

		Task result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findTaskById(id);

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		  
		if(!errors.hasErrors()) {
			
		
		final LocalDateTime initialDate = entity.getExecutionPeriod().getInitialDate();
	    final LocalDateTime finalDate = entity.getExecutionPeriod().getFinalDate();
		final double dur = Duration.between(initialDate, finalDate).toMinutes();
		
		final boolean condition7 = entity.getWorkload()==null || entity.getWorkload().toString().isEmpty() || entity.getWorkload()<0;
		if(condition7) {
			errors.state(request, !(condition7), "workload", "manager.task.form.error.workloadvacio");

		}else {
			final int minutos2 = (int) ((double)entity.getWorkload());
			

			final double minutosC = entity.getWorkload() - minutos2;
			
			final int minutos = (int) (entity.getWorkload() -minutosC);

			
			final double minutos_totales = (minutos*60 +minutosC*100);
			
			if(entity.getWorkload()!=null) {
				final boolean condition5 = minutos_totales>dur;
				final boolean condition6 = minutos_totales<0;
				errors.state(request, !(condition5 || condition6), "workload", "manager.task.form.error.workloaderroneo");

			}
		}

	
		
		
		
		

		
		
		final boolean condition1 = !Spam1.isSpam(entity.getTitle(), this.spamRepository.findSpam());
		final boolean condition2 = !Spam1.isSpam(entity.getDescription(), this.spamRepository.findSpam());
		
		if(this.repository.findTaskById(entity.getId()).getExecutionPeriod().getInitialDate()!=null) {
			final boolean condition3 = !(initialDate.isBefore(LocalDateTime.now()) && !entity.getExecutionPeriod().getInitialDate().equals(this.repository.findTaskById(entity.getId()).getExecutionPeriod().getInitialDate()));
			errors.state(request, condition3, "executionPeriod.initialDate", "manager.task.form.error.initialDate");

		}
		final boolean condition4 = !finalDate.isBefore(initialDate);
		

		
		final Set<Workplan> workplans =  entity.getWorkplan();
		if(entity.getIsPrivate().booleanValue()) {
			final boolean condition8 = workplans.stream().anyMatch(x -> !x.getIsPrivate().booleanValue());
			errors.state(request, !condition8, "isPrivate", "manager.task.form.error.taskprivada");

		}
		
		
		
		
		
		errors.state(request, condition1, "title", "manager.task.form.error.spam");
		errors.state(request, condition2, "description", "manager.task.form.error.spam");
		errors.state(request, condition4, "executionPeriod.finalDate", "manager.task.form.error.finalDate");

		
		}
		
	}

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		
			this.repository.save(entity);

		
	}

	
	
	
}
