
package acme.features.administrator.spam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSpamUpdateService implements AbstractUpdateService<Administrator, Spam> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSpamRepository spamRepository;

	// AbstractUpdateService<Administrator, Spam> interface -------------


	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

		//Con la siguiente línea añadimos las spamWords al modelo para que puedan 
		//aparecer en la tabla cuando se intenta actualizar con un valor erróneo, 
		//ya que no se pasa desde el formulario
		request.getModel().setAttribute("spamWords", entity.getSpamWords());
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "threshold", "spamWords");

	}

	@Override
	public Spam findOne(final Request<Spam> request) {
		assert request != null;

		List<Spam> result = this.spamRepository.findSpam();

		if (result.isEmpty()) {
			return new Spam();
		} else {
			return result.get(0);
		}
	}

	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Spam> request, final Spam entity) {
		assert request != null;
		assert entity != null;

		this.spamRepository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Spam> request, final Response<Spam> response) {
		if (request.getMethod().equals(HttpMethod.POST))
			response.setView("redirect:/administrator/spam/update");
	}

}
