
package acme.features.anonymous.shout;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.Spam.AnonymousSpamRepository;
import acme.components.Spam.Spam1;
import acme.entities.shouts.Shout;
import acme.entities.somp.Somp;
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

		model.setAttribute("codePlaceholder", AnonymousShoutCreateService.getCodeRegExp(entity.getMoment()));

		request.unbind(entity, model, "author", "text", "link", "somp.code",
						"somp.deadline", 
			"somp.budget", "somp.important");
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

		Somp somp = new Somp();
		somp.setImportant(Boolean.FALSE);

		result.setSomp(somp);
		somp.setShout(result);

		return result;

	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Somp somp = entity.getSomp();

		String s = somp.getCode();

		String regExp = AnonymousShoutCreateService.getCodeRegExp(entity.getMoment());

		if (s != null) {
			final boolean matchRegExp = s.matches(regExp);
			errors.state(request, matchRegExp, "somp.code", "anonymous.shout.form.error.somp.codeRegExp");

			final boolean uniqueId = this.shoutRepository.findCodes().stream().noneMatch(r -> r.equals(s));
			errors.state(request, uniqueId, "somp.code", "anonymous.shout.form.error.somp.codeUnique");
		}

		Date d = somp.getDeadline();

		if (d != null) {
			LocalDateTime localDate1 = Instant.ofEpochMilli(entity.getMoment().getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
			
			LocalDateTime localDate2 = Instant.ofEpochMilli(d.getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
			
			long daysBetween = ChronoUnit.DAYS.between(localDate1, localDate2);
			final boolean dNotAllowed = daysBetween >= 7 ;
			errors.state(request, dNotAllowed, "somp.deadline", "anonymous.shout.form.error.somp.deadline");
		}

		Money m = somp.getBudget();

		if (m != null) {
			final boolean notAllowedCurrency = m.getCurrency().equals("EUR") || m.getCurrency().equals("USD");
			errors.state(request, notAllowedCurrency, "somp.budget", "anonymous.shout.form.error.somp.budget");
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
			request.getModel().setAttribute("codePlaceholder", regExp);
		}

	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		this.shoutRepository.save(entity);
		this.shoutRepository.save(entity.getSomp());

	}

	private static String getCodeRegExp(Date d) {
		return "^\\w{3}-"+ Somp.getCodeRegExp(d)+"-\\w{2}$";
	}

}
