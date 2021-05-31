package acme.features.administrator.spamWord;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.entities.spam.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorSpamWordCreateService implements AbstractCreateService<Administrator, SpamWord> {
	
	@Autowired
	protected AdministratorSpamWordRepository spamWordRepository;
	
	@Override
	public boolean authorise(final Request<SpamWord> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public void bind(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}
	
	@Override
	public void unbind(final Request<SpamWord> request, final SpamWord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "word");
	}
	
	@Override
	public SpamWord instantiate(final Request<SpamWord> request) {
		assert request != null;
		
		SpamWord result;
		final Spam spam = this.spamWordRepository.findSpam().get(0);
		
		result = new SpamWord();
		result.setWord("");
		result.setSpam(spam);
		
		return result;
		
	}
	
	@Override
	public void validate(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final List<String> words = this.spamWordRepository.findAllSpamWords().stream().map(SpamWord::getWord).collect(Collectors.toList());

		errors.state(request, !words.contains(entity.getWord()), "word", "This word is already considered spam");
		
	}
	
	@Override
	public void create(final Request<SpamWord> request, final SpamWord entity) {
		assert request != null;
		assert entity != null;
		
		this.spamWordRepository.save(entity);
	}
	
	@Override
	public void onSuccess(final Request<SpamWord> request, final Response<SpamWord> response) {
		if(request.getMethod().equals(HttpMethod.POST)) response.setView("redirect:/administrator/spam/update");
	}
	
}
