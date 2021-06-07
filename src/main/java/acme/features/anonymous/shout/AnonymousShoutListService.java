package acme.features.anonymous.shout;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousShoutListService implements AbstractListService<Anonymous, Shout>{
	
	@Autowired
	AnonymousShoutRepository shoutRepository;
	
	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "author", "text", "moment", "link",
			"receipt.reference","receipt.deadline","receipt.totalPrice","receipt.paid");
	
	}

	@Override
	public Collection<Shout> findMany(final Request<Shout> request){
		assert request != null;
		
		Collection<Shout> result;
		
		result = this.shoutRepository.findMany();
		result = result.stream().filter(x->x.getMoment().after(Date.valueOf(LocalDate.now().minusMonths(1))))
			.sorted(Comparator.comparing(Shout::getMoment,Comparator.reverseOrder()))
			.collect(Collectors.toList());
		
		return result;
	}
}
