package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagerTaskDeleteService implements AbstractDeleteService<Manager, Task>  {

	@Autowired
	protected ManagerTaskRepository repository;
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		final boolean result;
		int taskId;
		Task task;

		taskId = request.getModel().getInteger("id");
		task = this.repository.findTaskById(taskId);
		
		final int taskUaId = task.getUserAccount().getId();
		final int principalId = request.getPrincipal().getAccountId();
		
		result = taskUaId==principalId;

		return result;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		//Este metodo está vacio puesto que no se utiliza.
                //Para encontrar su definicion previa se puede acudir a un commit anterior
		
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		//Este metodo está vacio puesto que no se utiliza.
 		//Para encontrar su definicion previa se puede acudir a un commit anterior
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
		
	}

	@Override
	public void delete(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

		
	}

}
