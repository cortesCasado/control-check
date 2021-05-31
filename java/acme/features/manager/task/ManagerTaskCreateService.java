package acme.features.manager.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.datatypes.ExecutionPeriod;
import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task> {
	
	@Autowired
	protected ManagerTaskRepository taskRepository;
	
	
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		return true;
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

		request.unbind(entity, model, "title", "executionPeriod.finalDate", "executionPeriod.initialDate","workload","description","url","isFinished");
		
	}
	
	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;
		
		Task result;
		Workplan workplan;
		ExecutionPeriod execution;
		final int userAcountId = request.getPrincipal().getAccountId();
		final UserAccount userAccount = this.taskRepository.findOneUserAccountById(userAcountId);

		execution = new ExecutionPeriod();
		execution.setInitialDate(new Date());
		execution.setFinalDate(new Date());
 
		workplan = new Workplan();
		workplan.setTitle("Execution prueba");
		workplan.setExecutionPeriod(execution);
		
		result = new Task();
		result.setTitle("New task");
		result.setDescription("New task");
		result.setIsPrivate(false);
		result.setIsFinished(false);
		result.setUrl("http://google.es/");
		result.setExecutionPeriod(execution);
		result.setUserAccount(userAccount);
		return result;
		
	}
	
	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}
	
	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
//		final Date moment;
		
//		moment = new Date(System.currentTimeMillis() - 1);
//		entity.setMoment(moment);
		this.taskRepository.save(entity);
		
	}


}
