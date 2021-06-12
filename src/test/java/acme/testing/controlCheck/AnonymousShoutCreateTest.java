
package acme.testing.controlCheck;

import java.util.Date;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.entities.somp.Somp;
import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest {

	/**
	 * Click on Anonymous - Shout!, create a shout, list all shouts, show the recently created one
	 * and check that every value is correct.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void createPositive(final int id, final int version, final String author, final String text, final String info, 
		final String somp_code1, final String somp_code2, final String somp_deadline, final String somp_budget, 
		final String somp_important) {
		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", info);
		
		
		Date d = new Date(System.currentTimeMillis() - 1);
		String code = somp_code1 + "-" + Somp.getCodeRegExp(d) + "-" +somp_code2;
		
		super.fillInputBoxIn("somp.code", code);
		super.fillInputBoxIn("somp.deadline", somp_deadline);
		super.fillInputBoxIn("somp.budget", somp_budget);
		super.fillInputBoxIn("somp.important", somp_important);

		super.clickOnSubmitButton("Shout!");

		super.clickOnMenu("Anonymous", "List shouts");

		super.checkColumnHasValue(id, 1, author);
		super.checkColumnHasValue(id, 2, text);
		super.checkColumnHasValue(id, 3, info);
		super.checkColumnHasValue(id, 4, code);
		super.checkColumnHasValue(id, 5, somp_deadline);
		super.checkColumnHasValue(id, 6, somp_budget);
		super.checkColumnHasValue(id, 7, somp_important);

	}

	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created
	 * because it have errors, finish.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void createNegative(final int id, final int version, final String author, final String text, final String info, 
		final String somp_code, final String somp_deadline, final String somp_budget, 
		final String somp_important) {
		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", info);
		super.fillInputBoxIn("somp.code", somp_code);
		super.fillInputBoxIn("somp.deadline", somp_deadline);
		super.fillInputBoxIn("somp.budget", somp_budget);
		super.fillInputBoxIn("somp.important", somp_important);

		super.clickOnSubmitButton("Shout!");

		super.checkErrorsExist("author");
		super.checkErrorsExist("text");
		super.checkErrorsExist("link");
		super.checkErrorsExist("somp.code");
		super.checkErrorsExist("somp.deadline");
		super.checkErrorsExist("somp.budget");

	}
}
