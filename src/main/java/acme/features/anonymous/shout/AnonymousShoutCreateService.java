
package acme.features.anonymous.shout;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.Spam.AnonymousSpamRepository;
import acme.components.Spam.Spam1;
import acme.entities.receiptEx.ReceiptEx;
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


		request.unbind(entity, model, "author", "text", "link", "receiptEx.referenciaEx",
			//			"receiptEx.deadlineEx", 
			"receiptEx.totalPriceEx", "receiptEx.paidEx");
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

		ReceiptEx receiptEx = new ReceiptEx();
		receiptEx.setPaidEx(Boolean.FALSE);
		receiptEx.setDeadlineEx(moment);

		result.setReceiptEx(receiptEx);
		receiptEx.setShout(result);

		return result;

	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final ReceiptEx receiptEx = entity.getReceiptEx();

		String s = receiptEx.getReferenciaEx();
		
		Calendar c = Calendar.getInstance();
		c.setTime(entity.getMoment());

		String regExp = AnonymousShoutCreateService.getReferenciaExRegExp(c, "-") + " [0-9]{2}";

		if (s != null) {
			final boolean matchRegExp = s.matches(regExp);
			errors.state(request, matchRegExp, "receiptEx.referenciaEx", "anonymous.shout.form.error.receiptEx.referenciaExRegExp");

			final boolean uniqueId = this.shoutRepository.findReferenciaExs().stream().noneMatch(r -> r.equals(s));
			errors.state(request, uniqueId, "receiptEx.referenciaEx", "anonymous.shout.form.error.receiptEx.referenciaExUnique");
		}
		
		Date d = receiptEx.getDeadlineEx();
		
		if (d != null) {
			final boolean dNotAllowed = d.after(entity.getMoment());
			errors.state(request, dNotAllowed, "receiptEx.deadlineEx", "anonymous.shout.form.error.receiptEx.deadlineEx");
		}
		
		Money m = receiptEx.getTotalPriceEx();

		if (m != null) {
			final boolean notAllowedCurrency = m.getCurrency().equals("EUR") || m.getCurrency().equals("USD");
			errors.state(request, notAllowedCurrency, "receiptEx.totalPriceEx", "anonymous.shout.form.error.receiptEx.totalPriceEx");
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
		this.shoutRepository.save(entity.getReceiptEx());

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
