
package acme.features.manager.workplan;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagerWorkplanDeleteService implements AbstractDeleteService<Manager, Workplan> {

	@Autowired
	protected ManagerWorkplanRepository repository;


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
		//Este metodo está vacio puesto que no se utiliza. 
		//Para encontrar su definicion previa se puede acudir a un commit anterior
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

	}

	@Override
	public void delete(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;

		for (final Task task : entity.getTask()) {
			task.getWorkplan().remove(entity);
			this.repository.save(task);
		}

		entity.getTask().clear();

		this.repository.delete(entity);
	}

}
