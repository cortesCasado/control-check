
package acme.features.manager.workplan;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkplanShowService implements AbstractShowService<Manager, Workplan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository	repository;

	@Autowired
	protected ManagerTaskRepository		taskRepository;

	// AbstractShowService<Administrator, UserAccount> interface --------------


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
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("workload", Workplan.getTotalWorkload(entity));
		
		model.setAttribute("isPrivate", entity.getIsPrivate());
		
		final List<Task> myTasks = this.taskRepository.findAllMyTask(request.getPrincipal().getAccountId());
		
		if (entity.getIsPrivate().booleanValue()) {
			final List<Task> l = this.taskRepository.findAllMyTask(request.getPrincipal().getAccountId())
				.stream()
				.filter(x -> !x.getWorkplan().contains(entity))
				.collect(Collectors.toList());
			
			model.setAttribute("allTasksAvailable", l);
		} else {
			final List<Task> l = this.taskRepository.findAllMyTaskOnlyPublic(request.getPrincipal().getAccountId())
				.stream()
				.filter(x -> !x.getWorkplan().contains(entity))
				.collect(Collectors.toList());
			
			model.setAttribute("allTasksAvailable", l);
		}
		
		model.setAttribute("allTasksAlreadySelected", 
			myTasks.stream().filter(x -> x.getWorkplan().contains(entity)).collect(Collectors.toList()));
		
		model.setAttribute("suggestedExecutionPeriod", entity.getSuggestedExecutionPeriod());
		
		request.unbind(entity, model, 
			"title", 
			"executionPeriod.finalDate",
			"executionPeriod.initialDate",
			"isPrivate",
			"task");
		
	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findById(id).orElseThrow(NoSuchElementException::new);
	}

}
