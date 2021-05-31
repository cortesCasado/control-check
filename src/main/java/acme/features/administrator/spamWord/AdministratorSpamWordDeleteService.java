package acme.features.administrator.spamWord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorSpamWordDeleteService implements AbstractDeleteService<Administrator, SpamWord>{
	
	@Autowired
	protected AdministratorSpamWordRepository repository;
	
	@Override
	public boolean authorise(final Request<SpamWord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		//Este metodo está vacio puesto que no se utiliza.
		//Para encontrar su definicion previa se puede acudir a un commit anterior
		
	}

	@Override
	public void unbind(final Request<SpamWord> request, final SpamWord entity, final Model model) {
		//Este metodo está vacio puesto que no se utiliza.
		//Para encontrar su definicion previa se puede acudir a un commit anterior
	}

	@Override
	public SpamWord findOne(final Request<SpamWord> request) {
		assert request != null;
		
		SpamWord result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findSpamWordById(id);

		return result;
	}

	@Override
	public void validate(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<SpamWord> request, final SpamWord entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
	}
	
	@Override
	public void onSuccess(final Request<SpamWord> request, final Response<SpamWord> response) {
		if(request.getMethod().equals(HttpMethod.POST)) response.setView("redirect:/administrator/spam/update");
	}
}
