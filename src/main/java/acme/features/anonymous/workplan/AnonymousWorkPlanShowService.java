package acme.features.anonymous.workplan;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workplan.Workplan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousWorkPlanShowService implements AbstractShowService<Anonymous, Workplan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousWorkPlanRepository repository;

	// AbstractShowService<Anonymous, Workplan> interface --------------


	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;
		
		final int workplanId = request.getModel().getInteger("id");
		final Workplan workplan = this.repository.findWorkPlanById(workplanId);
		
		final boolean isPrivate = workplan.getIsPrivate();
		
		final boolean isFinished = workplan.getExecutionPeriod().getFinalDate().isAfter(LocalDateTime.now());
		
		return !isPrivate && isFinished;
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("workload", Workplan.getTotalWorkload(entity));
		request.unbind(entity, model, "title", "executionPeriod.finalDate", "executionPeriod.initialDate", "task");
	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		assert request != null;

		Workplan result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findWorkPlanById(id);

		return result;
	}

}
