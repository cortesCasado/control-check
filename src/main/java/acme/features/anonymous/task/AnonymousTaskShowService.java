


package acme.features.anonymous.task;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.task.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousTaskShowService implements AbstractShowService<Anonymous, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousTaskRepository repository;

	// AbstractShowService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		final int taskId = request.getModel().getInteger("id");
		
		final boolean isPrivate =this.repository.findTaskById(taskId).getIsPrivate();
		
		final Task task = this.repository.findTaskById(taskId);

		final LocalDateTime finalDate = task.getExecutionPeriod().getFinalDate();
 
		boolean res;

		
		if(finalDate.isAfter(LocalDateTime.now())) {
			res = true;
		}else{
			res = false;
		}
		
		res = !isPrivate && res;
		return res;
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






	

