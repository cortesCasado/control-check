


package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerTaskShowService implements AbstractShowService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;

	// AbstractShowService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<Task> request) {
		

		final Principal uaId = request.getPrincipal();
		final int taskId = request.getModel().getInteger("id");
		final int userRequestId;
		try {
			 userRequestId =this.repository.findTaskById(taskId).getUserAccount().getId();

		}catch(final NullPointerException e) {
			return false;
		}
		
		if(uaId.getAccountId()==userRequestId) {
		
			return uaId.getActiveRole().getSimpleName().equals("Manager");
			
		}else {
			return false;
		}

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






	

