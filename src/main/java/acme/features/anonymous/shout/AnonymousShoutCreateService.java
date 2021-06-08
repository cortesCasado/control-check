
package acme.features.anonymous.shout;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.Spam.AnonymousSpamRepository;
import acme.components.Spam.Spam1;
import acme.entities.receipt.Receipt;
import acme.entities.shouts.Shout;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	@Autowired
	protected AnonymousShoutRepository	shoutRepository;

	@Autowired
	protected AnonymousSpamRepository	spamRepository;


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

		Calendar c = Calendar.getInstance();
		c.setTime(entity.getMoment());

		model.setAttribute("referenciaExPlaceholder", AnonymousShoutCreateService.getReferenciaExRegExp(c, "-") + " [0-9]{2}");

		request.unbind(entity, model, "author", "text", "link", "receipt.referenciaEx",
			//			"receipt.deadlineEx", 
			"receipt.totalPriceEx", "receipt.paidEx");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		Shout result = new Shout();
		Date moment = new Date(System.currentTimeMillis() - 1);

		result.setAuthor("");
		result.setText("");
		result.setMoment(moment);
		result.setLink("");

		Receipt receipt = new Receipt();
		receipt.setPaidEx(Boolean.FALSE);
		receipt.setDeadlineEx(moment);

		result.setReceipt(receipt);
		receipt.setShout(result);

		return result;

	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Receipt receipt = entity.getReceipt();

		String s = receipt.getReferenciaEx();
		
		Calendar c = Calendar.getInstance();
		c.setTime(entity.getMoment());

		String regExp = AnonymousShoutCreateService.getReferenciaExRegExp(c, "-") + " [0-9]{2}";

		if (s != null) {
			final boolean matchRegExp = s.matches(regExp);
			errors.state(request, matchRegExp, "receipt.referenciaEx", "anonymous.shout.form.error.receipt.referenciaExRegExp");

			final boolean uniqueId = this.shoutRepository.findReferenciaExs().stream().noneMatch(r -> r.equals(s));
			errors.state(request, uniqueId, "receipt.referenciaEx", "anonymous.shout.form.error.receipt.referenciaExUnique");
		}
		
		Money m = receipt.getTotalPriceEx();

		if (m != null) {
			final boolean notAllowedCurrency = m.getCurrency().equals("EUR") || m.getCurrency().equals("USD");
			errors.state(request, notAllowedCurrency, "receipt.totalPriceEx", "anonymous.shout.form.error.receipt.totalPriceEx");
		}

		if (!entity.getAuthor().isEmpty()) {
			final boolean condition1 = !Spam1.isSpam(entity.getAuthor(), this.spamRepository.findSpam());
			errors.state(request, condition1, "author", "anonymous.shout.form.error.author");
		}

		if (!entity.getText().isEmpty()) {
			final boolean condition2 = !Spam1.isSpam(entity.getText(), this.spamRepository.findSpam());
			errors.state(request, condition2, "text", "anonymous.shout.form.error.text");
		}
		
		if (errors.hasErrors()) {
			request.getModel().setAttribute("referenciaExPlaceholder", regExp);
		}

	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		this.shoutRepository.save(entity);
		this.shoutRepository.save(entity.getReceipt());

	}

	public static String getReferenciaExRegExp(Calendar c, String separator) {
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		String year = String.valueOf(c.get(Calendar.YEAR));

		if (day.length() == 1)
			day = "0" + day;
		if (month.length() == 1)
			month = "0" + month;

		return day + separator + month + separator + year;
	}

}
