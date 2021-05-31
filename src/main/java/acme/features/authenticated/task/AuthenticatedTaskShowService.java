


package acme.features.authenticated.task;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.task.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedTaskShowService implements AbstractShowService<Authenticated, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedTaskRepository repository;

	// AbstractShowService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		final int taskId = request.getModel().getInteger("id");
		
		final Task task = this.repository.findTaskById(taskId);
		
		final boolean isPrivate =task.getIsPrivate();
		
		final LocalDateTime finalDate = task.getExecutionPeriod().getFinalDate();
		
		return !isPrivate && finalDate.isBefore(LocalDateTime.now());
		
		
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "executionPeriod.finalDate", "executionPeriod.initialDate","workload","description","url","isPrivate");
		
	
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

}






	

