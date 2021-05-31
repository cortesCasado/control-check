package acme.features.anonymous.task;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.task.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousTaskListService implements AbstractListService<Anonymous, Task> {

	
	
	@Autowired
	protected AnonymousTaskRepository taskRepository;

	
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "description", "title", "url","executionPeriod.finalDate", "executionPeriod.initialDate","isFinished","workload" );
	
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request){
		assert request != null;
		
		Collection<Task> result;
		
		result = this.taskRepository.findMany();
		result = result.stream().filter(x->x.getIsFinished() == false && x.getIsPrivate()==false)
//			.sorted(Comparator.comparing(x->x.getMoment(),Comparator.reverseOrder()))
			.collect(Collectors.toList());

		return result;
	}
	
	
//	public Collection<Task> findNotFinished(final Request<Task> request){
//		assert request != null;
//		
//		Collection<Task> result;
//		
//		result = this.taskRepository.findNotFinished();
//		
//
//		return result;
//	}

	

}
