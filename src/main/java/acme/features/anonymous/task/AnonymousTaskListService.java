package acme.features.anonymous.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		
		request.unbind(entity, model, "description", "title", "url","executionPeriod.finalDate", "executionPeriod.initialDate","workload" );
	
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request){
		assert request != null;
		
		final Collection<Task> result;
		final List<Task> result2 = new ArrayList<Task>();
		
		result = this.taskRepository.findAllTaskNotPrivate();
		
		
		for( final Task t:result) {
			if(t.getExecutionPeriod().getFinalDate().isAfter(LocalDateTime.now())){
				result2.add(t);
			}
		}

		return result2;
	}
	
	


	

}
