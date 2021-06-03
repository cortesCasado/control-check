
package acme.features.anonymous.shout;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.Spam.AnonymousSpamRepository;
import acme.components.Spam.Spam1;
import acme.entities.info.Info;
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

		model.setAttribute("rareIdPlaceholder", this.getReferenceRegExp(c, "-"));

		request.unbind(entity, model, "author", "text", "link", "infoSheet.rareID", "infoSheet.moment", "infoSheet.money", "infoSheet.flag");
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

		Info infoSheet = new Info();
		infoSheet.setFlag(Boolean.FALSE);
		//		infoSheet.setMoment(moment);

		result.setInfoSheet(infoSheet);
		infoSheet.setShout(result);

		return result;

	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Info infoSheet = entity.getInfoSheet();

		Money m = infoSheet.getMoney();

		if (m != null) {
			final boolean notAllowedCurrency = m.getCurrency().equals("EUR") || m.getCurrency().equals("USD");
			errors.state(request, notAllowedCurrency, "infoSheet.money", "anonymous.shout.form.error.infoSheet.money");
		}

		String s = infoSheet.getRareID();

		if (s != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(entity.getMoment());
			
			String regExp = this.getReferenceRegExp(c, "-"); 
			
			final boolean matchRegExp = s.matches(regExp);
			errors.state(request, matchRegExp, "infoSheet.rareID", "anonymous.shout.form.error.infoSheet.rareIdRegExp");

			if (!matchRegExp) {
				request.getModel().setAttribute("rareIdPlaceholder", regExp);
			}

			final boolean uniqueId = this.shoutRepository.findRareIDs().stream().noneMatch(r -> r.equals(s));
			errors.state(request, uniqueId, "infoSheet.rareID", "anonymous.shout.form.error.infoSheet.rareIdUnique");
		}

		if (!entity.getAuthor().isEmpty()) {
			final boolean condition1 = !Spam1.isSpam(entity.getAuthor(), this.spamRepository.findSpam());
			errors.state(request, condition1, "author", "anonymous.shout.form.error.author");
		}

		if (!entity.getText().isEmpty()) {
			final boolean condition2 = !Spam1.isSpam(entity.getText(), this.spamRepository.findSpam());
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

		this.shoutRepository.save(entity);
		this.shoutRepository.save(entity.getInfoSheet());

	}

	private String getReferenceRegExp(Calendar c, String separator) {
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		String year = String.valueOf(c.get(Calendar.YEAR));

		if (day.length() == 1)
			day = "0" + day;
		if (month.length() == 1)
			month = "0" + month;

		StringBuilder bld = new StringBuilder(year);
		for (int i = year.length(); i < 4; ++i) {
			bld.insert(0, "0");
		}
		year = bld.toString();

		return day + separator + month + separator + year + " [0-9]{2}";
	}

}
