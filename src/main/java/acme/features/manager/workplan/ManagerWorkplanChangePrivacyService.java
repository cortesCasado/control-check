
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
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagerWorkplanChangePrivacyService implements AbstractDeleteService<Manager, Workplan> {

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
		//Este metodo está vacio puesto que no es utilizado. 
		//Para encontrar su definicion previa se puede acudir a un commit anterior
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		//Este metodo está vacio puesto que no es utilizado. 
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

		final Boolean condition1 = entity.getTaskList().stream().filter(Task::getIsPrivate).anyMatch(t -> t.getIsPrivate() && entity.getIsPrivate().equals(true));
		final boolean notAllowedTitle = !entity.getIsPrivate() || !Spam1.isSpam(entity.getTitle(), this.spamRepository.findSpam());

		errors.state(request, notAllowedTitle, "title", "manager.workplan.form.error.spam");
		errors.state(request, !condition1, "isPrivate", "manager.workplan.form.button.error"); // Para cambiar de privado a publico no puede tener tareas privadas

		if (errors.hasErrors()) {
			request.getModel().setAttribute("workload", Workplan.getTotalWorkload(entity));
			request.getModel().setAttribute("isPrivate", entity.getIsPrivate());
			request.getModel().setAttribute("suggestedExecutionPeriod", entity.getSuggestedExecutionPeriod());

			final List<Task> myTasks = this.taskRepository.findAllMyTask(request.getPrincipal().getAccountId());
			request.getModel().setAttribute("allTasksAvailable", myTasks.stream().filter(x -> !x.getWorkplan().contains(entity)).collect(Collectors.toList()));
			request.getModel().setAttribute("allTasksAlreadySelected", myTasks.stream().filter(x -> x.getWorkplan().contains(entity)).collect(Collectors.toList()));
			
		}

	}

	@Override
	public void delete(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;

		entity.setIsPrivate(!entity.getIsPrivate());
		this.repository.save(entity);

	}

}
