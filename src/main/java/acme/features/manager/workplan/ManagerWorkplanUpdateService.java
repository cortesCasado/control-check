/*
 * AuthenticatedWorkplanUpdateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.manager.workplan;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.Spam.Spam1;
import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkplanUpdateService implements AbstractUpdateService<Manager, Workplan> {

	@Autowired
	protected ManagerWorkplanRepository	repository;

	@Autowired
	protected ManagerTaskRepository		taskRepository;

	@Autowired
	private AdministratorSpamRepository	spamRepository;


	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		final String rol = request.getPrincipal().getActiveRole().getSimpleName();
		final int userAcountId = request.getPrincipal().getAccountId();
		final int taskId = request.getModel().getInteger("id");

		final Optional<Workplan> workplan = this.repository.findOneWorkplanByIdAndUA(taskId, userAcountId);

		return (rol.equals("Manager") && workplan.isPresent());
	}

	@Override
	public void bind(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		//Este metodo está vacio puesto que no se utiliza. 
		//Para encontrar su definicion previa se puede acudir a un commit anterior
	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Porque el framework añade el id 
		try {
			final Object id = request.getModel().getAttribute("task");
			entity.getTask().remove(id);
		} catch (final Throwable t) {
		}

		final boolean notAllowedTitle = entity.getIsPrivate() || !Spam1.isSpam(entity.getTitle(), this.spamRepository.findSpam());
		errors.state(request, notAllowedTitle, "title", "manager.workplan.form.error.spam");
		
		if (errors.hasErrors()) {
			final List<Task> myTasks = this.taskRepository.findAllMyTask(request.getPrincipal().getAccountId());
			request.getModel().setAttribute("allTasksAvailable", myTasks.stream().filter(x -> !x.getWorkplan().contains(entity)).collect(Collectors.toList()));
			request.getModel().setAttribute("allTasksAlreadySelected", myTasks.stream().filter(x -> x.getWorkplan().contains(entity)).collect(Collectors.toList()));
			request.getModel().setAttribute("suggestedExecutionPeriod", entity.getSuggestedExecutionPeriod());

		} else {

			final Boolean condition0 = entity.getExecutionPeriod().getInitialDate().isBefore(entity.getExecutionPeriod().getFinalDate());
			errors.state(request, condition0, "executionPeriod.initialDate", "manager.workplan.form.error.initialDate");

			if (errors.hasErrors()) {
				final List<Task> myTasks = this.taskRepository.findAllMyTask(request.getPrincipal().getAccountId());
				request.getModel().setAttribute("allTasksAvailable", myTasks.stream().filter(x -> !x.getWorkplan().contains(entity)).collect(Collectors.toList()));
				request.getModel().setAttribute("allTasksAlreadySelected", myTasks.stream().filter(x -> x.getWorkplan().contains(entity)).collect(Collectors.toList()));
				request.getModel().setAttribute("suggestedExecutionPeriod", entity.getSuggestedExecutionPeriod());

			} else {
				Object taskDebug = null;
				
				//Obtener la task que queremos añadir del modelo
				try {
					taskDebug = request.getModel().getAttribute("task");
				} catch (final Throwable t) {
				}

				if (taskDebug != null && !taskDebug.equals("")) {
					final Task parsedTask = this.taskRepository.findTaskById(Integer.parseInt(taskDebug.toString()));

					entity.addTask(parsedTask);
					parsedTask.addWorkplan(entity);
				}
			}
		}
	}

	@Override
	public void update(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
