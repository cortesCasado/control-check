package acme.features.anonymous.workplan;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workplan.Workplan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousWorkPlanListServiceExecutionPeriod implements AbstractListService<Anonymous, Workplan>{
	
	@Autowired
	protected AnonymousWorkPlanRepository workPlanRepository;

	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("workload", Workplan.getTotalWorkload(entity));
		request.unbind(entity, model, "title", "executionPeriod.finalDate",  "executionPeriod.initialDate");
	}

	@Override
	public Collection<Workplan> findMany(final Request<Workplan> request) {
		assert request != null;
		
		Collection<Workplan> result;
		
		result = this.workPlanRepository.findPublicWorkPlans();
		
		result = result.stream().filter(x->x.getExecutionPeriod().getFinalDate().isAfter(LocalDateTime.now()))
			.sorted(Comparator.comparing(x->x.getExecutionPeriod().getFinalDate()))
			.collect(Collectors.toList());
		
		return result;
	}
	
	

}