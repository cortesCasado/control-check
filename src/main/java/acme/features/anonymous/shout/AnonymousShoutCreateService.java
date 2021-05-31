package acme.features.anonymous.shout;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.Spam.AnonymousSpamRepository;
import acme.components.Spam.Spam1;
import acme.entities.shouts.Shout;
import acme.entities.spam.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {
	
	@Autowired
	protected AnonymousShoutRepository shoutRepository;
	
	@Autowired
	protected AnonymousSpamRepository spamRepository;
	
	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		request.bind(entity, errors);
	}
	
	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "author", "text", "link");
	}
	
	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;
		
		Shout result;
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		
		result = new Shout();
		result.setAuthor("");
		result.setText("");
		result.setMoment(moment);
		result.setLink("");
		
		return result;
		
	}
	
	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!entity.getAuthor().isEmpty()) {
		final boolean condition1 =  !Spam1.isSpam(entity.getAuthor(), this.spamRepository.findSpam());

		errors.state(request, condition1, "author", "anonymous.shout.form.error.author");
		
		}
		if(!entity.getText().isEmpty()) {
		final boolean condition2 =  !Spam1.isSpam(entity.getText(), this.spamRepository.findSpam());

		errors.state(request, condition2, "text", "anonymous.shout.form.error.text");
		
		}
		
	}
	
	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		final List<Spam> spam = this.spamRepository.findSpam();
		
		final boolean salida = Spam1.isSpam(entity.getText(),  spam);
		
		if(!salida) {
		this.shoutRepository.save(entity);
		}
		
		
	}
	
	
	


}
