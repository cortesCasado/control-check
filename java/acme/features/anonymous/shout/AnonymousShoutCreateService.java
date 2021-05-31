package acme.features.anonymous.shout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.entities.spam.Spam;
import acme.entities.spam.SpamWord;
import acme.features.anonymous.spam.AnonymousSpamRepository;
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
		result.setAuthor("John Doe");
		result.setText("I'm the best!");
		result.setMoment(moment);
		result.setLink("http://example5.org");
		
		return result;
		
	}
	
	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}
	
	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		
		final StringTokenizer st = new StringTokenizer(entity.getText());
		
		List<Spam> spam = new ArrayList<>();
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		
		spam = this.spamRepository.findSpam();
		
		 final Set<SpamWord> palabras = spam.get(0).getSpamWords();
		
		 final List<SpamWord> palabras1 = new ArrayList<>();
		 
		
		for (final SpamWord x : palabras)
		     palabras1.add(x);
		
		
		
		String texto = entity.getText();
		final Double contador = 0.0;
		
		for(int i = 0; i<palabras1.size(); i++) {
			
		final String palabra = palabras1.get(i).getWord();
		
		
		while (texto.indexOf(palabra) > -1) {
		      texto = texto.substring(texto.indexOf(
		    	  palabra)+ palabra.length(),texto.length());
		}
		}
	
		
		if((contador/st.countTokens()) <= spam.get(0).getThreshold()) {
		this.shoutRepository.save(entity);
			
		}
		
	}
	


}
